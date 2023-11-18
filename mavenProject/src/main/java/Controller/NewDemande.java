package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import Model.Beneficiaire;
import Model.Demande;

public class NewDemande {

	public NewDemande(String title, String explication, String demandeur, String etat, String jour, String ville) throws BadLengthException{
		
		/*vérification taille des arguments*/
		if (title.length() > 50) {
			throw new BadLengthException ("Titre");
		} else if (explication.length() > 300) {
			throw new BadLengthException ("Détails");
		} else if (demandeur.length() > 20) {
			throw new BadLengthException ("Beneficiaire");
		} else if (etat.length() > 10) {
			throw new BadLengthException ("Etat");
		} else if (jour.length() > 10) {
			throw new BadLengthException ("Date");
		} else if (ville.length() > 30) {
			throw new BadLengthException ("Ville");
		}  
		
		int noDemande = 1 ;
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();

		    //réaliser la commande dans la database et récupérer le nombre de lignes trouvées
		    ResultSet result = state.executeQuery("SELECT MAX(num) FROM DemandeAide ;");
	        
		    /*S'il y a des avis dans la table, on récupère le plus grand numéro d'avis et on met le numéro suivant à celui-là*/
		    if (result.next()) {
		    	noDemande =result.getInt(1)+1;
		    }
		    
			//insérer l'avis dans la database
			String commande = "INSERT into DemandeAide VALUES ('"+noDemande+"','"+title+"','"+explication+"','"+demandeur+"','"+etat+"','"+jour+"','"+ville+"');";
		    state.executeUpdate(commande);
		    
		    
		    //Print la database
	        ResultSet res = state.executeQuery("SELECT * FROM DemandeAide");
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
	        
	        
		}catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
	}	
	
}
