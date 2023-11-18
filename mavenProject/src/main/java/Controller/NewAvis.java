package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import Model.Avis;
import Model.User;


public class NewAvis {
	
	public NewAvis(String message, String destinataire, String emetteur, int note) {
		int noAvis = 1 ;
		String BDD = "projet_gei_014";
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/" + BDD;
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();

		    //réaliser la commande dans la database et récupérer le nombre de lignes trouvées
		    ResultSet result = state.executeQuery("SELECT MAX(num) FROM Avis ;");
	        ResultSetMetaData resultMeta = result.getMetaData();                                         

	        
		    /*S'il y a des avis dans la table, on récupère le plus grand numéro d'avis et on met le numéro suivant à celui-là*/
		    if (result.next()) {
		    	noAvis =result.getInt(1)+1;
		    }

		    
	        //fermer la connexion avec la base de données
	        result.close();
	        state.close();
		}catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
		
		//Action del = new ActionAvis("DELETE FROM `Avis`;", "Avis");		
		String commande = "INSERT into Avis VALUES ('" +noAvis+"','"+destinataire+"','" +emetteur+ "','" +message+ "','" +note+"');";
		
		
		try {
			Action insert = new Action(commande, "Avis");
		} catch (SQLIntegrityConstraintViolationException e) {}
	}
	
}
