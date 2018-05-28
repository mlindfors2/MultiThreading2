package assignment5Park;

public class ParkSlot {
	private int number;
	private Car car;

	public ParkSlot() {
		number = 0;
		car = null;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setParkedCar(Car car) {
		this.car = car;
	}

	public Car getParkedCar() {
		return this.car;
	}
}
