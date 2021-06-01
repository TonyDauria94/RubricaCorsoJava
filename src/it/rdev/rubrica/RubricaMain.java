package it.rdev.rubrica;

import java.util.HashSet;
import java.util.Set;

import it.rdev.rubrica.controller.RubricaController;
import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.impl.file.ContactSerializer;

public class RubricaMain {

	public static void main(String[] args) {
		
		RubricaController controller = new RubricaController();

		Contact c = new Contact();

		c.setName("Antonio");
		c.setSurname("D'Auria");

		Set<String> phones = new HashSet<>();
		Set<String> emails = new HashSet<>();
		
		phones.add("3453095317");
		phones.add("4325432523");
		
		emails.add("antonio.dauria@protom.com");
		emails.add("tonydauria94@gmail.com");

		c.setPhoneNumbers(phones);
		c.setEmails(emails);
		
		//controller.addContact(c);
		System.out.println(controller.getContactList());
		
		c = ContactSerializer.deserialize("name=Antonio---surname=D'Auria---phones=4325432523&&&3453095317---emails=antonio.dauria@protom.com&&&tonydauria94@gmail.com");
		System.out.println(c);
	}

}
