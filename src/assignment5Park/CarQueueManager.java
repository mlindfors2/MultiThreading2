package assignment5Park;

import java.util.LinkedList;

/**
 * Class that handles the "buffer" with cars
 * 
 * @author Mikael Lindfors
 *
 */

public class CarQueueManager {
	private int maxQueueSize = 100;
	private LinkedList<Car> carQueue = new LinkedList<Car>();
	private Object lockObj = new Object();

	/**
	 * Constructor that sets the maxsize for the buffer.
	 * 
	 * @param maxSize
	 */
	public CarQueueManager(int maxSize) {
		this.maxQueueSize = maxSize;
	}

	/**
	 * Method that adds a car to the queue. Uses lockObj for synchronization and
	 * will only add car if the buffer isn't full or the car object isn't null.
	 * 
	 * @param car Car object
	 * @return boolean true if successful or false if buffer is full or car is null.
	 */
	public boolean add(Car car) {
		synchronized (lockObj) {
			if (carQueue.size() < maxQueueSize && car != null) {
				carQueue.addLast(car);
				return true;
			}
		}
		return false;
	}

	/**
	 * Method that removes a car from the first slot in the queue. If queue is empty
	 * null will be returned.
	 * 
	 * @return Car object
	 */
	public Car remove() {
		if (carQueue.size() < 1) {
			return null;
		}
		return carQueue.removeFirst();
	}

	/**
	 * Method that returns a car at a specific index if index is a correct number.
	 * 
	 * @param index int index on what position the car is stored at.
	 * @return car object
	 */
	public Car getCarAt(int index) {
		if (index >= 0 && index < maxQueueSize) {
			return carQueue.get(index);
		}
		return null;
	}

	/**
	 * Method that uses lockObj to synchronize and check if the queue isnt empty and that the queue hasnt grown above maxQueueSize, then returns the first 
	 * car from the queue.
	 * @return Car object.
	 */
	public Car getACarToPark() {
		Car car = null;
		synchronized (lockObj) {
			int count = carQueue.size();
			if ((count > 0) && (count < maxQueueSize)) {
				car = carQueue.removeFirst();
			}

		}
		return car;
	}
}
