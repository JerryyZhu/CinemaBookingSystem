
public class Seats {
	boolean[] row;
	String rowID;
	
	public Seats(String rowID, int numSeats) {
		this.rowID = rowID;
		row = new boolean[numSeats];
		System.out.println(rowID + "created with " + numSeats + "allocated");
	}
	
	public boolean allocateSeats(int numSeats, Requests record) {
		int start = -1;
		int count = 0;
		for(int i = 0; i<row.length; i++) {
			// Current seat empty
			if (row[i]) {
				// Previous seat was allocated
				if (start == -1) start = i;
				count += 1;
				
				// Sequential number of seats found
				if (numSeats == count) {
					// Retain records
					record.setStart(start);
					record.setNumSeats(numSeats);
					record.setRow(rowID);
					
					// Book seats
					for(int j = start; j <= i; j++) {
						row[j] = true;
					}
					return true;
				}
			}
			// Current seat not empty
			else {
				start = -1;
				count = 0;
			}
		}
		return false;
	}
	
}
