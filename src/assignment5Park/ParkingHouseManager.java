package assignment5Park;

import java.util.Random;

public class ParkingHouseManager {

	private int parkingSize;// antal tillgängliga parkeringsplatser?
	private ParkSlot[] parkingSlots;
	private CarQueueManager queue; // KÖ med väntande bilar
	private Random rand;
	private Object lockObj = new Object();
	private int maxIterations = 20 ;

	public ParkingHouseManager(int nbrParkingSlots, int queueSize) {
		this.parkingSize = nbrParkingSlots;
		CreateParkingSlots();
		rand = new Random();
		queue = new CarQueueManager(queueSize);
	}

	public void start() {
//		Runnable worker = new WorkerThread("" + i);
//        executor.execute(worker);
	}

	private void CreateParkingSlots() {
		parkingSlots = new ParkSlot[parkingSize];
		for (int i = 0; i < parkingSize; i++) {
			parkingSlots[i] = new ParkSlot();
		}
	}

	private void ParkACar(Object Obj) {
		for (int i = 0; i < maxIterations; i++) {
			synchronized (lockObj) {
				int slot = getAvailableSlot();
				if (slot != -1) {
					Car car = queue.GetACarToPark();
					if (car != null) {
						parkingSlots[slot].setCar(car);
						System.out.println(car.getRegnr().toString());
					}

				}
			}
			try {
				Thread.sleep(rand.nextInt() * 500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Done parking car");
	}

	private void newCarArrived(Object obj) {
		for (int i = 0; i < maxIterations; i++) {
			synchronized (lockObj) {
				int regnr = rand.nextInt(100) * 10;
				Car car = new Car("ABC" + regnr);
				if (queue.add(car)) {
					System.out.println("The car with regnr: " + car.getRegnr() + " has arrived to the queue");
				} else {
					System.out.println("The queue is full!. Come back later");
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
		System.out.println("Done in arrivals");
	}
	
	private void unParkACar(Object obj) {
		int index = 0;
		for (int i = 0;i<maxIterations;i++) {
			synchronized(lockObj) {
				index = getACarToDepart();
				if ( index >=0 && parkingSlots[index].getCar() != null) {
					Car car = parkingSlots[index].getCar();
					System.out.println("hämtar bil med regnr" + car.getRegnr());
					parkingSlots[index] = new ParkSlot();
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Done");
	}

	private int getACarToDepart() {
			int index = 0;
			boolean found = false;
		for (int i =0;i<parkingSlots.length;i++) {
			index = rand.nextInt(parkingSlots.length); 
			if(parkingSlots[i] != null) {
				found = true;
			}
			if (found) {
				return index;
			}
		}
		return -1;
	}

	private int getAvailableSlot() {
		for (int i = 0; i < parkingSlots.length; i++) {
			if (parkingSlots[i].getCar() == null) {
				return i;
			}
		}
		return -1;
	}

}
