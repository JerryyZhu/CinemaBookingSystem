import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Sessions {
	private String title;
	private String time;
	Set <Seats> seatingRows;
	
	public Sessions(String title, String time){
//		System.out.println("");
		this.title = title;
		this.time = time;
		seatingRows = new LinkedHashSet<Seats>();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public void createSeats(Set<Row> rows) {
		String letter; 
		int numSeats;
		Row current = null;
		Iterator<Row> itr = rows.iterator();
		while(itr.hasNext()) {
			current = itr.next();
			letter = current.getLetter();
			numSeats = current.getNumSeats();
			
			Seats seating = new Seats(letter,numSeats);
			seatingRows.add(seating);
		}
	}
	
	public boolean requestSeats(int numSeats, Requests record){
		
		// Iterate through each row, call request and determine if
		// if it is successful or not
//		System.out.println("Sessions: requestSeats called for" + time);
		Seats current = null;
		Iterator<Seats> itr = seatingRows.iterator();
		while(itr.hasNext()) {
			current = itr.next();
			if (current == null) break; // May be redundant
			// Check if seats were allocated successfully
			if (current.allocateSeats(numSeats, record)) {
				// Provide record of session time
				record.setTime(time);
				return true;
			}
		}
		// No rows had enough seats
		return false;
		
	}
	
	public boolean changeSeats(int number, Requests record) {
		// Find old row
		Seats oldRow = null;
		Iterator<Seats> itr = seatingRows.iterator();
		while(itr.hasNext()) {
			oldRow = itr.next();
			if (oldRow == null) break; // May be redundant
			// Check if seats were allocated successfully
			oldRow.clearSeats(record);
			return true;
		}
		return false;
	}
	
	public void revertChanges(String row, int start, int numSeats) {
		Seats current = null;
		Iterator<Seats> itr = seatingRows.iterator();
		
		while(itr.hasNext()) {
			current = itr.next();
			if (current == null) break; // May be redundant
			// Re allocate the seats
			if (current.getRowID().equals(row)) {
				current.setSeats(start, numSeats);
			}
			
		}
	}
	
// NOT USED, misread spec, produced output based on seat allocation instead of bookings
// e.g. A: 1-10,11-14 became A: 1-14

//	public void printInfo() {
//		Seats current = null;
//		System.out.println(this.title.trim());
//		Iterator<Seats> itr = seatingRows.iterator();
//		while(itr.hasNext()) {
//			current = itr.next();
//			current.printInfo();
//		}
//	}
}
