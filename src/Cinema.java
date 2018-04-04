import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Cinema class
 * Maintains the ID of the server and its corresponding sessions
 * Allows operations for allocation, cancelling and changing seat bookings
 * @author Jerry
 *
 */

public class Cinema {
	String id = null;
	Set<Row> rows;
	Set<Sessions> sessions;
	
	/**
	 * Constructor for the cinema class
	 */

	public Cinema(String id) {
		this.id = id;
		rows = new LinkedHashSet<Row>();
		sessions = new LinkedHashSet<Sessions>();
		
	}
	
	/**
	 * Adds a row to the cinema given the letter of the row and the number of seats.
	 * @pre assume that that the row letter has not been used before
	 * @param rowLetter, the letter of the row
	 * @param numSeats, the number of seats in rowLetter
	 */
	public void addRow(String rowLetter, String numSeats) {
		Row newRow = new Row(rowLetter, Integer.parseInt(numSeats));
		rows.add(newRow);
		System.out.println(newRow.getLetter() + " with " + newRow.getNumSeats() + " seats was successfully added to Cinema " + this.id);
	}
	
	/**
	 * Creates a session in the cinema if you prove the time and movie name
	 * @param time
	 * @param movieName
	 */
	public void addSession(String time, String movieName) {
		Sessions newSession = new Sessions(movieName, time);
		System.out.println("Cinema adding sessions with rows" + rows);
		newSession.createSeats(rows);
		sessions.add(newSession);
//		System.out.println("New session added to Cinema " + id + " "+ movieName + " " + time);
	}
	
	/**
	 * Requests seats, books the seats in a custom for a session if given:
	 * the time, number of seats. Takes in a record file which notes the request ID
	 * and relevant details necessary for other actions such as cancel and change
	 * 
	 * @param time
	 * @param numSeats
	 * @param record
	 * @return
	 */
	public boolean requestSeats(String time, int numSeats, Requests record) {
		// Find session that matches the time
//		System.out.println("Cinama" + id + " requestSeats called");
		Sessions current = null;
		Iterator<Sessions> itr = sessions.iterator();
		while(itr.hasNext()) {
			current = itr.next();
			if (time.equals(current.getTime())) {
				// Log what row and seats
				if (current.requestSeats(numSeats,record)) {
					record.setCinemaID(this.id);
					return true; // Seats found
				}
				else {
					return false; // Request rejected
				}
			}
		}
		return false;
		
	}
	/**
	 * Clears a customers tickets without changing record
	 * @param time
	 * @param newTicketNum
	 * @return true if successful, false - original seat bookings were retained
	 */
	public boolean changeTickets(String time, int newTicketNum, Requests record) {
		Sessions current = null;
		Iterator<Sessions> itr = sessions.iterator();
		while(itr.hasNext()) {
			current = itr.next();
			if (time.equals(current.getTime())) {
//				System.out.println("Change tickets correct time was found");
				// Seats were successfully clear
				if (current.changeSeats(newTicketNum, record)) {
//					System.out.println("Session change seat call was successful");
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void revertChanges(String time, String row, int start, int numSeats) {
		// Find the corresponding session
		Sessions current = null;
		Iterator<Sessions> itr = sessions.iterator();
		while(itr.hasNext()) {
			current = itr.next();
			if (time.equals(current.getTime())) {
				current.revertChanges(row,start,numSeats);
			}
		}
	}
	
	public void printSessionInfo(String time) {
//		System.out.println("Inside Cinema" + id + " for time " + time);
		Sessions current = null;
		Iterator<Sessions> itr = sessions.iterator();
		while(itr.hasNext()) {
			current = itr.next();
			if (time.equals(current.getTime())) {
				current.printInfo();
			}
		}
	}
}
