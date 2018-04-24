package assignment4;

public class Modifier {

	private int nbrOfStrings; // Total numbers of strings to test
	private BoundedBuffer buffer;

	public Modifier(BoundedBuffer buffer, int nbrOfStrings) {
		this.buffer = buffer;
		this.nbrOfStrings = nbrOfStrings;
	}
	
	
	public void modifierLoop() {
		for (int i =0;i<nbrOfStrings;i++) {
			buffer.modify();
		}
	}

}
