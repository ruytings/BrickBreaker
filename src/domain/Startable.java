package domain;

public interface Startable {
	
	public void start() throws DomainException ;
	public void resume() throws DomainException;
	public void stop() throws DomainException;
	public StartableStatus getStatus();
	

}
