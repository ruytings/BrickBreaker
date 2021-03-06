package domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Chronometer extends StartableObject implements Subject {

	private static final int DELAY_IN_MILLISECONDS = 0;
	private int intervalInMilliseconds = 1;
	private java.util.Timer timer;
	private int time;
	
	private Set<Observer> observers = new HashSet<Observer>();

	public Chronometer() throws DomainException {
	}
	
	public Chronometer(int interval) throws DomainException {
		setIntervalInMilliseconds(interval);
	}

	public void start() throws DomainException  {
		setTime(0);
		run();
		super.start();
	}

	public void resume()  throws DomainException {
		run();
		super.resume();
	}

	private void run() {
		if (timer != null) {
			timer.cancel();
		}
		timer = new Timer();
		timer.schedule(new Task(), DELAY_IN_MILLISECONDS,
				getIntervalInMilliseconds());
	}

	public void stop() throws DomainException  {
		if (timer != null) {
			timer.cancel();
		}
		super.stop();
	}

	public int getTime() {
		return time;
	}

	private void setTime(int time) {
		this.time = time;
	}

	private class Task extends TimerTask {
		public void run() {
			setTime(getTime() + getIntervalInMilliseconds());
			try {
				notifyObservers();
			} catch (DomainException e) {
				e.printStackTrace();
			}
		}
	}

	private int getIntervalInMilliseconds() {
		return intervalInMilliseconds;
	}

	private void setIntervalInMilliseconds(int intervalInMilliseconds)
			throws DomainException {
		if (intervalInMilliseconds <= 0) {
			throw new DomainException("Interval should not be negative");
		}
		this.intervalInMilliseconds = intervalInMilliseconds;
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		Iterator<Observer> iterator = observers.iterator();
		while(iterator.hasNext()){
			Observer o = iterator.next();
			if(o.equals(observer)){
				iterator.remove();
			}
		}
	}

	@Override
	public void notifyObservers() throws DomainException {
		for(Observer o :observers){
			o.update();
		}
	}
}
