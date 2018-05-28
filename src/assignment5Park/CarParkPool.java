package assignment5Park;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarParkPool {
	
	private ExecutorService executor = Executors.newFixedThreadPool(8);
	private ParkHouse parkHouse = new ParkHouse(10);
	
	
	public static void main(String [] args ) {
		CarParkPool cpp = new CarParkPool();
		ParkingHouseManager phm =  new ParkingHouseManager(10,100);
		phm.start();
	}
}