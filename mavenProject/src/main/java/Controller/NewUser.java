package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import Model.Beneficiaire;
import Model.Benevole;
import Model.User;
import Model.Valideur;

public class NewUser {
	private int type ;
	
	public NewUser (User user) throws Exception{
		
		if (user instanceof Beneficiaire) {
			type = 0;
		}else if (user instanceof Benevole) {
			type = 1;
		}else if (user instanceof Valideur) {
			type = 2;
		}
		
		ActionPerson del = new ActionPerson("DELETE FROM `Person`;");		
		String commande = "INSERT into Person VALUES ('" +user.getIdUser()+"','" +user.getNom()+ "','" +user.getPrenom()+ "','" +user.getAge()+"','" +type+ "','" +user.getEmail()+"','" +user.getTelephone()+"','" +user.getVille()+"','" +user.getAdresse()+"','" +user.getNote()+"','" +user.getPassword()+"');";
		ActionPerson insert = new ActionPerson(commande);		
		
	}
}
