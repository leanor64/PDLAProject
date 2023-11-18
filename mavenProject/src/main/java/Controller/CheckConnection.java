package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import Model.Avis;
import Model.User;

public class CheckConnection {
	public CheckConnection (String id, String password) throws BadConnectionException {
		//"SELECT userName FROM Person WHERE userName = '"+id+"' AND passwrd = '"+password+"';";
		String BDD = "projet_gei_014";
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/" + BDD;
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    
		    //réaliser la commande dans la database et récupérer le nombre de lignes trouvées
		    ResultSet result = state.executeQuery("SELECT * FROM Person WHERE userName = '"+id+"' AND passwrd = '"+password+"';");

		    /*Si la table est vide, donc s'il n'existe pas de User avec ce mdp et cet id*/
		    if (!(result.next())) {
		    	System.out.println("table vide donc erreur");
		    	throw (new BadConnectionException("MDP ou identifiant incorrect"));
		    }
		    
	        //fermer la connexion avec la base de données
	        result.close();
	        state.close();
		}catch (BadConnectionException e) {
			throw e ;
	        
		}catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
	}
		
	public static void main(String[] args) {
		
		try {
			Action del = new Action("DELETE FROM `Person`;", "Person");	//A supprimer	
			NewUser u1 = new NewUser ("maurice", "abracadabra", "Camus", "Albert", 28, "blabla@gmail.com", "0123456789", "Toulouse", "8 allée des sc appliquees", 14, 1);
			NewUser u = new NewUser ("albert", "abracadabra", "Camus", "Albert", 28, "blabla@gmail.com", "0123456789", "Toulouse", "8 allée des sc appliquees", 14, 0);
			NewUser v = new NewUser ("pat", "abracadabra", "Camus", "Albert", 28, "blabla@gmail.com", "0123456789", "Toulouse", "8 allée des sc appliquees", 14, 2);
		} catch (Exception exc) {
			System.out.println ("erreur id");
		}
		
		try {
			new CheckConnection("act", "abracadabra");
		} catch (Exception e1) {
			System.out.println("boulette n°1");
		}
		
		try {
			new CheckConnection("act", "abracadabre");
		} catch (Exception e2) {
			System.out.println("boulette n°2");
		}
	
	}
}
