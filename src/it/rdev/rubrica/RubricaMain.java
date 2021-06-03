package it.rdev.rubrica;

import it.rdev.rubrica.controller.RubricaController;
import it.rdev.rubrica.model.Contact;

public class RubricaMain {

	public static void main(String[] args) {
		
		RubricaController controller = new RubricaController();

		Contact c = new Contact();

		c.setName("Antonio");
		c.setSurname("D'Auria");
		
		c.addPhone("3453095317");
		c.addPhone("4325432523");

		c.addEmail("antonio.dauria@protom.com");
		c.addEmail("tonydauria94@gmail.com");
		c.addEmail("antoniodauriadev@gmail.com");
		
		controller.addContact(c);
		
		c = new Contact();
		
		c.setName("Nicola");
		c.setSurname("Ferrare");
		
		c.addPhone("179293782");
		
		c.addEmail("nicola@ferrara.com");
		
		controller.addContact(c);
		
		c = new Contact();
		
		c.setName("Mario");
		c.setSurname("Rossi");
		
		c.addPhone("14232562");
		
		c.addEmail("Mario@Rossi.com");
		
		controller.addContact(c);
		
		System.out.println(controller.getContactList());

		controller.deleteContact(c.setId(1)); // cancella il contatto Nicola Ferrara...
		
		c.setName("Giuseppe");
		c.setSurname("Verdi");
		controller.updateContact(c.setId(1)); // aggiorna il contatto MARIO e rinominarlo in Giuseppe verdi;
		

		System.out.println(controller.getContactList());
	}

}
