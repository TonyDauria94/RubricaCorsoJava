package it.rdev.rubrica.model.impl.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	
	private DataSource()  {
	
		file = new File(Configuration.getInstance().getValue(ConfigKeys.FILE_PATH));
		
		// Se il file non esiste viene creato
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	public BufferedReader getReader() throws FileNotFoundException {
		return new BufferedReader(new FileReader(file));
	}
	
	public BufferedWriter getWriter(boolean append) throws IOException {
		return new BufferedWriter(new FileWriter(file, append));
	}

}
