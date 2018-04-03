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
		System.out.println(rowLetter + " with " + numSeats + " seats was successfully added to Cinema " + this.id);
	}

	public void addSession(String time, String movieName) {
		Sessions newSession = new Sessions(movieName, time);
		sessions.add(newSession);
	}
}
