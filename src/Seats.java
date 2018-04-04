
public class Seats {
	boolean[] row;
	String rowID;
	
	public Seats(String rowID, int numSeats) {
		this.rowID = rowID;
		row = new boolean[numSeats+1];
//		System.out.println(rowID + "created with " + numSeats + "allocated" + row[0]);
//		System.out.print("WHAT");
	}
	
	public boolean allocateSeats(int numSeats, Requests record) {
//		System.out.println("allocateSeats called for" + rowID);
		int start = -1;
		int count = 0;
		for(int i = 1; i<row.length; i++) {
			// Current seat empty
			if (!row[i]) {
				// Previous seat was allocated
				if (start == -1) start = i;
				count += 1;
				
				// Sequential number of seats found
				if (numSeats == count) {
//					System.out.println("allocateSeats: Success " + rowID + start +'-'+ (start+numSeats-1));
					// Retain records
					record.setStart(start);
					record.setNumSeats(numSeats);
					record.setRow(rowID);
					
					// Book seats
					for(int j = start; j <= i; j++) {
						row[j] = true;
					}
//					System.out.println("Seats found " + rowID + " " + start + "-" + (start+numSeats-1));
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
	
	public void clearSeats(Requests record) {
		int numSeats = record.getNumSeats();
		int startNum = record.getStart();
		
		for (int i = startNum; i<(startNum + numSeats); i++) {
			row[i] = false;
		}
	}
	
	public void setSeats(int startNum, int numSeats) {
		for (int i = startNum; i<(startNum + numSeats); i++) {
			row[i] = true;
		}
	}
	
	public void revertSeats(Requests record) {
		int numSeats = record.getNumSeats();
		int startNum = record.getStart();
		
		for (int i = startNum; i<(startNum + numSeats); i++) {
			row[i] = true;
		}
	}
}
