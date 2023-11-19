package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import Model.* ;

import View.* ;

public class NewUser {	
	/* type : numero attribue
	 Benevole : 0
	 Beneficiaire : 1
	 Valideur : 2
	 */
	
	public NewUser (String idUser, String password, String nom, String prenom, int age, String email, String telephone, String ville, String adresse, int type) throws SQLIntegrityConstraintViolationException, BadLengthException{
		
		/*vérification taille des arguments*/
		if ((nom.length() > 20) || (nom.length()==0)) {
			throw new BadLengthException ("Nom");
		} else if ((prenom.length() > 20) || (prenom.length()==0)) {
			throw new BadLengthException ("Prenom");
		} else if ((email.length() > 50) || (email.length()==0)) {
			throw new BadLengthException ("Email");
		} else if ((telephone.length() > 10) || (telephone.length()==0)) {
			throw new BadLengthException ("Téléphone");
		} else if ((ville.length() > 30) || (ville.length()==0)) {
			throw new BadLengthException ("Ville");
		} else if ((adresse.length() > 100) || (adresse.length()==0)) {
			throw new BadLengthException ("Adresse");
		} else if ((idUser.length() > 20) || (idUser.length()==0)) {
			throw new BadLengthException ("Identifiant");
		} else if ((password.length() > 20) || (password.length()<8)) {
			throw new BadLengthException ("Mot de passe");
		}		
		
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    
		    //insérer la personne dans la database
			String commande = "INSERT into Person VALUES ('" +idUser+"','" +nom+ "','" +prenom+ "','" +age+"','" +type+ "','" +email+"','" +telephone+"','" +ville+"','" +adresse+"','" +0.0+"','" +password+"','" +0+"');";
		    state.executeUpdate(commande);
	        
	        //Print la database
	        ResultSet result = state.executeQuery("SELECT * FROM Person");
	        ResultSetMetaData resultMeta = result.getMetaData();                                     
	        System.out.println("\n****************************************************************************");
	        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
	            System.out.print("  " + resultMeta.getColumnName(i).toUpperCase() + " ");
	        System.out.println("\n****************************************************************************");
	        while(result.next()){
	            for(int i = 1; i <= resultMeta.getColumnCount(); i++)
	                System.out.print("      " + result.getObject(i).toString() + "      ");
	            System.out.println("\n---------------------------------------------------------------------------");
	        }                    
	        //fermer la connexion avec la base de données
	        result.close();
	        state.close();
	        
		} catch (SQLIntegrityConstraintViolationException exc) {
			throw exc;
		} catch (Exception e){
		    e.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
	}
	
}
