package Controller;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import Model.Avis;
import Model.User;


import View.FormulaireInscription;

public class Action {

	static Connection conn;
    static Statement state;
    static ResultSet result;
    static ResultSetMetaData resultMeta;
    static Object[][] donn;
    static String[] champs;
    static Object[] val;
    
    
	public Action(String commande, String tableBDD) throws SQLIntegrityConstraintViolationException{
	   	String BDD = "projet_gei_014";
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/" + BDD;
		String user = "projet_gei_014";
		String passwd = "Rei4wie9";
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    
		    //réaliser la commande dans la database
		    state.executeUpdate(commande);
	        
	        //Print la database
	        ResultSet result = state.executeQuery("SELECT * FROM "+tableBDD);
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
		
	public static void main(String[] args) {
		
		try {
			Action del = new Action("DELETE FROM `Person`;", "Person");
			NewUser u1 = new NewUser ("maurice", "abracadabra", "Camus", "Albert", 28, "blabla@gmail.com", "0123456789", "Toulouse", "8 allée des sc appliquees", 14, 1);
			NewUser u = new NewUser ("albert", "abracadabra", "Camus", "Albert", 28, "blabla@gmail.com", "0123456789", "Toulouse", "8 allée des sc appliquees", 14, 0);
			NewUser v = new NewUser ("pat", "abracadabra", "Camus", "Albert", 28, "blabla@gmail.com", "0123456789", "Toulouse", "8 allée des sc appliquees", 14, 2);
		} catch (Exception exc) {
			System.out.println ("erreur id");
		}
		
		
		
		try {
			NewAvis a = new NewAvis ("Maurice il était top", "maurice", "albert", 3);
			NewAvis b = new NewAvis ("Jean il était top", "maurice", "albert", 3);
			NewAvis c = new NewAvis ("Patoche il était top", "maurice", "albert", 3);
			NewAvis d = new NewAvis ("AA il était top", "maurice", "albert", 3);

		} catch (Exception x) {
			System.out.println("Erreur note");
		}
		
		
	}

	
}

	