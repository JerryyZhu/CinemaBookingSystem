import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Sessions {
	private String title;
	private String time;
	Set <Seats> seatingRows;
	
	public Sessions(String title, String time){
		System.out.println("");
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
	
	

}
