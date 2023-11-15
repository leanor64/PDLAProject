package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import Model.* ;

import View.* ;

public class NewUser {
	private int type ;
	
	public NewUser (User user) throws SQLIntegrityConstraintViolationException{
		
		if (user instanceof Beneficiaire) {
			type = 0;
		}else if (user instanceof Benevole) {
			type = 1;
		}else if (user instanceof Valideur) {
			type = 2;
		}
		
		//Action del = new Action("DELETE FROM `Person`;", "Person");	//A supprimer	
		String commande = "INSERT into Person VALUES ('" +user.getIdUser()+"','" +user.getNom()+ "','" +user.getPrenom()+ "','" +user.getAge()+"','" +type+ "','" +user.getEmail()+"','" +user.getTelephone()+"','" +user.getVille()+"','" +user.getAdresse()+"','" +user.getNote()+"','" +user.getPassword()+"');";
		Action insert = new Action(commande, "Person");
		
	}
	
}
