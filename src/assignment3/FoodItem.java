package assignment3;

public class FoodItem {
	private double weight;
	private double volume;
	private String name;
	private String producerName;

	public FoodItem(double volume, double weight, String name) {
		this.name = name;
		this.volume = volume;
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public double getVolume() {
		return volume;
	}

	public String getName() {

		return name;
	}

	public String getProducerName() {
		return producerName;
	}
	public void setProducerName(String n) {
		this.producerName = n;
	}
}
