package assignment5Park;

import java.util.Date;

public class DateTime {
	int hour;
	int minute;
	int second;

	public DateTime (Date date) {
		String dateStr = date.toString().substring(11, 19);
		String [] array = dateStr.split(":");
		hour = Integer.parseInt(array[0]);
		minute = Integer.parseInt(array[1]);
		second = Integer.parseInt(array[2]);
	}
	public int getHour () {
		return hour;
	}
	public int getMinute () {
		return minute;
	}
	public int getSecond () {
		return second;
	}

}
