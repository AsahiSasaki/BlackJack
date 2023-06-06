package controller;

import java.util.Random;

public class Prob {

	public static void main(String[] args) {
		Random rand = new Random();
		for(int i = 0; i<100;i++) {
			double d = rand.nextInt();
			if(d<0.3) {
				System.out.println("〇");
			}
			if(d>0.4) {
				System.out.println("×");
			}
		}
	}

}
