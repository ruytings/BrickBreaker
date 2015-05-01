package khleuven.mobile.chronometer;

import khleuven.mobile.KHLeuvenMobileException;

public interface Startable {

	public abstract void start() throws KHLeuvenMobileException;

	public abstract void resume() throws KHLeuvenMobileException;

	public abstract void stop() throws KHLeuvenMobileException;

	public abstract StartableStatus getStatus();

}