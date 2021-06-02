package it.rdev.rubrica.model.impl.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;

public class ContactDAOImpl extends AbstractDAO<Contact> implements ContactDAO {

	@Override
	public List<Contact> getAll() {
		
		List<Contact> list = new ArrayList<>();
		
		// Recupero il reader.
		// Grazie alla clausola try, il reader/writer verrà chiuso automaticamente.
		try (BufferedReader br = DataSource.getInstance().getReader()) {
		    String line;
		    int i = 0;
		    while ((line = br.readLine()) != null) {
		    	// Ogni riga del file è un contatto.
		    	// Effettuo una deserializzazione dei contatti riga per riga.
		    	// Imposto come id il numero di riga che coinciderà anche con la posizione
		    	// del contatto all'interno dell'array.
		    	list.add(ContactSerializer.deserialize(line, i++));
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		// Restituisco la lista di contatti in output.
		return list;
	}
	
	@Override
	public boolean persist(Contact t) throws SQLException, IOException {
		
		// Se il contatto passato è null è inutile continuare.
		if (t == null) 
			return false;
		
		// Recupero il writer in modalità append
		try (BufferedWriter bw = DataSource.getInstance().getWriter(true)) {
			// aggiungo in coda al file il contatto serializzato
			bw.append(ContactSerializer.serialize(t));	
			return true;	
		}
		
	}

	@Override
	public boolean delete(Contact t) throws SQLException, IOException {
		// Se il contatto passato oppure is suo id è null è inutile continuare.
		if (t == null || t.getId() == null) 
			return false;
		
		// L'id del contatto fa riferimento alla riga nella quale si trova all'interno del file
		// e alla posizione nell'array una volta effettuata la deserializzazione.
		
		List<Contact> contacts = this.getAll();
		
		// Se la dimensione della lista contatti è inferiore all'id del contatto passato,
		// allora posso procedere con l'eliminazione del contatto nella posizione corrispondente all'ID.
		// Per sicurezza controllo anche se il contatto in lista e quello in input hanno lo stesso ID, 
		// questa clausola dovrebbe restituire sempre true, in quanto l'accesso al file non è multi-utente.
		if (contacts.size() > t.getId() && t.equals(contacts.get(t.getId()))) {
			
			contacts.remove((int) t.getId());
			
			// Riscrivo tutti i contatti sul file.
			this.writeAll(contacts);
			return true;
			
		}
		
		return false;
	}

	@Override
	public boolean update(Contact t) throws SQLException, IOException {
		// Se il contatto passato oppure is suo id è null è inutile continuare.
		if (t == null || t.getId() == null) 
			return false;
		
		// L'id del contatto fa riferimento alla riga nella quale si trova all'interno del file
		// e alla posizione nell'array una volta effettuata la deserializzazione.
		
		List<Contact> contacts = this.getAll();
		
		// Se la dimensione della lista contatti è inferiore all'id del contatto passato,
		// allora posso procedere con l'update del contatto nella posizione corrispondente all'ID.
		// Per sicurezza controllo anche se il contatto in lista e quello in input hanno lo stesso ID, 
		// questa clausola dovrebbe restituire sempre true, in quanto l'accesso al file non è multi-utente.
		if (contacts.size() > t.getId() && t.equals(contacts.get(t.getId()))) {
			
			// Utilizzo il metodo set per sovrascrivere il contatto e fare quindi l'update
			contacts.set(t.getId(), t);
			
			// Riscrivo tutti i contatti sul file.
			this.writeAll(contacts);
			return true;
			
		}
		
		return false;
	}
	
	/* REINIZIALIZZA il file e ci scrive sopra una lista di contatti.
	 * 
	 * */
	private boolean writeAll(List<Contact> contacts) throws IOException {
		
		// Recupero il writer in modalità NON append
		try (BufferedWriter bw = DataSource.getInstance().getWriter(false)) {
			
			for (Contact c : contacts)
				bw.append(ContactSerializer.serialize(c));
			
			return true;
			
		} 
	}

}
