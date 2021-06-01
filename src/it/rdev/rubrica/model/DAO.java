package it.rdev.rubrica.model;

import java.io.IOException;
import java.sql.SQLException;

public interface DAO<T> {
	
	boolean persist(T t) throws SQLException, IOException;
	
	boolean delete(T t) throws SQLException, IOException;
	
	boolean update(T t) throws SQLException, IOException;
	
}
