package controller;

public class CincoThreadsController extends Thread {
	@Override
	public void run() {
		int tid = (int) getId();
		System.out.println("#"+tid);
	}
}
