package assignment3;
/**
 * FoodItem class used for transfering Food between producers and consumers.
 * @author mlind
 *
 */
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
/**
 * Returns the weight of a FoodItem.
 * @return double weight
 */
	public double getWeight() {
		return weight;
	}
/**
 * Returns the volume of a FoodItem.
 * @return double volume;
 */
	public double getVolume() {
		return volume;
	}
/**
 * Returns the name of the FoodItem.
 * @return String name
 */
	public String getName() {

		return name;
	}
/**
 * Returns the name of the producer of the FoodItem.
 * @return String producerName
 */
	public String getProducerName() {
		return producerName;
	}
	/**
	 * Method that sets the name of the producer of the FoodItem.
	 * @param n String name of the producer.
	 */
	public void setProducerName(String n) {
		this.producerName = n;
	}
}
