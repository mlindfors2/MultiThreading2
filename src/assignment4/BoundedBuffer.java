package assignment4;

import javax.swing.JTextArea;

public class BoundedBuffer {

	private String[] buffer;
	private BufferStatus[] status;
	private int writePos;
	private int readPos;
	private int findPos;
	private JTextArea rtxBox;
	private String findString;
	private String replaceString;
	private int start;
	private int nbrReplacements;
	private boolean notify;
	private Object lockObject;
	
	public enum BufferStatus {
		Empty,Checked, New;
	}
	

	public BoundedBuffer(int maxCapacity, String findString, String replaceString) {
		buffer = new String[maxCapacity];
		this.findString = findString;
		this.replaceString = replaceString;
		
		
	}

	public void mark() {

	}

	public void modify() {

	}

	public String readData() {
		return null;
	}

	public String replaceAt(String strSource, String strReplace, int pos, int size) {
		return null;
	}

	public void select() {

	}

	public void writeData(String s) {

	}

}
