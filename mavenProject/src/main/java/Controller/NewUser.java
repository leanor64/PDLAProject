package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import Model.User;

public class NewUser {
	
	public NewUser (User user) {
		//ActionBDD del = new ActionBDD("DELETE FROM `Person` WHERE `username` = maurizio;");		
		String comm = "INSERT into Person VALUES ('" +user.getIdUser()+"','" +user.getNom()+ "','" +user.getPrenom()+ "','" +user.getAge()+"','" +1+ "','" +user.getEmail()+"','" +user.getTelephone()+"','" +user.getVille()+"','" +user.getAdresse()+"','" +user.getNote()+"','" +user.getPassword()+"');";
		ActionBDD insert = new ActionBDD(comm);

	}
}
