package assignment4;

import java.net.SocketTimeoutException;

import javax.management.monitor.Monitor;
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
	private Object lockObject1 = new Object();
	private Object lockObject2 = new Object();
	private Object lockObject3 = new Object();
	private int currentBufferSize;
	int c1=0,c2=0,c3=0;

	public enum BufferStatus {
		Empty, Checked, New;
	}

	public BoundedBuffer(int maxCapacity, String findString, String replaceString) {
		buffer = new String[maxCapacity];
		status = new BufferStatus[maxCapacity];
		this.findString = findString;
		this.replaceString = replaceString;
		currentBufferSize = 0;
		nbrReplacements = 0;
		
		for (int i = 0; i < buffer.length; i++) {
			status[i] = BufferStatus.Empty;
			buffer[i] = new String("");
		}

	}

	public void mark() {

	}

	public void modify() {
		
		System.out.println("Modifying : " + c2 +" NbrReplacements : " +nbrReplacements + " currentBufferSize : " + currentBufferSize);
		c2++;
		
		synchronized(lockObject1) {
		try {
			while ( currentBufferSize == 0 || nbrReplacements > 14) {
				System.out.println("Modifiy fastnade");
				lockObject1.wait();
			}

		if (buffer[findPos].equals(findString) ) {
			System.out.println("Modifier har fått träff. Justerar " +buffer[findPos] + " till : "+ replaceString);
			buffer[findPos] =  replaceString;
			status[findPos] = BufferStatus.Checked;
			nbrReplacements++;
			findPos = (findPos + 1) % buffer.length;
		} else { //if(status[findPos].equals(BufferStatus.New)) {
			status[findPos] = BufferStatus.Checked;
			nbrReplacements++;
			findPos = (findPos + 1) % buffer.length;
		}
		lockObject1.notify();
	}catch (InterruptedException e) {} 
		}
	}

	public String readData() {
		System.out.println("Reading : " + c3 + " NumberReplacements : " + nbrReplacements + " currentBufferSize : " + currentBufferSize );
		c3++;
		String result = "";
		synchronized (lockObject1) {
			try {
				while (nbrReplacements == 0 || currentBufferSize == 0) {
					System.out.println("Reader fastnade");
					lockObject1.wait();
				}
//				if (status[readPos].equals(BufferStatus.Checked)) {
					result = buffer[readPos];
					status[readPos] = BufferStatus.Empty;
					readPos = (readPos + 1) % buffer.length;
					currentBufferSize--;
					nbrReplacements--;
//				} else {
//					readPos = (readPos + 1) % buffer.length;
//				}
				lockObject1.notify();
			} catch (InterruptedException e) {
			}
		}
		return result;
	}

	public String replaceAt(String strSource, String strReplace, int pos, int size) {
		return null;
	}

	public void select() {

	}

	public void writeData(String s) {
		System.out.println("Writing : " + c1 + " CurrentBufferSize : " + currentBufferSize);
		c1++;
		synchronized (lockObject1) {
			try {

				while (currentBufferSize == buffer.length) {
					System.out.println("Writer fastnade");
					lockObject1.wait();
				}

//				if (status[writePos].equals(BufferStatus.Empty)) {
					buffer[writePos] = s;
					status[writePos] = BufferStatus.New;
					writePos = (writePos + 1) % buffer.length;
					currentBufferSize++;
//				} else {
//					writePos = (writePos + 1) % buffer.length;
//				}
				lockObject1.notify();
			} catch (InterruptedException e) {
			}
		}

		// status[writePos] = Checked;

	}

}

//***************************************************************************************************


//
//package assignment4;
//
//import javax.management.monitor.Monitor;
//import javax.swing.JTextArea;
//
//public class BoundedBuffer {
//
//	private String[] buffer;
//	private BufferStatus[] status;
//	private int writePos;
//	private int readPos;
//	private int findPos;
//	private JTextArea rtxBox;
//	private String findString;
//	private String replaceString;
//	private int start;
//	private int nbrReplacements;
//	private boolean notify;
//	private Object lockObject1 = new Object();
//	private Object lockObject2 = new Object();
//	private Object lockObject3 = new Object();
//	private int currentBufferSize;
//	int c1=0,c2=0,c3=0;
//
//	public enum BufferStatus {
//		Empty, Checked, New;
//	}
//
//	public BoundedBuffer(int maxCapacity, String findString, String replaceString) {
//		buffer = new String[maxCapacity];
//		status = new BufferStatus[maxCapacity];
//		this.findString = findString;
//		this.replaceString = replaceString;
//		currentBufferSize = 0;
//		nbrReplacements = 0;
//		
//		for (int i = 0; i < buffer.length; i++) {
//			status[i] = BufferStatus.Empty;
//			buffer[i] = new String("");
//		}
//
//	}
//
//	public void mark() {
//
//	}
//
//	public void modify() {
//		
//		System.out.println("Modifying : " + c2 +" NbrReplacements : " +nbrReplacements + " currentBufferSize : " + currentBufferSize);
//		c2++;
//		
//		synchronized(lockObject1) {
//		try {
//			while ( currentBufferSize == 0 || nbrReplacements > 13) {
//				System.out.println("Modifiy fastnade");
//				lockObject1.wait();
//			}
//
//		if (buffer[findPos].equals("user1AAAAA") && status[findPos].equals(BufferStatus.New)) {
//			buffer[findPos] =  "banjolasse";//replaceString;
//			status[findPos] = BufferStatus.Checked;
//			nbrReplacements++;
//			findPos = (findPos + 1) % buffer.length;
//		} else { //if(status[findPos].equals(BufferStatus.New)) {
//			status[findPos] = BufferStatus.Checked;
//			nbrReplacements++;
//			findPos = (findPos + 1) % buffer.length;
//		}
//		lockObject1.notify();
//	}catch (InterruptedException e) {} 
//		}
//	}
//
//	public String readData() {
//		System.out.println("Reading : " + c3 + " NumberReplacements : " + nbrReplacements + " currentBufferSize : " + currentBufferSize );
//		c3++;
//		String result = "";
//		synchronized (lockObject1) {
//			try {
//				while (nbrReplacements == 0) {
//					lockObject1.wait();
//				}
////				if (status[readPos].equals(BufferStatus.Checked)) {
//					result = buffer[readPos];
//					status[readPos] = BufferStatus.Empty;
//					readPos = (readPos + 1) % buffer.length;
//					currentBufferSize--;
//					nbrReplacements--;
////				} else {
////					readPos = (readPos + 1) % buffer.length;
////				}
//				lockObject1.notify();
//			} catch (InterruptedException e) {
//			}
//		}
//		return result;
//	}
//
//	public String replaceAt(String strSource, String strReplace, int pos, int size) {
//		return null;
//	}
//
//	public void select() {
//
//	}
//
//	public void writeData(String s) {
//		System.out.println("Writing : " + c1 + " CurrentBufferSize : " + currentBufferSize);
//		c1++;
//		synchronized (lockObject1) {
//			try {
//
//				while (currentBufferSize == buffer.length) {
//					lockObject1.wait();
//				}
//
////				if (status[writePos].equals(BufferStatus.Empty)) {
//					buffer[writePos] = s;
//					status[writePos] = BufferStatus.New;
//					writePos = (writePos + 1) % buffer.length;
//					currentBufferSize++;
////				} else {
////					writePos = (writePos + 1) % buffer.length;
////				}
//				lockObject1.notify();
//			} catch (InterruptedException e) {
//			}
//		}
//
//		// status[writePos] = Checked;
//
//	}
//
//}
//
