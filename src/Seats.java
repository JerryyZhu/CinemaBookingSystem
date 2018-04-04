
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
	
	/**
	 * @return the rowID
	 */
	public String getRowID() {
		return rowID;
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
	
	public void printInfo() {
		// Do not print out anything for a row that is completely empty as per specifications
		if (isEmpty()) {
			return;
		}
		else {
			int lastFull = -1;
			int count = 0;
			boolean first = true;
			System.out.print(rowID + ": ");
			for (int i = 0; i < row.length; i++) {
				if (row[i]) {
					if (lastFull == -1) {
						lastFull = i;
					}
					count += 1;
				}
				else {
					if (lastFull == -1) {
						if (first) {
							if (count == 1) {
								System.out.print(lastFull);
							}
							else {
								System.out.print(lastFull + "-" + (lastFull + count - 1));
							}
						}
						else {
							if (count == 1) {
								System.out.print("," + lastFull);
							}
							else {
								System.out.print("," + lastFull + "-" + (lastFull + count - 1));
							}
						}
					}
					else {
						// Do nothing
						lastFull = -1;
					}
				}
			}
		}
	}
	
	private boolean isEmpty() {
		for (int i = 0; i < row.length; i++) {
			if (row[i]) return false;
		}
		return true;
	}
}
