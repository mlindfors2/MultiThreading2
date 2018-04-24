package assignment4;

import javax.swing.JTextArea;

public class BoundedBuffer {

	private String[] buffer;
	private BufferStatus[] status;
	private int writePos;
	private int readPos;
	private int findPos;
	private  JTextArea rtxBox;
	private String findString;
	private String replaceString;
	private int start;
	private int nbrReplacements;
	private boolean notify;
	private Object lockObject;
	
	public BoundedBuffer(int maxCapacity) {
		buffer = new String[maxCapacity];
		status = new BufferStatus[maxCapacity];
	}
	
	
	
	
	
	
}
