package assignment5Park;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Mainclass that  initialize the ParkHouseManager and setup a Threadpool with 5 threads.
 * @author Mikael Lindfors
 *
 */

public class CarpoolMain {

	/**
	 * Mainmethod that starts 20 tasks with random tasks (newCarArrived(), parkACar(), unParkACar() ) and sends 
	 * them to the threadpool for execution.
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// Object lockObj = new Object();
		ParkHouseManager parkHouse = new ParkHouseManager(50, 150);
		Random rnd = new Random();
		ExecutorService executor = Executors.newFixedThreadPool(5);// creating a pool of 5 threads

		for (int i = 0; i < 20; i++) {
			// synchronized(lockObj) //Do not synchronize to simulate real scenario
			{
				int intChoice = rnd.nextInt(3);

				Choices choice = Choices.values()[intChoice];
				Runnable worker = new WorkerThread(parkHouse, rnd, choice);
				executor.execute(worker);// calling execute method of ExecutorService
			}
		}
		executor.shutdown();

		while (!executor.isTerminated()) {
		}

		System.out.println("Finished all threads");

	}

}
