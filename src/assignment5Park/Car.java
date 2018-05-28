package assignment5Park;

import java.util.Calendar;
import java.util.Date;

public class Car {
	private String regnr;
	
//	private double startTime;
//	private double endTime;
	private DateTime startTime;
	private DateTime endTime;
	
	public Car(String regNr) {
		this.regnr = regNr;
	}
	public String getRegnr() {
		return regnr;
	}
	public void setRegnr(String regnr) {
		this.regnr = regnr;
	}
	public DateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}
	public DateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
	
	public Date setTime() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}
	
	
		
}
