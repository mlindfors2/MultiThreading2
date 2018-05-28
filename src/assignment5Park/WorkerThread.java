package assignment5Park;

import java.util.Random;

/**
 * Class that contains 3 different tasks for the threadpool to work with.
 * Implements runnable so that it can be executed in the threadpool.
 * @author Mikael Lindfors
 *
 */

public class WorkerThread implements Runnable 
{
	private ParkHouseManager parkHouse;
	private Choices choice;	
	private Random rnd;
	
	/**
	 * Constructor that receives a ParkHouseManager object and a random enum choice which
	 * task will be performed
	 * @param parkHouse ParkHouseManager object
	 * @param rnd - not used
	 * @param choice - random enum which decides which task will be called.
	 */
	public WorkerThread(ParkHouseManager parkHouse, Random rnd, Choices choice)
	{
		this.choice = choice;		
		this.parkHouse = parkHouse; 
		this.rnd = rnd;
	}
	
	/**
	 * Runmethod that will do one of the following 3 tasks, newCarArrived(), parkACar() and unParkCar().
	 * When done it will pause the thread for a second before its complete.
	 */
	@Override
	public void run() 
	{
	    switch (choice)
	    {
	    	case Arrival:
			try {
				parkHouse.newCarArrived();
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		    	break;
	    case Park:
	    	try {
				parkHouse.parkACar();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	break;
	    case Departure:
	    	try {
				parkHouse.unParkACar();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	break;
	    	
	    }
	    try {  Thread.sleep(1000);  } catch (InterruptedException e) { e.printStackTrace(); }  
	}

}
