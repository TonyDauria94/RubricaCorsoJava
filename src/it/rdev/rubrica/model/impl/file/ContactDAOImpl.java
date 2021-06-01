package it.rdev.rubrica.model.impl.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;
import it.rdev.rubrica.model.impl.rdbms.AbstractDAO;

public class ContactDAOImpl extends AbstractDAO<Contact> implements ContactDAO {

	@Override
	public List<Contact> getAll() {
		return null;
	}
	
	@Override
	public boolean persist(Contact t) throws SQLException {
		
		DataSource ds = DataSource.getInstance();
		File f = ds.getFile();
		
		try {
		    BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
			writer.append(t.serialize());	
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Contact t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Contact t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
