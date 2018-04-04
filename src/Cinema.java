import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;



public class Cinema {
	String id = null;
	Set<Row> rows;
	Set<Sessions> sessions;

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

	public void addSession(String time, String movieName) {
		Sessions newSession = new Sessions(movieName, time);
		newSession.createSeats(rows);
		sessions.add(newSession);
		System.out.println("New session added to Cinema " + id + " "+ movieName + " " + time);
	}
	
	public boolean requestSeats(String time, int numSeats, Requests record) {
		// Find session that matches the time
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
}
