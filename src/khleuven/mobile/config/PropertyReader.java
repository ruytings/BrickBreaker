package khleuven.mobile.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	private static PropertyReader uniqueInstance;
	
	private String filename;
	private InputStream inputStream;
	private Properties properties;
	
	private PropertyReader(String filename) {
		super();
		this.setFilename(filename);
	}
	
	private PropertyReader(InputStream inputStream){
		super();
		this.setInputStream(inputStream);
	}
	
	private void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;		
	}
	
	private InputStream getInputStream(){
		return inputStream;
	}

	public static PropertyReader getInstance(String filename){
		if(uniqueInstance == null){
			uniqueInstance = new PropertyReader(filename);
		}
		return uniqueInstance;
	}
	
	public static PropertyReader getInstance(InputStream inputStream){
		if(uniqueInstance == null){
			uniqueInstance = new PropertyReader(inputStream);
		}
		return uniqueInstance;
	}
	
	public Properties getProperties() throws ConfigException{
		if(properties == null){
			try {
				properties = new Properties();
				if(getInputStream()==null){
					setInputStream(new FileInputStream(getFilename()));
				}				 
				properties.load(getInputStream());
				getInputStream().close();
			} catch (FileNotFoundException e) {
				throw new ConfigException(e.getMessage(), e);
			} catch (IOException e) {
				throw new ConfigException(e.getMessage(), e);
			}
			
		}
		return properties;
	}

	private String getFilename() {
		return filename;
	}

	private void setFilename(String filename) {
		this.filename = filename;
	}
}
