package assignment3;

public class FoodItem {
	private double weight;
	private double volume;
	private String name;
	
	public FoodItem(double volume , double weight, String name) {
		this.name = name;
		this.volume = volume;
		this.weight = weight;
	}
	public double getWeight() {
		return weight;
	}
	
	public double getVolume () {
		return volume;
	}
}
