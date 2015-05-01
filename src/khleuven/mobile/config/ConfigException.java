package khleuven.mobile.config;

public class ConfigException extends Exception {
	private static final long serialVersionUID = 1L;

	public ConfigException() {
		super();
	}

	public ConfigException(String message, Throwable exception) {
		super(message, exception);
	}

	public ConfigException(String message) {
		super(message);
	}

	public ConfigException(Throwable exception) {
		super(exception);
	}

}
