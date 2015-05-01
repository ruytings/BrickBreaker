package domain;

public abstract class StartableObject implements Startable {

	private StartableStatus status = StartableStatus.CREATED;

	public void start() throws DomainException {
		if(status.equals(StartableStatus.STARTED) || status.equals(StartableStatus.RESUMED)){
			throw new DomainException("Timer already started");
		}
		setStatus(StartableStatus.STARTED);
	}

	public void resume() throws DomainException  {
		if(status.equals(StartableStatus.STARTED) || status.equals(StartableStatus.CREATED) || status.equals(StartableStatus.RESUMED)){
			throw new DomainException("Timer already started");
		}
		setStatus(StartableStatus.RESUMED);
	}

	public void stop() throws DomainException  {
		if(status.equals(StartableStatus.STOPPED) || status.equals(StartableStatus.CREATED)){
			throw new DomainException("Timer not started");
		}
		setStatus(StartableStatus.STOPPED);
	}

	public StartableStatus getStatus() {
		return status;
	}

	private void setStatus(StartableStatus status) {
		this.status = status;
	}
	
	
}
