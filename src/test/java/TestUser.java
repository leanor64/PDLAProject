import Controller.MainController;
import Model.*;

import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.jupiter.api.*;


public class TestUser {

	@BeforeEach
	public void EmptyDB() {
		MainController.EmptyDB("Avis");
		MainController.EmptyDB("DemandeAide");
		MainController.EmptyDB("Person");
	}

	@Test
	void testCheckConnection() throws SQLIntegrityConstraintViolationException, BadLengthException {
		
		/*Try to connect with unexisting id and password -> supposed to be false*/
		assert !MainController.ExistsInDB("Person", "idInexistant");
		assert !MainController.CheckConnection("idInexistant", "badPassword");
		
		try{ 
			MainController.NewUser ("identifiant", "motdepasse", "Dujardin", "Jean", 40, "dujardin@gmail.com", "0123456789", "Paris", "8 rue des lilas", 0);
		} catch (Exception e) {
			System.out.println("Erreur add User");
		}

		/*Try to connect with good id and password -> supposed to be true*/
		assert MainController.CheckConnection("identifiant", "motdepasse");

		/*Try to connect with good id but bad password -> supposed to be false*/
		assert !MainController.CheckConnection("identifiant", "badmotdepasse");		

	}
	
	@Test
	void testGetTypeUser() throws SQLIntegrityConstraintViolationException, BadLengthException, UnexistingUserException {
		
		try{ 
			MainController.NewUser ("idBenevole", "motdepasse", "Dujardin", "Jean", 40, "dujardin@gmail.com", "0123456789", "Paris", "8 rue des lilas", 0);
			MainController.NewUser ("idBeneficiaire", "youpiyoupi", "Verlaine", "Paul", 17, "verlaine@gmail.com", "0123456789", "Assas", "9 rue des poissons rouges", 1);
			MainController.NewUser ("idValideur", "abracadabra", "Camus", "Albert", 28, "albert@gmail.com", "0123456789", "Toulouse", "160 allée des sc appliquees", 2);
		} catch (Exception e) {
			System.out.println("Erreur add User");
		}


		/*Try to get the type of an unexisting User -> supposed to throw an UnexistingUserException*/
		try {
			assert (!MainController.ExistsInDB("Person", "idInexistant"));
			int n = MainController.getTypeOfUser("idInexistant");
			assert false ;
		} catch (UnexistingUserException e) {
			//OK
		}
	
		/*Try to get the type of a benevole -> supposed to return 0*/
	 	assert (0 == MainController.getTypeOfUser("idBenevole"));

		/*Try to get the type of a beneficiary -> supposed to return 1*/
	 	assert (1 == MainController.getTypeOfUser("idBeneficiaire"));

		/*Try to get the type of a valider -> supposed to return 2*/
	 	assert (2 == MainController.getTypeOfUser("idValideur"));
	}

	
	
}