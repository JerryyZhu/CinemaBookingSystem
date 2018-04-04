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
	

}
