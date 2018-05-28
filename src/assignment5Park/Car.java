package assignment5Park;

import java.util.Calendar;
import java.util.Date;
/**
 * Car class that handled cars
 * @author Mikael Lindfors
 *
 */
public class Car {
	private String regnr;
	private double startTime;
	private double endTime;
	
	/**
	 * Constructor that sets the registration number on the car.
	 * @param regNr String regnr
	 */
	public Car(String regNr) {
		this.regnr = regNr;
	}
	/**
	 * Method that returns the registration number on the car
	 * @return String regnr
	 */
	public String getRegnr() {
		return regnr;
	}
	/**
	 * Method that sets the registration number on the car.
	 * @param regnr String regnr
	 */
	public void setRegnr(String regnr) {
		this.regnr = regnr;
	}
	/**
	 * Method that gets the start time for the car
	 * @return double startTime
	 */
	public double getStartTime() {
		return startTime;
	}
	/**
	 * Method that sets the starttime for the car
	 * @param startTime Double startTime
	 */
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}
	/**
	 * Method that gets the endtime for the car
	 * @return double endTime
	 */
	public double getEndTime() {
		return endTime;
	}
	/**
	 * Method that sets the endtime for the car
	 * @param endTime double endTime
	 */
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}		
}
