package it.rdev.rubrica.model.impl.rdbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;

public class ContactDAOImpl extends AbstractDAO<Contact> implements ContactDAO {

	private final String TABLE_CONTACTS = "contacts";
	private final String TABLE_EMAILS = "emails";
	private final String TABLE_PHONES = "phone_numbers";

	public List<Contact> getAll() {
		List<Contact> contacts = new ArrayList<>();
		try {
			ResultSet rs = this.executeQuery("SELECT id, name, surname FROM " + TABLE_CONTACTS);
			while(rs.next()) {
				contacts.add(
						new Contact()
						.setId(rs.getInt("id"))
						.setName(rs.getString("name"))
						.setSurname(rs.getString("surname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}
	

	@Override
	public boolean persist(Contact t) throws SQLException {

		// Recupero l'id della insert in modo che si possa procedere con l'inserimento anche
		// delle email e dei numeri di telefono.
		Long id = this.executeInsert("INSERT INTO " + TABLE_CONTACTS + "(name, surname)" + 
							"VALUES (?, ?);", t.getName(), t.getSurname() );
		
		for (String phone : t.getPhoneNumbers()) {
			this.executeInsert("INSERT INTO " + TABLE_PHONES + "(contact_id, phone)" + 
					"VALUES (?, ?);", id, phone );
		}
		
		for (String email : t.getEmails()) {
			this.executeInsert("INSERT INTO " + TABLE_EMAILS + "(contact_id, email)" + 
					"VALUES (?, ?);", id, email );
		}
		
		return id != null;
		
	}

	@Override
	public boolean delete(Contact t) throws SQLException {
		return this.executeUpdate("DELETE FROM " + TABLE_CONTACTS + " WHERE id=?;", t.getId()) > 0;
		
	}

	@Override
	public boolean update(Contact t) throws SQLException {
		
		// Se l'id non è impostato, è inutile proseguire con l'esecuzione
		if (t.getId() == null)
			return false;
		
		this.executeUpdate("UPDATE " + TABLE_CONTACTS
							+ " SET name = ?, surname = ?"
							+ " WHERE id=?;", t.getName(), t.getSurname(), t.getId());
		
		// elimino dal db tutte le email e i telefoni per poi reinserirli.
		// TODO meccanismo da migliorare...
		this.executeUpdate("DELETE FROM " + TABLE_EMAILS + " WHERE contact_id=?", t.getId());

		this.executeUpdate("DELETE FROM " + TABLE_PHONES + " WHERE contact_id=?", t.getId());
		
		for (String phone : t.getPhoneNumbers()) {
			this.executeInsert("INSERT INTO " + TABLE_PHONES + "(contact_id, phone)" + 
					"VALUES (?, ?);", t.getId(), phone );
		}
		
		for (String email : t.getEmails()) {
			this.executeInsert("INSERT INTO " + TABLE_EMAILS + "(contact_id, email)" + 
					"VALUES (?, ?);", t.getId(), email );
		}
		
		return true;
	}

}
