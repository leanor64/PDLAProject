import Controller.MainController;
import Model.BadLengthException;
import Model.UnexistingUserException;

import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.jupiter.api.*;

public class TestAddToDB {
	
	@BeforeEach
	public void EmptyDB() {
		MainController.EmptyDB("Avis");
		MainController.EmptyDB("DemandeAide");
		MainController.EmptyDB("Person");
	}
	
	@Test
	void testAddUserDB() throws SQLIntegrityConstraintViolationException, BadLengthException {
				
		/*add a User -> not supposed to throw an exception*/
		MainController.NewUser ("identifiant", "abracadabra", "Camus", "Albert", 28, "albert@gmail.com", "0123456789", "Toulouse", "160 allée des sc appliquees", 1);
		assert MainController.ExistsInDB("Person", "identifiant");
		
		/*add a second User -> not supposed to throw an exception*/
		MainController.NewUser ("identifiant2", "youpiyoupi", "Verlaine", "Paul", 17, "verlaine@gmail.com", "0123456789", "Assas", "9 rue des poissons rouges", 2);
		assert MainController.ExistsInDB("Person", "identifiant2");
		
		/*add a User with an id already taken -> supposed to catch an SQLException*/
		try {
			MainController.NewUser ("identifiant", "motdepasse", "Dujardin", "Jean", 40, "dujardin@gmail.com", "0123456789", "Paris", "8 rue des lilas", 0);
			assert false ;
		} catch (SQLIntegrityConstraintViolationException e) {
			assert MainController.ExistsInDB("Person", "identifiant");
		}
		
		/*add a User with a password too small -> supposed to catch a BadLengthException*/
		try {
			MainController.NewUser ("identifiant3", "abc", "Careme", "Maurice", 56, "careme@gmail.com", "0123456789", "Bruxelles", "16 rue des oiseaux", 2);
			assert false ;
		} catch (BadLengthException e) {
			assert (!MainController.ExistsInDB("Person", "identifiant3"));
		}		
		
	}
	
	@Test
	void testAddDemandeDB() throws BadLengthException, UnexistingUserException{
		
		try {
			MainController.NewUser ("identifiant", "abracadabra", "Camus", "Albert", 28, "albert@gmail.com", "0123456789", "Toulouse", "160 allée des sc appliquees", 1);
			MainController.NewUser ("identifiant2", "youpiyoupi", "Verlaine", "Paul", 17, "verlaine@gmail.com", "0123456789", "Assas", "9 rue des poissons rouges", 2);
		} catch (Exception e) {
			System.out.println("Erreur add User");
		}

		/*add a Demand -> not supposed to throw an exception*/
		MainController.NewDemande ("Besoin daide 1", "Détails de la demande", "identifiant", "08/12/2023", "Paris");
		assert MainController.ExistsInDB("DemandeAide", "1");
		
		/*add a second Demand, the num (primary_key) is supposed to be 2 -> not supposed to throw an SQLException*/
		MainController.NewDemande ("Besoin daide 2", "Détails de la demande 2", "identifiant", "08/12/2023", "Paris");
		assert MainController.ExistsInDB("DemandeAide", "2");
		
		/*add a Demand with an unexisting user (primary_key = 3) -> supposed to catch an UnexistingUserException*/
		try {
			assert (!MainController.ExistsInDB("Person", "idInexistant"));
			MainController.NewDemande ("Besoin daide 3", "Détails de la demande 3", "idInexistant", "08/12/2023", "Paris");
			assert false ;
		} catch (UnexistingUserException e) {
			assert (!MainController.ExistsInDB("DemandeAide", "3"));
		}
		
		/*add a Demand with a date too long (primary_key = 3) -> supposed to catch an BadLengthException*/
		try {
			assert (!MainController.ExistsInDB("DemandeAide", "3"));
			MainController.NewDemande ("Besoin daide 3", "Détails de la demande 3", "identifiant", "078/12/2023", "Paris");
			assert false ;
		} catch (BadLengthException e) {
			assert (!MainController.ExistsInDB("DemandeAide", "3"));
		}
		
	}		
		
	@Test
	void testAddAvisDB() throws BadLengthException, UnexistingUserException {

		try {
			MainController.NewUser ("identifiant", "abracadabra", "Camus", "Albert", 28, "albert@gmail.com", "0123456789", "Toulouse", "160 allée des sc appliquees", 1);
			MainController.NewUser ("identifiant2", "youpiyoupi", "Verlaine", "Paul", 17, "verlaine@gmail.com", "0123456789", "Assas", "9 rue des poissons rouges", 2);
		} catch (Exception e) {
			System.out.println("Erreur add User");
		}
		
		/*add an Avis -> not supposed to throw an exception*/
		MainController.NewAvis ("Détails avis 1", "identifiant", "identifiant2", 5);
		assert MainController.ExistsInDB("Avis", "1");
		
		/*add a second Avis -> not supposed to throw an exception*/
		MainController.NewAvis ("Détails de avis 2", "identifiant", "identifiant2", 3);
		assert MainController.ExistsInDB("Avis", "2");
		
		/*add an Avis with an unexisting benevole (primary_key = 3) -> supposed to catch an UnexistingUserException*/
		try {
			assert (!MainController.ExistsInDB("Person", "idInexistant"));
			MainController.NewAvis ("Détails de avis 3", "idInexistant", "identifiant2", 1);
			assert false ;
		} catch (UnexistingUserException e) {
			assert (!MainController.ExistsInDB("Avis", "3"));
		}
				
		/*add an Avis with an unexisting beneficiaire (primary_key = 3) -> supposed to catch an UnexistingUserException*/
		try {
			assert (!MainController.ExistsInDB("Person", "idInexistant"));
			MainController.NewAvis ("Détails de l'avis 3", "identifiant2", "idInexistant", 1);
			assert false ;
		} catch (UnexistingUserException e) {
			assert (!MainController.ExistsInDB("Avis", "3"));
		}
		
		/*add an Avis with a too long message (primary_key = 3) -> supposed to catch an BadLengthException*/
		String message = "Je suis un bénéficiaire qui donne son avis sur le bénévole qui a réalisé sa demande d’aide. Le bénéficiaire et le bénévole existent bien dans la base de données, mais ce message est beaucoup trop long, il doit lever une exception car la taille est limitée à 300. Or, ici le message fait 302 caractères.";
		try {
			MainController.NewAvis (message, "identifiant2", "identifiant", 1);
			assert false ;
		} catch (BadLengthException e) {
			assert (!MainController.ExistsInDB("Avis", "3"));
		}
		
	}
	
}
