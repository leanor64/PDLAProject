package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import Model.Avis;


public class NewAvis {

	public NewAvis(Avis avis,int no_avis) {
		//Action del = new ActionAvis("DELETE FROM `Avis`;", "Avis");		
		String commande = "INSERT into Avis VALUES ('" +no_avis+"','"+avis.getDest().getIdUser()+"','" +avis.getEmet().getIdUser()+ "','" +avis.getMessage()+ "','" +avis.getNote()+"');";
		try {
			Action insert = new Action(commande, "Avis");
		} catch (SQLIntegrityConstraintViolationException e) {}
	}
	
}
