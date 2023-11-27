package mavenProject;

import Controller.MainController;
import Model.BadLengthException;

import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAddToDB {
	
	//@BeforeEach
	public void EmptyTables() {
		MainController.EmptyDB("Avis") ;
		MainController.EmptyDB("DemandeAide") ;
		MainController.EmptyDB("Person") ;

	}
		
	/*interface FallibleCode{
		void run() throws Exception;
	}

	private static void assertThrows (FallibleCode code) {
		try {
			code.run();
			throw new RuntimeException("Code should have throw an exception");
		}catch(Exception e) {
			//do nothing, it works
		}
	}*/
	
	@Test
	void testAddUserDB() {
		
		/*add a User*/
		try {
			MainController.NewUser ("identifiant", "abracadabra", "Camus", "Albert", 28, "albert@gmail.com", "0123456789", "Toulouse", "160 allée des sc appliquees", 1);
			assert MainController.ExistsInDB("Person", "identifiant");
			/*Not supposed to catch an Exception*/
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Exception : " + e.getMessage())	;
			assert false ;
		} catch (BadLengthException e) {
			System.out.println("Exception : " + e.getMessage())	;
			assert false ;
		}
		
		/*add a User with an id already taken*/
		try {
			MainController.NewUser ("identifiant", "motdepasse", "Dujardin", "Jean", 40, "dujardin@gmail.com", "0123456789", "Paris", "8 rue des lilas", 0);
			assert false ;
		} catch (SQLIntegrityConstraintViolationException e) {
			/*Supposed to catch an SQLException*/
			assert MainController.ExistsInDB("Person", "identifiant");
		} catch (BadLengthException e) {
			System.out.println("Exception : " + e.getMessage())	;
			assert false ;
		}
		
		/*add a User with a password too small*/
		try {
			MainController.NewUser ("identifiant2", "abc", "Careme", "Maurice", 56, "careme@gmail.com", "0123456789", "Bruxelles", "16 rue des oiseaux", 2);
			assert false ;
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Exception : " + e.getMessage())	;
			assert false ;
		} catch (BadLengthException e) {
			/*Supposed to catch a BadLengthException because password is too small*/
			assert (!MainController.ExistsInDB("Person", "identifiant2"));
		}		
		
		
	}
	
	@Test
	void testAddDemandeDB() {
		/*add a Demand*/
		/*try {
			MainController.NewDemande ("Besoin daide 1", "Détails de la demande", "albert", "08/12/2023", "Paris");
			assert MainController.ExistsInDB("DemandeAide", "identifiant");*/
			/*Not supposed to catch an Exception*/
		/*} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Exception : " + e.getMessage())	;
			assert false ;
		} catch (BadLengthException e) {
			System.out.println("Exception : " + e.getMessage())	;
			assert false ;*/
		}
		
	@Test
	void testAddAvisDB() {
		//TODO
	}
	
}
