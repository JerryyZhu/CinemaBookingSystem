
public class Seats {
	boolean[] row;
	String rowID;
	
	public Seats(String rowID, int numSeats) {
		this.rowID = rowID;
		row = new boolean[numSeats];
		System.out.println(rowID + "created with " + numSeats + "allocated");
	}
	
	
	
}
