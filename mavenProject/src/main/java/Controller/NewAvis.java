package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import Model.Avis;


public class NewAvis {

	public NewAvis(Avis avis,int no_avis) {
		//ActionAvis del = new ActionAvis("DELETE FROM `Avis`;");		
		String commande = "INSERT into Avis VALUES ('" +no_avis+"','"+avis.getDest().getIdUser()+"','" +avis.getEmet().getIdUser()+ "','" +avis.getMessage()+ "','" +avis.getNote()+"');";
		ActionAvis insert = new ActionAvis(commande);
	}
	
}
