package it.rdev.rubrica.model;

import java.util.Set;
import java.util.TreeSet;

public class Contact {

	private Integer id;
	private String name;
	private String surname;
	private Set<String> phoneNumbers;
	private Set<String> emails;
	
	
	public Contact() {}

	public String getName() {
		return name;
	}
	public Contact setName(String name) {
		this.name = name;
		return this;
	}
	public String getSurname() {
		return surname;
	}
	public Contact setSurname(String surname) {
		this.surname = surname;
		return this;
	}
	public Set<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public Contact setPhoneNumbers(Set<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
		return this;
	}
	public Contact addPhone(String phoneNumber) {
		if (this.phoneNumbers == null)
			this.phoneNumbers = new TreeSet<>();
		
		this.phoneNumbers.add(phoneNumber);
		return this;
	}
	public Set<String> getEmails() {
		return emails;
	}
	public Contact setEmails(Set<String> emails) {
		this.emails = emails;
		return this;
	}
	public Contact addEmail(String email) {
		if (this.emails == null)
			this.emails = new TreeSet<>();
		
		this.emails.add(email);
		return this;
	}
	public Integer getId() {
		return id;
	}
	public Contact setId(Integer id) {
		this.id = id;
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", surname=" + surname + ", phoneNumbers=" + phoneNumbers
				+ ", emails=" + emails + "]";
	}

}
