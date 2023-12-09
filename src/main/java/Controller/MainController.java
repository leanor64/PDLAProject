package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.*;

import Model.BadLengthException;
import Model.StatutDemande;
import Model.UnexistingAvisException;
import Model.UnexistingDemandException;
import Model.UnexistingInfoException;
import Model.UnexistingUserException;
import View.ViewMain;

public class MainController {
      
	public MainController(){}
	
	public static void PrintDB (String table) {
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		try {
			Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    
	        ResultSet res = state.executeQuery("SELECT * FROM "+ table);
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
	        res.close();
		} catch (Exception e) {
			e.printStackTrace();
		    System.out.println("Erreur");
	    	System.exit(0);
		}
		
		
		
	}
	
	public static boolean ExistsInDB (String table, String primaryKey){
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		boolean res = false ; 
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    String commande = "";

		    if (table.equals("Avis")){
			    commande = "SELECT COUNT(*) FROM Avis WHERE num = "+primaryKey+";";
		    } else if (table.equals("Person")){
			    commande = "SELECT COUNT(*) FROM Person WHERE userName = '"+primaryKey+"';";
		    } else if (table.equals("DemandeAide")){
			    commande = "SELECT COUNT(*) FROM DemandeAide WHERE num = "+primaryKey+";";
		    }

		    ResultSet result = state.executeQuery(commande);
		    if (result.next() && result.getInt(1) == 1) {
		    	res = true ; 
		    } 
		    result.close();
		    state.close();

		    
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
		return res ;
	}
	
	public static void EmptyDB (String table)  {
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    String commande = "TRUNCATE "+ table +";";
		    state.executeUpdate(commande);
		    state.close();
        
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
	}
	
	public static void NewUser (String idUser, String password, String nom, String prenom, int age, String email, String telephone, String ville, String adresse, int type) throws SQLIntegrityConstraintViolationException, BadLengthException{
		/* Benevole : 0 | Beneficiaire : 1 | Valideur : 2 */
		
		/*vérification taille des arguments*/
		if ((nom.length() > 20) || (nom.isEmpty())) {
			throw new BadLengthException ("Nom");
		} else if ((prenom.length() > 20) || (prenom.isEmpty())) {
			throw new BadLengthException ("Prenom");
		} else if ((email.length() > 50) || (email.isEmpty())) {
			throw new BadLengthException ("Email");
		} else if ((telephone.length() > 10) || (telephone.isEmpty())) {
			throw new BadLengthException ("Téléphone");
		} else if ((ville.length() > 30) || (ville.isEmpty())) {
			throw new BadLengthException ("Ville");
		} else if ((adresse.length() > 100) || (adresse.isEmpty())) {
			throw new BadLengthException ("Adresse");
		} else if ((idUser.length() > 20) || (idUser.isEmpty())) {
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
	        
	        //fermer la connexion avec la base de données
	        state.close();
	        
		} catch (SQLIntegrityConstraintViolationException exc) {
			throw exc;
		} catch (Exception e){
		    e.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
	}
	
	public static void NewAvis(String message, String destinataire, String emetteur, int note) throws BadLengthException, UnexistingUserException{
		int noAvis = 1 ;
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";		
		
		/*vérification taille des arguments*/
		if (message.length() > 300) {
			throw new BadLengthException ("Message");
		} else if ((destinataire.length() > 20) || (destinataire.length()==0)){
			throw new BadLengthException ("Destinataire");
		} else if ((emetteur.length() > 50) || (emetteur.length()==0)) {
			throw new BadLengthException ("Emetteur");
		}	
		
		/*Vérification de l'existence de l'emetteur et du destinataire"*/
		if (!ExistsInDB("Person", destinataire)) {
	    	throw (new UnexistingUserException("Destinataire inexistant"));
	    } else if (!ExistsInDB("Person", emetteur)) {
	    	throw (new UnexistingUserException("Emetteur inexistant"));
	    } 
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    

	    	 //mise à jour de la note du destinataire
		    ResultSet result2 = state.executeQuery("SELECT * FROM Person WHERE userName = '"+destinataire+"';");
		    result2.next();
		    int oldNbAvis = result2.getInt(12) ;
		    float newNote = note ;
		    if (!(oldNbAvis == 0)) {
		    	float oldNote = result2.getFloat(10) ;
				newNote = (oldNote * oldNbAvis + note)/(oldNbAvis+1);
		    }
		    String commande2 = "UPDATE Person SET nbAvis = '" +(oldNbAvis+1)+ "', note = '"+newNote+"' WHERE userName = '"+destinataire+"' ;";
		    state.executeUpdate(commande2);


		    //réaliser la commande dans la database et récupérer le nombre de lignes trouvées
		    ResultSet result = state.executeQuery("SELECT MAX(num) FROM Avis ;");
	        
		    /*S'il y a des avis dans la table, on récupère le plus grand numéro d'avis et on met le numéro suivant à celui-là*/
		    if (result.next()) {
		    	noAvis =result.getInt(1)+1;
		    }

		    
			//insérer l'avis dans la database
			String commande = "INSERT into Avis VALUES ('" +noAvis+"','"+destinataire+"','" +emetteur+ "','" +message+ "','" +note+"');";
			state.executeUpdate(commande);
		    	
	        result.close();
	        result2.close();
	        state.close();
	        
	        
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
	}
	
	public static void NewDemande(String title, String explication, String demandeur, String jour, String ville) throws BadLengthException, UnexistingUserException{
		
		int noDemande = 1 ; 
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		/*vérification taille des arguments*/
		if ((title.length() > 50) || (title.length()==0)){
			throw new BadLengthException ("Titre");
		} else if ((explication.length() > 300) || (explication.length()==0)){
			throw new BadLengthException ("Détails");
		} else if ((demandeur.length() > 20) || (demandeur.length()==0)){
			throw new BadLengthException ("Beneficiaire");
		} else if ((jour.length() > 10) || (jour.length()==0)) {
			throw new BadLengthException ("Date");
		} else if ((ville.length() > 30) || (ville.length()==0)){
			throw new BadLengthException ("Ville");
		}  
		
		/*Vérification de l'existence du demandeur*/
		if (!ExistsInDB("Person", demandeur)) {
	    	throw (new UnexistingUserException("Destinataire inexistant"));
	    } 
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    
		  //récupérer le num max de demande
		    ResultSet result = state.executeQuery("SELECT MAX(num) FROM DemandeAide ;");
	        
		    /*S'il y a des avis dans la table, on récupère le plus grand numéro d'avis et on met le numéro suivant à celui-là*/
		    if (result.next()) {
		    	noDemande =result.getInt(1)+1;
		    }
		    
		    
			//insérer l'avis dans la database
			String commande = "INSERT into DemandeAide VALUES ('"+title+"','"+explication+"','"+demandeur+"','EN_ATTENTE','"+jour+"','"+ville+"','',"+noDemande+");";
		    state.executeUpdate(commande);
		  
	        state.close();
	        
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
	}	
	
	public static boolean CheckConnection (String id, String password) {
		/*Pour vérifier que l'identifiant et le mot de passe rentrés existent bien dans la database*/
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014" ;
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		boolean goodId = true ;
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    
		    //réaliser la commande dans la database et récupérer le nombre de lignes trouvées
		    ResultSet result = state.executeQuery("SELECT * FROM Person WHERE userName = '"+id+"' AND passwrd = '"+password+"';");

		    /*Si la table est vide, donc s'il n'existe pas de User avec ce mdp et cet id*/
		    if (!(result.next())) {
		    	goodId = false ;
		    }
		    
	        //fermer la connexion avec la base de données
	        result.close();
	        state.close();
		  
		}catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
		return goodId ;
	}
	
	public static int getTypeOfUser (String idUser) throws UnexistingUserException {
		int type = 4; //initialisation obligatoire
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		/*Vérification de l'existence du User*/
		if (!ExistsInDB("Person", idUser)) {
	    	throw (new UnexistingUserException("User inexistant"));
	    } 
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    

		    /*On récupère la ligne correspondant à idUser dans la base de données pour obtenir son type (0, 1 ou 2)*/
		    ResultSet res = state.executeQuery("SELECT * FROM Person WHERE userName = '"+idUser+"';");
		    if (res.next()) {
		    	type = res.getInt(5) ;
		    }
		    
		  //fermer la connexion avec la base de données
	        res.close();
	        state.close();
		
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
		return type ;
	}
	
	public static String getInfoOfDemand(int noDemande, String info) throws UnexistingInfoException, UnexistingDemandException {
		String information = "" ;
		int column ;
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		/*Vérification de l'existence de la demande*/
		if (!ExistsInDB("DemandeAide", Integer.toString(noDemande))) {
	    	throw (new UnexistingDemandException("Demande inexistante"));
	    } 
		
	
		/*Vérification de l'existence de l'info*/
		if (info.equals("titre")) {
			column = 1;
		} else if (info.equals("explication")) {
			column = 2;
		} else if (info.equals("beneficiaire")) {
			column = 3;
		} else if (info.equals("statut")) {
			column = 4;
		} else if (info.equals("jour")) {
			column = 5;
		} else if (info.equals("ville")) {
			column = 6;
		} else if (info.equals("benevole")) {
			column = 7;
		} else {
			throw new UnexistingInfoException(info + "n'est pas un attribut d'une demande");
		}
		
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();

		    /*On récupère la ligne correspondant au numero de la demande dans la base de données*/
		    ResultSet res = state.executeQuery("SELECT * FROM DemandeAide WHERE num = '"+noDemande+"';");
		    if (res.next()) {
		    information = res.getString(column) ;
		    }
		    
		    
		  //fermer la connexion avec la base de données
	        res.close();
	        state.close();
		
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
		return information ;
		
	}
	 	
	public static String getInfoOfUser(String idUser, String info) throws UnexistingInfoException, UnexistingUserException {
		String information = "" ;
		int column ;
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		/*Vérification de l'existence du User*/
		if (!ExistsInDB("Person", idUser)){
	    	throw (new UnexistingUserException("User inexistant"));
	    } 
		
		
		/*Vérification de l'existance de l'information*/
		if (info.equals("nom")) {
			column = 2;
		} else if (info.equals("prenom")) {
			column = 3;
		} else if (info.equals("age")) {
			column = 4;
		} else if (info.equals("email")) {
			column = 6;
		} else if (info.equals("telephone")) {
			column = 7;
		} else if (info.equals("ville")) {
			column = 8;
		} else if (info.equals("adresse")) {
			column = 9;
		} else if (info.equals("note")) {
			column = 10;
		} else if (info.equals("mot de passe")) {
			column = 11;
		} else if (info.equals("nbAvis")) {
			column = 12;
		} else {
			throw new UnexistingInfoException(info + " n'est pas un attribut d'une personne");
		}
		
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();

		    /*On récupère la ligne correspondant à l'id dans la base de données*/
		    ResultSet res = state.executeQuery("SELECT * FROM Person WHERE userName = '"+idUser+"';");
		    if (res.next()) {
			information = res.getString(column) ;
		    
		    		    
		  //fermer la connexion avec la base de données
	        res.close();
	        state.close();
		    }
		
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
		return information ;
		
	}
	
	public static String getInfoOfAvis(int noAvis, String info) throws UnexistingInfoException, UnexistingAvisException {
		String information = "" ;
		int column ;
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		/*Vérification de l'existence de l'avis*/
		if (!ExistsInDB("Avis", Integer.toString(noAvis))) {
	    	throw (new UnexistingAvisException("Avis inexistant"));
	    }
		
		/*Vérification de l'existence de l'info*/
		if (info.equals("destinataire")) {
			column = 2;
		} else if (info.equals("emetteur")) {
			column = 3;
		} else if (info.equals("commentaire")) {
			column = 4;
		} else if (info.equals("note")) {
			column = 5;
		} else {
			throw new UnexistingInfoException(info + "n'est pas un attribut d'une personne");
		}
		
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();

		    /*On récupère la ligne correspondant au title dans la base de données*/
		    ResultSet res = state.executeQuery("SELECT * FROM Avis WHERE num = '"+noAvis+"';");
		    if (res.next()) {
			information = res.getString(column) ;
		    
		  //fermer la connexion avec la base de données
	        res.close();
	        state.close();
		    }
		
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
		return information ;
		
	}

	public static void setStatusOfDemand(int noDemande, StatutDemande nvStatut) throws UnexistingDemandException {
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		/*Vérification de l'existence de la demande*/
		if (!ExistsInDB("DemandeAide", Integer.toString(noDemande))) {
	    	throw (new UnexistingDemandException("Demande inexistante"));
	    } 
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    
	    	 //Editer l'etat de la demande dans la BDD
		    String commande = "UPDATE DemandeAide SET state = '"+nvStatut+"' WHERE num = '"+noDemande+"' ;";
	    	state.executeUpdate(commande);
		    
		    
		  //fermer la connexion avec la base de données
	        state.close();
		
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
	}

	public static void setBenevoleOfDemand(int noDemande, String idBenevole) throws UnexistingDemandException, UnexistingUserException {
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		/*Vérification de l'existence de la demande et du benevole*/
		if (!ExistsInDB("DemandeAide", Integer.toString(noDemande))) {
	    	throw (new UnexistingDemandException("Demande inexistante"));
	    } else if (!ExistsInDB("Person", idBenevole)) {
	    	throw (new UnexistingUserException("Benevole inexistant"));
	    }
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();

	    	 //Editer le bénévole et le statut de la demande dans la BDD
		    String commande = "UPDATE DemandeAide SET benevole = '"+idBenevole+"' WHERE num = '"+noDemande+"' ;";
	    	state.executeUpdate(commande);
	    	String commande2 = "UPDATE DemandeAide SET state = '"+StatutDemande.ACCEPTEE+"' WHERE num = '"+noDemande+"' ;";
	    	state.executeUpdate(commande2);
		    
		  //fermer la connexion avec la base de données
	        state.close();
		
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
	}
	
	public static void setInfoOfUser(String idUser, String info, String nvValeur) throws UnexistingInfoException, UnexistingUserException, BadLengthException {
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		/*Vérification de l'existence du User*/
		if (!ExistsInDB("Person", idUser)) {
	    	throw (new UnexistingUserException("Benevole inexistant"));
	    } 
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    
		    String column ;
		    String commande ;
		    if (info.equals("age")) {
			    commande = "UPDATE Person SET age = '"+Integer.parseInt(nvValeur)+"' WHERE userName = '"+idUser+"' ;";
		    } else {
		    	
			    if (info.equals("nom")) {
					column = "lastName";
					if ((nvValeur.length() > 20) || nvValeur.isEmpty()) {
						throw new BadLengthException("Nom");
					}
				} else if (info.equals("prenom")) {
					column = "firstName";
					if ((nvValeur.length() > 20) || nvValeur.isEmpty()) {
						throw new BadLengthException("Prenom");
					}
				} else if (info.equals("email")) {
					column = "email";
					if ((nvValeur.length() > 50) || nvValeur.isEmpty()) {
						throw new BadLengthException("Email");
					}
				} else if (info.equals("telephone")) {
					column = "phone";
					if ((nvValeur.length() > 10) || nvValeur.isEmpty()) {
						throw new BadLengthException("Téléphone");
					}
				} else if (info.equals("ville")) {
					column = "city";
					if ((nvValeur.length() > 30) || nvValeur.isEmpty()) {
						throw new BadLengthException("Ville");
					}
				} else if (info.equals("adresse")) {
					column = "adress";
					if ((nvValeur.length() > 100) || nvValeur.isEmpty()) {
						throw new BadLengthException("Adresse");
					}
				} else if (info.equals("mot de passe")) {
					column = "passwrd";
					if ((nvValeur.length() > 50) || (nvValeur.length()<8)) {
						throw new BadLengthException("Mot de passe");
					}
				} else {
					throw new UnexistingInfoException(info + " n'est pas un attribut d'une personne");
				}
			    
			    commande = "UPDATE Person SET "+column+" = '"+nvValeur+"' WHERE userName = '"+idUser+"' ;";

		    }
		    
	    	 //Editer la bonne information du User dans la BDD
	    	state.executeUpdate(commande);
	    			    
		  //fermer la connexion avec la base de données
	        state.close();
		

		} catch (java.sql.SQLException exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		
	}
	
	public static ArrayList<Integer> getDemandsofStatus(StatutDemande statut){
		ArrayList<Integer> listDemands = new ArrayList<Integer>() ;
		
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    
		    /*On récupère dans la BDD les demandes dont le statut correspond à celui cherché*/
		    ResultSet res = state.executeQuery("SELECT * FROM DemandeAide WHERE state = '"+statut+"';");
		    while (res.next()) {
		    	 listDemands.add(res.getInt(8)); 
		    } 
		    
		  //fermer la connexion avec la base de données
	        res.close();
	        state.close();
		
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
	
		return listDemands ;
	}

	public static ArrayList<Integer> getDemandsOfBeneficiaire (String benef) throws UnexistingUserException{
		ArrayList<Integer> listDemands = new ArrayList<Integer>() ;
		
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		/*Vérification de l'existence du beneficiaire*/
		if (!ExistsInDB("Person", benef)) {
	    	throw (new UnexistingUserException("Benevole inexistant"));
	    } 
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    
		    /*On récupère dans la BDD les demandes dont le bénéficiaire correspond à celui cherché*/
		    ResultSet res = state.executeQuery("SELECT * FROM DemandeAide WHERE beneficiaire = '"+benef+"';");
		    while (res.next()) {
		    	 listDemands.add(res.getInt(8)); 
		    }
		    
		  //fermer la connexion avec la base de données
	        res.close();
	        state.close();
		
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
	
		return listDemands ;
	}	
	
	public static ArrayList<Integer> getListOfAvis(String idBenevole) throws UnexistingUserException{
		ArrayList<Integer> listAvis = new ArrayList<Integer>() ;
		
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_014";
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		/*Vérification de l'existence du beneficiaire*/
		if (!ExistsInDB("Person", idBenevole)) {
	    	throw (new UnexistingUserException("Benevole inexistant"));
	    } 
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    
		    /*On récupère dans la BDD les demandes dont le statut correspond à celui cherché*/
		    ResultSet res = state.executeQuery("SELECT * FROM Avis WHERE destinataire = '"+idBenevole+"';");
		    
		    while (res.next()) {
		    	 listAvis.add(res.getInt(1));
			}
		    
		  //fermer la connexion avec la base de données
	        res.close();
	        state.close();
		
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
	
		return listAvis ;
			
	}
	
	public static void main(String[] args) {

		ViewMain main = new ViewMain();
		main.setVisible(true);
		
	}

	
}

	