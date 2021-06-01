package it.rdev.rubrica.model.impl.file;

import java.io.File;

import it.rdev.rubrica.config.ConfigKeys;
import it.rdev.rubrica.config.Configuration;

public class DataSource {
	
private static DataSource ds;
	
	public static DataSource getInstance() {
		if(ds == null) {
			ds = new DataSource();
		}
		return ds;
	}
	
	private File file;
	
	private DataSource() {
		try {
			file = new File(Configuration.getInstance().getValue(ConfigKeys.FILE_PATH));
			
			// se il file non esiste viene creato
			file.createNewFile();
		
		} catch (Exception e) {

		}
	}
	
	public File getFile() {
		return file;
	}
	

}
