package assignment5Park;

import java.util.LinkedList;

public class CarQueueManager {
	private int maxQueueSize = 100;
	private LinkedList<Car> carQueue = new LinkedList<Car>();
	private Object lockObj = new Object();

	public CarQueueManager(int maxSize) {
		this.maxQueueSize = maxSize;
	}

	public boolean add(Car car) {
		synchronized (lockObj) {
			if (carQueue.size() < maxQueueSize && car != null) {
				carQueue.addLast(car);
				return true;
			}
		}
		return false;
	}

	public Car remove() {
		if (carQueue.size() < 1) {
			return null;
		}
		return carQueue.removeFirst();
	}

	public Car GetCarAt(int index) {
		if (index >= 0 && index < maxQueueSize) {
			return carQueue.get(index);
		}
		return null;
	}

	public Car GetACarToPark() {
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
