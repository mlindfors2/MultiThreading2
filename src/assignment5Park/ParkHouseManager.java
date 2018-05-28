package assignment5Park;

import java.util.Random;

public class ParkHouseManager {

	private int parkingSize;

	private ParkSlot[] parkingSlots; // parkerade bilar i parkeringshuset
	private CarQueueManager queue;
	private Random rnd;

	public ParkHouseManager(int maxAvailableSlots, int carQueueSize) {
		parkingSize = maxAvailableSlots;
		createParkingSlots();
		queue = new CarQueueManager(carQueueSize);
		rnd = new Random();
	}

	private void createParkingSlots() {
		parkingSlots = new ParkSlot[parkingSize];

		for (int i = 0; i < parkingSize; i++) {
			parkingSlots[i] = new ParkSlot(); // create all parking slots with default values
		}

	}

	private Object lockObj = new Object();

	public void parkACar() throws InterruptedException {
		synchronized (lockObj) {
			int slot = getAvailableSlot(); // Kollar om finns enledig plats
			if (slot != -1) {
				Car car = queue.getACarToPark();

				if (car != null) // Om bilen fanns
				{
					parkingSlots[slot].setParkedCar(car);

					System.out.println(String.format("Car nr %s is parked at slot nr %d !", car.getRegnr(), slot));

				}
			}
		}
	}

	private int getAvailableSlot() {
		for (int i = 0; i < parkingSlots.length; i++) {
			if (parkingSlots[i].getParkedCar() == null)
				return i;
		}
		return -1;
	}

	public void newCarArrived() throws InterruptedException {
		synchronized (lockObj) {
			int regnr = rnd.nextInt(100);
			Car car = new Car("ABC" + regnr);
			if (queue.add(car)) {

				System.out.println(String.format(" Car nr %s has arrived and put into the queue!", car.getRegnr()));

			} else
				System.out.println(String.format("The queue is full! Come later plz!"));
		}
	}

	public void unParkACar() throws InterruptedException {
		int index = 0;

		synchronized (lockObj) {
			index = getACarToDepart();

			if (index >= 0) {
				Car car = parkingSlots[index].getParkedCar();

				System.out.println(String.format(" Car nr %s left the parking house!", car.getRegnr()));
				parkingSlots[index] = new ParkSlot(); // get default values, release the old object
			}
		}

	}

	private int getACarToDepart() {
		int index = 0;
		Boolean found = false;

		// The following loop is only to randomize a departure
		for (int j = 0; j < parkingSlots.length; j++) {
			index = rnd.nextInt(parkingSlots.length);
			found = isOccupied(index);
			if (found) {
				return index;

			}
		}
		return -1;
	}

	private Boolean isOccupied(int index) {
		// TODO Auto-generated method stub
		return parkingSlots[index].getParkedCar() != null;
	}

}
