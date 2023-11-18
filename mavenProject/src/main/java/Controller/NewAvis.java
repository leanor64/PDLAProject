package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class NewAvis {
	
	public NewAvis(String message, String destinataire, String emetteur, int note) throws BadLengthException, BadConnectionException{
		
		/*vérification taille des arguments*/
		if (message.length() > 300) {
			throw new BadLengthException ("Message");
		} else if (destinataire.length() > 20) {
			throw new BadLengthException ("Destinataire");
		} else if (emetteur.length() > 50) {
			throw new BadLengthException ("Emetteur");
		} 
		
		int noAvis = 1 ;
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    

		    /*On vérifie si le destinataire existe bien dans la base de données*/
		    ResultSet result2 = state.executeQuery("SELECT * FROM Person WHERE userName = '"+destinataire+"';");
		    if (!(result2.next())) {
		    	throw (new BadConnectionException("Destinataire inexistant"));
		    } else {
		    	 //mise à jour de la note du destinataire
			    int oldNbAvis = result2.getInt(12) ;
			    int newNote = note ;
			    if (!(oldNbAvis == 0)) {
			    	int oldNote = result2.getInt(10) ;
					newNote = (oldNote * oldNbAvis + note)/(oldNbAvis+1);
			    }
			    String commande2 = "UPDATE Person SET nbAvis = '" +(oldNbAvis+1)+ "', note = '"+newNote+"' WHERE userName = '"+destinataire+"' ;";
			    state.executeUpdate(commande2);
		    }

		    //réaliser la commande dans la database et récupérer le nombre de lignes trouvées
		    ResultSet result = state.executeQuery("SELECT MAX(num) FROM Avis ;");
	        
		    /*S'il y a des avis dans la table, on récupère le plus grand numéro d'avis et on met le numéro suivant à celui-là*/
		    if (result.next()) {
		    	noAvis =result.getInt(1)+1;
		    }
		    
			//insérer l'avis dans la database
			String commande = "INSERT into Avis VALUES ('" +noAvis+"','"+destinataire+"','" +emetteur+ "','" +message+ "','" +note+"');";
		    state.executeUpdate(commande);
		    
		   

		    
		    
		    
		    //Print la database Avis
	        ResultSet res = state.executeQuery("SELECT * FROM Avis");
	        ResultSetMetaData resultMeta = res.getMetaData();                                     
	        System.out.println("\n****************************************************************************");
	        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
	            System.out.print("  " + resultMeta.getColumnName(i).toUpperCase() + " ");
	        System.out.println("\n****************************************************************************");
	        while(res.next()){
	            for(int i = 1; i <= resultMeta.getColumnCount(); i++)
	                System.out.print("      " + res.getObject(i).toString() + "      ");
	            System.out.println("\n---------------------------------------------------------------------------");
	        }                
		   
	        //fermer la connexion avec la base de données
	        result.close();
	        state.close();
	        
		} catch (BadConnectionException exc1) {
			throw exc1 ;
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
	}		
		
}
