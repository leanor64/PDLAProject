package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import Model.* ;

import View.* ;

public class NewUser {	
	/* type : numero attribue
	 Benevole : 0
	 Beneficiaire : 1
	 Valideur : 2
	 */
	
	public NewUser (String idUser, String password, String nom, String prenom, int age, String email, String telephone, String ville, String adresse, int note, int type) throws SQLIntegrityConstraintViolationException{
		//Action del = new Action("DELETE FROM `Person`;", "Person");	//A supprimer	
		String commande = "INSERT into Person VALUES ('" +idUser+"','" +nom+ "','" +prenom+ "','" +age+"','" +type+ "','" +email+"','" +telephone+"','" +ville+"','" +adresse+"','" +note+"','" +password+"');";
		Action insert = new Action(commande, "Person");
		
	}
	
}
