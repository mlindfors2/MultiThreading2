package assignment3;

import java.util.Random;

import javax.swing.JLabel;

public class Producer implements Runnable {

	private Buffer buffer;
	private Thread t1;
	private boolean running = false;
	private Random rand = new Random();
	private String name;
	private FoodItem[] foodBuffer;
	private JLabel lblStatusP;

	public Producer(Buffer buffer, String name, JLabel lblStatusP) {
		this.buffer = buffer;
		this.name = name;
		this.lblStatusP = lblStatusP;
		initFoodItems();
	}

	public boolean checkStatus() {
		return running;
	}

	public void startThread() {
		if (t1 == null) {
			t1 = new Thread(this);
			t1.start();
			running = true;
			lblStatusP.setText("Producing");
		}
	}

	public void stopThread() {
		if (t1 != null) {
			try {
				running = false;
				lblStatusP.setText("Stop");
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		while (running) {
			FoodItem food = foodBuffer[rand.nextInt(20)];
			food.setProducerName(this.name);
			buffer.push(food);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

	private void initFoodItems() {
		foodBuffer = new FoodItem[20];
		foodBuffer[0] = new FoodItem(1.1, 0.5, "Milk");
		foodBuffer[1] = new FoodItem(0.6, 0.1, "Cream");
		foodBuffer[2] = new FoodItem(1.1, 0.5, "Youghurt");
		foodBuffer[3] = new FoodItem(2.24, 0.66, "Butter");
		foodBuffer[4] = new FoodItem(3.4, 1.2, "Flour");
		foodBuffer[5] = new FoodItem(3.7, 1.8, "Suger");
		foodBuffer[6] = new FoodItem(1.55, 0.27, "Salt");
		foodBuffer[7] = new FoodItem(0.6, 0.19, "Almonds");
		foodBuffer[8] = new FoodItem(1.95, 0.75, "Bread");
		foodBuffer[9] = new FoodItem(1.4, 0.5, "Donuts");
		foodBuffer[10] = new FoodItem(1.3, 1.5, "Jam");
		foodBuffer[11] = new FoodItem(4.1, 2.5, "Ham");
		foodBuffer[12] = new FoodItem(6.8, 3.9, "Chicken");
		foodBuffer[13] = new FoodItem(0.87, 0.55, "Salat");
		foodBuffer[14] = new FoodItem(2.46, 0.29, "Orange");
		foodBuffer[15] = new FoodItem(2.44, 0.4, "Apple");
		foodBuffer[16] = new FoodItem(1.3, 0.77, "Pear");
		foodBuffer[17] = new FoodItem(2.98, 2.0, "Soda");
		foodBuffer[18] = new FoodItem(3.74, 1.5, "Beer");
		foodBuffer[19] = new FoodItem(2.0, 1.38, "Hotdogs");
	}
}
