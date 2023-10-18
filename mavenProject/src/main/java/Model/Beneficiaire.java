package Model;

public class Beneficiaire extends User{
	
	public Beneficiaire(int idUser, String password, String nom, String prenom, int age, String email, String telephone, String ville, String adresse) {
		super(idUser, password, nom, prenom, age, email, telephone, ville, adresse);
	}

}
