package Controller;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import Model.User;
import Model.Avis;

import View.FormulaireInscription;

public class ActionAvis {

	static Connection conn;
    static Statement state;
    static ResultSet result;
    static ResultSetMetaData resultMeta;
    static Object[][] donn;
    static String[] champs;
    static Object[] val;
    static String tableBDD = "Avis";
	
	public ActionAvis(String commande) {
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
	        ResultSet result = state.executeQuery("SELECT * FROM Avis order by num");
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
		} catch (Exception e){
		    e.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
	}
	
	public static void main(String[] args) {
		User d = new User ("hugo", "abracadabra", "Camus", "Albert", 28, "blabla@gmail.com", "0123456789", "Toulouse", "8 allée des sc appliquees", 14);
		User e = new User ("patrick", "abracadabra", "Camus", "Albert", 28, "blabla@gmail.com", "0123456789", "Toulouse", "8 allée des sc appliquees", 14);
		
		try {
			NewUser u = new NewUser (d);
			NewUser v = new NewUser (e);
			
			//2 lignes pour ajouter un avis dans la base de données
			Avis av = new Avis ("Maurice il était top", d, e, 1);
			NewAvis a = new NewAvis (av, av.getNoAvis());
			
		} catch (Exception x) {
			System.out.println("Erreur note");
		}
	}
}

	