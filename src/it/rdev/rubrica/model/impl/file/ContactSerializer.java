package it.rdev.rubrica.model.impl.file;

import java.util.Iterator;

import it.rdev.rubrica.model.Contact;

public abstract class ContactSerializer {

	/* Serializza un contatto in modo che possa essere scritto su file.
	 * */
	public static String serialize(Contact c) {
		
		StringBuilder sb = new StringBuilder();

		sb.append("name=" + c.getName());
		sb.append("---surname=" + c.getSurname());
		sb.append("---phones=");
		
		Iterator<String> phonesIt = c.getPhoneNumbers().iterator();
		for(int i = 0; phonesIt.hasNext(); i++) {
			if( i > 0) {
				sb.append("&&&");
			}
			sb.append(phonesIt.next());
		}
		
		
		sb.append("---emails=");
		
		Iterator<String> emailIt = c.getEmails().iterator();
		for(int i = 0; emailIt.hasNext(); i++) {
			if( i > 0) {
				sb.append("&&&");
			}
			sb.append(emailIt.next());
		}
		
		sb.append("\n");
		
		return sb.toString();
		
	}

	/* Deserializza un contatto letto da un file.
	 * */
	public static Contact deserialize(String s) {
		String values[] =s.split("---");
		
		Contact c = new Contact();

		c.setName(values[0].replace("name=", ""));
		c.setSurname(values[1].replace("surname=", ""));

		String phones[]= values[2].split("&&&");
		String emails[]= values[3].split("&&&");
		
		for (String phone : phones) {
			c.addPhone(phone);
		}
		
		for (String email : emails) {
			c.addEmail(email);
		}
		
		return c;
		
	}
	
	/* Deserializza un contatto ed aggiunge la posizione come id
	 * */
	public static Contact deserialize(String s, int pos) {
		return deserialize(s).setId(pos);
	}
}
