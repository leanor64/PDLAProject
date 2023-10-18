package Controller;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	static Connection conn;
    static Statement state;
    static ResultSet result;
    static ResultSetMetaData resultMeta;
    static Object[][] donn;
    static String[] champs;
    static Object[] val;
    static String tableBDD = "Person";
	
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {

            	String BDD = "projet_gei_014";
            	String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/" + BDD;
            	String user = "projet_gei_014";
            	String passwd = "Rei4wie9";
            	
            	try {
            	    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
            	    Statement state = conn.createStatement();
            	    
            	    //inserer ligne dans database
                    state.executeUpdate("INSERT into Person VALUES ('jules', 'Careme', 'Maurice', 18, 0, 'mauricecar@gmail.com', '0123456789', 'Paris', 'Chemin du thym', 5);");
                    state.executeUpdate("INSERT into Person VALUES ('pierrick', 'Careme', 'Maurice', 18, 0, 'mauricecar@gmail.com', '0123456789', 'Paris', 'Chemin du thym', 5);");
                    
                    //Supprimer toute la table
                    state.executeUpdate("DELETE FROM `Person`");

                    //Print la database
                    ResultSet result = state.executeQuery("SELECT * FROM Person order by userName");
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
        });
    }
	
	
	
}