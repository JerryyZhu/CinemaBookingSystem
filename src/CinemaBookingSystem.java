import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CinemaBookingSystem {
	
	Map<String, Cinema> map = new HashMap<String, Cinema>();
	Set<Requests> requestSet;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		generateFromFile(args[0]);
		CinemaBookingSystem system = new CinemaBookingSystem();
		system.generateFromFile(args[0]);

	}
	
	private void generateFromFile(String fileName) {
		Scanner sc = null;
		String currentLine = null;
		String[] parts = null;
		
		requestSet = new LinkedHashSet<Requests>();
		File input = new File(fileName);
	      try
	      {
	          sc = new Scanner(input);
	          // Read input from the scanner here
	          while (sc.hasNextLine()) {
	        	  currentLine = sc.nextLine();
	        	  parts = currentLine.split("#"); // Removes the comment
	        	  currentLine = parts[0].trim();
	        	  if (currentLine.length() < 3) {
	        		  continue;
	        	  }
	        	  processCommand(currentLine);
	          }
	      }
	      catch (FileNotFoundException e)
	      {
	          System.out.println(e.getMessage());
	      }
	      finally
	      {
	          if (sc != null) sc.close();
	      }
	}
	
	public void processCommand(String line) {
		// Cinema 1 A 15  # Row A of cinema 1 has 15 seats
		System.out.println("======== Currently processing: " + line + "========");
		String[] parts = line.split(" ");
		String movieName = "";
		String cinemaID = null;
		Cinema cinema = null;
		String time = null;
		String id = null;
		Requests current = null;
		
		// Determine what command
		switch(parts[0].toLowerCase()) {
			case "cinema":
				cinema = map.get(parts[1]);
				if (cinema == null) {
					cinema = new Cinema(parts[1]);
					map.put(parts[1], cinema);
//					System.out.println(parts[1] + "mapped");
				}
//				System.out.println("Cinema name" + parts[1] +".");
				cinema.addRow(parts[2],parts[3]);
				break;
			
			case "session":
				// Session 1 09:00 Toy Story
//				System.out.println("Inside session");
				for(int i = 3; i < parts.length; i++) {
					movieName = movieName + " " + parts[i];
				}
//				System.out.println("Movie name is" + movieName);
				cinemaID = parts[1];
//				System.out.println("Cinema name is " + parts[1]+".");
				cinema = map.get(cinemaID);
				if (cinema == null) {
//					System.out.println("Cinema" + parts[1] + "does not exist");
					break;
				}
				cinema.addSession(parts[2], movieName);
				break;
				
			case "request":
				// Request <id> <cinema> <time> <tickets>
				time = parts[3];
				int numTickets = Integer.parseInt(parts[4]);
				cinemaID = parts[2];
				
				// Request, 
				Requests record = new Requests(parts[1]);
				
				cinema = map.get(cinemaID);
				if (cinema.requestSeats(time, numTickets, record)) {
					record.setCinemaID(cinemaID);
					record.outputBooking();
					requestSet.add(record);
				}
				else {
					System.out.println("Booking rejected");
				}
//				System.out.println("Not implemented yet");
				break;
				
			case "change":
				// Change <id> <cinema> <time> <tickets>
				int newTicketNum = Integer.parseInt(parts[4]);
				Cinema newCinema = null;
				cinemaID = parts[2];
				time = parts[3];
				id = parts[1];
				
				// Find corresponding record, otherwise do nothing
				Iterator<Requests> itr = requestSet.iterator();
				while(itr.hasNext()) {
					current = itr.next();
					
					// If ID of the record and command match
//					System.out.println("Currently looking for Request ID" + id + " We are at id:" + current.getCinemaID());
					if (id.equals(current.getRequestID())) {
						
						// Grab old details
						String oldCinema = current.getCinemaID();
						String oldSession = current.getTime();
						String oldRow = current.getRow();
						int oldStart = current.getStart();
						int oldNumSeats = current.getNumSeats();
						
						// Remove booking
						cinema = map.get(oldCinema);
						cinema.changeTickets(oldSession, oldNumSeats, current);
						
						// Try Booking New 
						newCinema = map.get(cinemaID);
						if (cinema.requestSeats(time, newTicketNum, current)) {
							current.setCinemaID(cinemaID);
							current.outputChange();
						}
						else {
							// TESTING ONLY
							cinema.revertChanges(oldSession, oldRow, oldStart, oldNumSeats);
//							current.outputChange(); // TESTING ONLY
							System.out.println("Change rejected");
						}
						break;
					}
					
				}
//				System.out.println("Not implemented yet");
				break;
				
			case "cancel":
				// Cancel <id>
				// Find the request id, to get relevant details such as cinema, session, and seat numbers
				id = parts[1];
				Iterator<Requests> itrr = requestSet.iterator();
				while(itrr.hasNext()) {
					current = itrr.next();
					if (id.equals(current.getRequestID())) {
						// Early exit, if cancelled dont need to clear seats, in case they've been booked
						// again
						if (current.isCancelled()) {
							System.out.println("Cancel rejected");
							return;
						}
						
						String oldCinema = current.getCinemaID();
						String oldSession = current.getTime();
						int oldNumSeats = current.getNumSeats();
						
						// Remove booking
						cinema = map.get(oldCinema);
						cinema.changeTickets(oldSession, oldNumSeats, current);
						current.setCancelled();
						System.out.println("Cancel " + id);
					}
					
				}
				
//				System.out.println("Not implemented yet");
				break;
				
			case "print":
				cinemaID = parts[1];
				cinema = map.get(cinemaID);
//				System.out.println(parts[1]);
				if (cinema != null) {
					cinema.printSessionInfo(parts[2]);
				}
				
//				System.out.println("Not implemented yet");
				Set<String> cinemaRows = new LinkedHashSet<String>();
				// Iterate through all requests
				Iterator<Requests> itr = requestSet.iterator();
				
				// Need a set of rows to look through
				
				break;
				
			default:
//				System.out.println("Command not recognised " + parts[0]);
				break;
		}
	}
	public void print(String cinemaID, String time) {
		Cinema cinema = map.get(cinemaID);
		if (cinema != null) {
			
		}
		Set<String> cinemaRows = new LinkedHashSet<String>();
		// Iterate through all requests
		Iterator<Requests> itr = requestSet.iterator();
		Requests current = null;
		while(itr.hasNext()) {
			current = itr.next();
			if (current.getCinemaID().equals(cinemaID) && )
		}
		
	}
}
