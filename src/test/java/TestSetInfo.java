import Controller.MainController;
import Model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetInfo {

	@BeforeAll
	public static void FillDB() {
		MainController.EmptyDB("Avis");
		MainController.EmptyDB("DemandeAide");
		MainController.EmptyDB("Person");

		try {
			MainController.NewUser ("identifiant", "abracadabra", "Camus", "Albert", 28, "albert@gmail.com", "0123456789", "Toulouse", "160 allée des sc appliquees", 1);
			MainController.NewUser ("identifiant2", "youpiyoupi", "Verlaine", "Paul", 17, "verlaine@gmail.com", "0123456789", "Assas", "9 rue des poissons rouges", 2);
			MainController.NewDemande ("Besoin daide 1", "Détails de la demande", "identifiant2", "09/12/2023", "Lyon");
		} catch (Exception e){
			System.out.println("Erreur add User, Demands and Avis");
		}

	}

	@Test
	void testSetBenevoleDemande() throws UnexistingDemandException, UnexistingUserException, UnexistingInfoException {

		/*Try to set the benevole of an unexisting demand -> supposed to throw an UnexistingDemandException*/
		try {
			assert MainController.ExistsInDB("Person", "identifiant");
			assert !MainController.ExistsInDB("DemandeAide", "2");
			MainController.setBenevoleOfDemand(2, "identifiant");
			assert false ;
		} catch (UnexistingDemandException e) {
			//OK
		}

		/*Try to set the benevole of a demand with an unexisting benevole -> supposed to throw an UnexistingUserException*/
		try {
			assert !MainController.ExistsInDB("Person", "idInexistant");
			assert MainController.ExistsInDB("DemandeAide", "1");
			MainController.setBenevoleOfDemand(1, "idInexistant");
			assert false ;
		} catch (UnexistingUserException e) {
			//OK
		}

		/*Try to set the benevole of a demand -> supposed to change the name of the benevole*/
		assertEquals("",MainController.getInfoOfDemand(1,"benevole"));
		MainController.setBenevoleOfDemand(1, "identifiant");
		assertEquals("identifiant", MainController.getInfoOfDemand(1,"benevole"));

	}
	
	@Test
	void testSetStatusDemande() throws UnexistingInfoException, UnexistingDemandException {

		/*Try to set the state of an unexisting demand -> supposed to throw an UnexistingDemandException*/
		try {
			assert !MainController.ExistsInDB("DemandeAide", "3");
			MainController.setStatusOfDemand(3, StatutDemande.VALIDEE);
			assert false ;
		} catch (UnexistingDemandException e) {
			//OK
		}

		/*Try to set the state of a demand -> supposed to change the state of the demande*/
		MainController.setStatusOfDemand(1, StatutDemande.REFUSEE_INAPPROPRIEE);
		assertEquals("REFUSEE_INAPPROPRIEE", MainController.getInfoOfDemand(1,"statut"));

	}
	
	@Test
	void testSetInfoUser() throws UnexistingInfoException, UnexistingUserException, BadLengthException {

		/*Try to set an info of an unexisting User -> supposed to throw an UnexistingUserException*/
		try {
			assert !MainController.ExistsInDB("Person", "idInexistant");
			MainController.setInfoOfUser("idInexistant", "nom","no matter");
			assert false ;
		} catch (UnexistingUserException e) {
			//OK
		}

		/*Try to set an unexisting info of a user -> supposed to throw an UnexistingInfoException*/
		try {
			assert MainController.ExistsInDB("Person","identifiant");
			MainController.setInfoOfUser("identifiant", "infoInexistante","nomatter");
			assert false ;
		} catch (UnexistingInfoException e) {
			//OK
		}

		/*Try to set the name of the user "identifiant" -> supposed to change the name of the user*/
		assertEquals("Camus", MainController.getInfoOfUser("identifiant", "nom"));
		MainController.setInfoOfUser("identifiant", "nom", "Einstein");
		assertEquals("Einstein", MainController.getInfoOfUser("identifiant","nom"));

		/*Try to set the firstname of the user "identifiant" -> supposed to change the firstname of the user*/
		assertEquals("Albert", MainController.getInfoOfUser("identifiant", "prenom"));
		MainController.setInfoOfUser("identifiant", "prenom", "Jean");
		assertEquals("Jean", MainController.getInfoOfUser("identifiant","prenom"));

		/*Try to set the age of the user "identifiant" -> supposed to change the name of the user*/
		assertEquals("28", MainController.getInfoOfUser("identifiant", "age"));
		MainController.setInfoOfUser("identifiant", "age", "12");
		assertEquals("12", MainController.getInfoOfUser("identifiant","age"));

		/*Try to set the email of the user "identifiant" with a new email null -> supposed to throw a BadLengthException */
		try {
			MainController.setInfoOfUser("identifiant", "email", "");
			assert false ;
		} catch (BadLengthException e){
			//OK
		}

		/*Try to set the phone number of an existing user with a new number too long -> supposed to throw a BadLengthException*/
		try {
			MainController.setInfoOfUser("identifiant", "telephone", "012345678901234");
			assert false;

		} catch (BadLengthException e){
			//OK
		}
	}
	
	
}
