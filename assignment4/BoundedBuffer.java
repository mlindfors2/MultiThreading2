package assignment4;

/**
 * Buffer class that contains a bounded buffer and handles a series of strings
 * from the writer that will be modified by the modifier and read by the reader.
 * 
 * @author Mikael Lindfors
 *
 */
public class BoundedBuffer {

	private String[] buffer;
	private BufferStatus[] status;
	private int writePos;
	private int readPos;
	private int findPos;
	private String findString;
	private String replaceString;
	private int nbrReplacements;
	private Object lockObject1 = new Object();
	private int currentBufferSize;

	public enum BufferStatus {
		Empty, Checked, New;
	}

	/**
	 * Constructor that takes input from the GUI and also sets all enums to Empty in
	 * bufferStatus.
	 * 
	 * @param maxCapacity int max positions in queue.
	 * @param findString String that will be search for by the modifier.
	 * @param replaceString String that will replace all matching strings found by
	 *            modifier.
	 */
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

	/**
	 * Method not used at the moment.
	 */
	public void mark() {

	}

	/**
	 * Method that search for a specific string (word) and replace it with another
	 * string from user input.
	 */
	public void modify() {
		synchronized (lockObject1) {
			try {
				while (status[findPos] != BufferStatus.New) {
					lockObject1.wait();
				}
				if (buffer[findPos].contains(findString)) {
					buffer[findPos] = buffer[findPos].replaceFirst(findString, replaceString);

					status[findPos] = BufferStatus.Checked;
					nbrReplacements++;
					findPos = (findPos + 1) % buffer.length;

				} else {
					status[findPos] = BufferStatus.Checked;
					nbrReplacements++;
					findPos = (findPos + 1) % buffer.length;
				}
				lockObject1.notify();
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Method that reads data from the buffer if its not empty or the buffer'
	 * contains no slots where the modifier has analyzed.
	 * 
	 * @return
	 */
	public String readData() {
		String result = "";
		synchronized (lockObject1) {
			try {
				while (status[readPos] != BufferStatus.Checked) {
					lockObject1.wait();
				}
				result = buffer[readPos];
				status[readPos] = BufferStatus.Empty;
				readPos = (readPos + 1) % buffer.length;
				currentBufferSize--;
				nbrReplacements--;
				lockObject1.notify();
			} catch (InterruptedException e) {
			}
		}
		return result;
	}

	/**
	 * Method not used at the moment
	 * 
	 * @param strSource Not used.
	 * @param strReplace Not used.
	 * @param pos Not used.
	 * @param size Not used.
	 * @return null - Not used.
	 */
	public String replaceAt(String strSource, String strReplace, int pos, int size) {
		return null;
	}

	/**
	 * Method not used at the moment.
	 */
	public void select() {

	}

	/**
	 * Method that writes strings (words) to the buffer if its not full.
	 * 
	 * @param s - String with a single word (or newline).
	 */
	public void writeData(String s) {
		synchronized (lockObject1) {
			try {
				while (status[writePos] != BufferStatus.Empty) {
					lockObject1.wait();
				}
				buffer[writePos] = s;
				status[writePos] = BufferStatus.New;
				writePos = (writePos + 1) % buffer.length;
				currentBufferSize++;
				lockObject1.notify();
			} catch (InterruptedException e) {
			}
		}

	}

}
