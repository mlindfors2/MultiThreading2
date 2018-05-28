package assignment5;

import java.util.ArrayList;

public class PoolController implements Runnable {

	private ArrayList<PoolBall> ballList;

	public PoolController(ArrayList<PoolBall> list) {
		this.ballList = list;
	}

	public void moveAll() {
		for (int i = 0;i<ballList.size();i++) {
			ballList.get(i).setX(ballList.get(i).getX()-ballList.get(i).getx_direction());
			ballList.get(i).setY(ballList.get(i).getY()-ballList.get(i).gety_direction());
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
