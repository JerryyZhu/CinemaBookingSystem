import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CinemaBookingSystem {
	
	Map<String, Cinema> map = new HashMap<String, Cinema>();

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
		File input = new File(fileName);
	      try
	      {
	          sc = new Scanner(input);
	          // Read input from the scanner here
	          while (sc.hasNextLine()) {
	        	  currentLine = sc.nextLine();
	        	  parts = currentLine.split("#"); // Removes the comment
	        	  currentLine = parts[0].trim();
//	        	  System.out.println("What's about to be passed in" + currentLine + " length is " + currentLine.length());
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
		System.out.println("Currently processing: " + line);
		String[] parts = line.split(" ");
		String movieName = "";
		String cinemaName = null;
		Cinema cinema = null;
		
		System.out.println("processCommand first input" + parts[0] +".");
		
		// Determine what command
		switch(parts[0].toLowerCase()) {
			case "cinema":
				cinema = map.get(parts[1]);
				if (cinema == null) {
					cinema = new Cinema(parts[1]);
					map.put(parts[1], cinema);
					System.out.println(parts[1] + "mapped");
				}
				System.out.println("Cinema name" + parts[1] +".");
				cinema.addRow(parts[2],parts[3]);
				break;
			
			case "session":
				// Session 1 09:00 Toy Story
				System.out.println("Inside session");
				for(int i = 3; i < parts.length; i++) {
					movieName = movieName + " " + parts[i];
				}
				System.out.println("Movie name is" + movieName);
				cinemaName = parts[1];
				System.out.println("Cinema name is " + parts[1]+".");
				cinema = map.get(cinemaName);
				if (cinema == null) {
					System.out.println("What the fuck");
					System.out.println("Cinema" + parts[1] + "does not exist");
					break;
				}
				cinema.addSession(parts[2], movieName);
				break;
				
			case "request":
				System.out.println("Not implemented yet");
				break;
				
			case "change":
				System.out.println("Not implemented yet");
				break;
				
			case "cancel":
				System.out.println("Not implemented yet");
				break;
				
			case "print":
				System.out.println("Not implemented yet");
				break;
			default:
				System.out.println("Command not recognised " + parts[0]);
				break;
		}
	}

}
