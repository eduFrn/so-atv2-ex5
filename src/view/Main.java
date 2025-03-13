package view;

import controller.ThreadPing;

public class Main {
	public static void main(String[] args) {
		for (int i = 1; i <= 3; i++) {
			ThreadPing ping = new ThreadPing(i);
			ping.start();
		}
	}
}
