package assignment5Park;

import java.util.LinkedList;
//******************ANVÄNDS EJ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class ParkHouse {
	private int maxCapacity;
	private LinkedList<Car> carQueue = new LinkedList<Car>();
	private ParkSlot[] slots;
	
	public ParkHouse(int maxCapacity, int i) {
		this.maxCapacity = maxCapacity;
		slots =  new ParkSlot[maxCapacity];
	}

	public void push(Car car) {
		carQueue.addLast(car);
	}

	public Car pop(Car car) {
		return carQueue.removeFirst();
	}

	public int getNumberOfCars() {
		return carQueue.size();
	}
}
