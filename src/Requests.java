
public class Requests {
	private String requestID;
	private String CinemaID;
	private String row;
	private String time;
	private int start;
	private int numSeats;
	private boolean cancelled;
	
	public Requests(String ID) {
		this.requestID = ID;
	}
	
	/**
	 * @return the cinemaID
	 */
	public String getCinemaID() {
		return CinemaID;
	}

	/**
	 * @param cinemaID the cinemaID to set
	 */
	public void setCinemaID(String cinemaID) {
		CinemaID = cinemaID;
	}

	/**
	 * @return the requestID
	 */
	public String getRequestID() {
		return requestID;
	}
	
	/**
	 * @return the cancelled
	 */
	public boolean isCancelled() {
		return cancelled;
	}

	/**
	 * @param cancelled the cancelled to set
	 */
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	/**
	 * @param requestID the requestID to set
	 */
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	/**
	 * @return the row
	 */
	public String getRow() {
		return row;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(String row) {
		this.row = row;
	}
	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * @return the numSeats
	 */
	public int getNumSeats() {
		return numSeats;
	}
	/**
	 * @param numSeats the numSeats to set
	 */
	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}
	
	public void outputBooking() {
		if (numSeats == 1) {
			System.out.println("Booking " + requestID + " " + row + start);
		}
		else {
			System.out.println("Booking " + requestID + " " + row + start + "-" + row + (start + numSeats - 1));
		}
		
	}
	
	public void outputChange() {
		if (numSeats == 1) {
			System.out.println("Change " + requestID + " " + row + start);
		}
		else {
			System.out.println("Change " + requestID + " " + row + start + "-" + row + (start + numSeats - 1));
		}
		
	}
	
}
