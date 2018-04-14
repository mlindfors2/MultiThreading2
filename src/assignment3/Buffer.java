package assignment3;

import java.util.ArrayList;

public class Buffer {

//	private ArrayList<FoodItem> queue = new ArrayList<FoodItem>();
	private FoodItem[] queue;
	private int maxCapacity;

	public Buffer(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		queue = new FoodItem[maxCapacity];
	}
	public void push() {
		
	}
	
	public FoodItem pop() {
		return null;
	}
}
