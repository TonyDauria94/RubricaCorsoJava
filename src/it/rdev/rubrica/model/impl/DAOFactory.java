package it.rdev.rubrica.model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import it.rdev.rubrica.config.ConfigKeys;
import it.rdev.rubrica.config.Configuration;
import it.rdev.rubrica.model.ContactDAO;

public class DAOFactory {
	
private static final Map<String, String> CLASS_MAP;
	
	static {
		CLASS_MAP = new HashMap<>();
		CLASS_MAP.put("FILE", "it.rdev.rubrica.model.impl.file.ContactDAOImpl");
		CLASS_MAP.put("RDBMS", "it.rdev.rubrica.model.impl.rdbms.ContactDAOImpl");
	}

	public static ContactDAO getContactDAO() {
		ContactDAO dao = null;
		try {
			Class<?> clazz = (Class<?>) Class.forName(
					CLASS_MAP.get( Configuration.getInstance().getValue(
								ConfigKeys.PERSISTENCE
							))
					);
			
			dao = (ContactDAO) clazz.getConstructor().newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		return dao;
	}
	
}
