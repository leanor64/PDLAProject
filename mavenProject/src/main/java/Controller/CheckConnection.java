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
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014" ;
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    
		    //réaliser la commande dans la database et récupérer le nombre de lignes trouvées
		    ResultSet result = state.executeQuery("SELECT * FROM Person WHERE userName = '"+id+"' AND passwrd = '"+password+"';");

		    /*Si la table est vide, donc s'il n'existe pas de User avec ce mdp et cet id*/
		    if (!(result.next())) {
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

}
