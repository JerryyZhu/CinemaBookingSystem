/**
 * 
 * @author Jerry
 *
 */

public class Row {
	private String letter;
	private int numSeats;
	
	public Row(String letter, int numSeats) {
		this.letter = letter;
		this.numSeats = numSeats;
	}

	public String getLetter() {
		return letter;
	}

	public int getNumSeats() {
		return numSeats;
	}
}
