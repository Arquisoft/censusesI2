package es.uniovi.parser;

public class Voter {

	private String name, email, nif, password;
	private int pollStCode;
	
	public Voter(String name, String email, String nif, int pollStCode, String password) {
		this.name = name;
		this.email = email;
		this.nif = nif;
		this.pollStCode = pollStCode;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getNif() {
		return nif;
	}

	public int getPollStCode() {
		return pollStCode;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Voter [name=" + name + ", email=" + email + ", nif=" + nif + ", pollStCode=" + pollStCode
				+ ", password=" + password + "]";
	}
	
	
}
