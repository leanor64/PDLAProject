import Controller.MainController;
import Model.StatutDemande;
import Model.UnexistingDemandException;
import Model.UnexistingInfoException;
import Model.UnexistingUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetInfo {

	@BeforeEach
	public void EmptyDB() {
		MainController.EmptyDB("Avis");
		MainController.EmptyDB("DemandeAide");
		MainController.EmptyDB("Person");
	}

	@Test
	void testSetBenevoleDemande() throws UnexistingDemandException, UnexistingUserException, UnexistingInfoException {

		try{
			MainController.NewUser ("identifiant", "abracadabra", "Camus", "Albert", 28, "albert@gmail.com", "0123456789", "Toulouse", "160 allée des sc appliquees", 1);
			MainController.NewUser ("identifiant2", "youpiyoupi", "Verlaine", "Paul", 17, "verlaine@gmail.com", "0123456789", "Assas", "9 rue des poissons rouges", 2);
			MainController.NewDemande ("Besoin daide 1", "Détails de la demande", "identifiant2", "09/12/2023", "Lyon");
		} catch (Exception e) {
			System.out.println("Erreur add User et Demand");
		}

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
		assertEquals(MainController.getInfoOfDemand(1,"benevole"),"");
		MainController.setBenevoleOfDemand(1, "identifiant");
		assertEquals(MainController.getInfoOfDemand(1,"benevole"),"identifiant");

	}
	
	@Test
	void testSetStatusDemande() throws UnexistingInfoException, UnexistingDemandException {

		try{
			MainController.NewUser ("identifiant", "abracadabra", "Camus", "Albert", 28, "albert@gmail.com", "0123456789", "Toulouse", "160 allée des sc appliquees", 1);
			MainController.NewDemande ("Besoin daide 1", "Détails de la demande", "identifiant", "09/12/2023", "Lyon");
		} catch (Exception e) {
			System.out.println("Erreur add User et Demand");
		}

		/*Try to set the state of an unexisting demand -> supposed to throw an UnexistingDemandException*/
		try {
			assert !MainController.ExistsInDB("DemandeAide", "2");
			MainController.setStatusOfDemand(2, StatutDemande.VALIDEE);
			assert false ;
		} catch (UnexistingDemandException e) {
			//OK
		}

		/*Try to set the state of a demand -> supposed to change the state of the demande*/
		assertEquals(MainController.getInfoOfDemand(1,"statut"),"EN_ATTENTE");
		MainController.setStatusOfDemand(1, StatutDemande.REFUSEE_INAPPROPRIEE);
		assertEquals(MainController.getInfoOfDemand(1,"statut"),"REFUSEE_INAPPROPRIEE");

	}
	
	@Test
	void testSetInfoUser() throws UnexistingInfoException, UnexistingUserException {
		try{
			MainController.NewUser ("identifiant", "abracadabra", "Camus", "Albert", 28, "albert@gmail.com", "0123456789", "Toulouse", "160 allée des sc appliquees", 1);
		} catch (Exception e) {
			System.out.println("Erreur add User");
		}

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

		/*Try to set the name of an existing user -> supposed to change the name of the user*/
		assertEquals(MainController.getInfoOfUser("identifiant", "nom"),"Camus");
		MainController.setInfoOfUser("identifiant", "nom", "Einstein");
		assertEquals(MainController.getInfoOfUser("identifiant","nom"),"Einstein");

		/*Try to set the firstname of an existing user -> supposed to change the firstname of the user*/
		assertEquals(MainController.getInfoOfUser("identifiant", "prenom"),"Albert");
		MainController.setInfoOfUser("identifiant", "prenom", "Jean");
		assertEquals(MainController.getInfoOfUser("identifiant","prenom"),"Jean");

		/*Try to set the age of an existing user -> supposed to change the name of the user*/
		assertEquals(MainController.getInfoOfUser("identifiant", "age"),"28");
		MainController.setInfoOfUser("identifiant", "age", "12");
		assertEquals(MainController.getInfoOfUser("identifiant","age"),"12");

		/*Try to set the email of an existing user -> supposed to change the email of the user*/
		assertEquals(MainController.getInfoOfUser("identifiant", "email"),"albert@gmail.com");
		MainController.setInfoOfUser("identifiant", "email", "jean@gmail.com");
		assertEquals(MainController.getInfoOfUser("identifiant","email"),"jean@gmail.com");

		/*Try to set the phone number of an existing user -> supposed to change the phone number of the user*/
		assertEquals(MainController.getInfoOfUser("identifiant", "telephone"),"0123456789");
		MainController.setInfoOfUser("identifiant", "telephone", "9876543210");
		assertEquals(MainController.getInfoOfUser("identifiant","telephone"),"9876543210");

		/*Try to set the city of an existing user -> supposed to change the city of the user*/
		assertEquals(MainController.getInfoOfUser("identifiant", "ville"),"Toulouse");
		MainController.setInfoOfUser("identifiant", "ville", "Lyon");
		assertEquals(MainController.getInfoOfUser("identifiant","ville"),"Lyon");

		/*Try to set the address of an existing user -> supposed to change the address of the user*/
		assertEquals(MainController.getInfoOfUser("identifiant", "adresse"),"160 allée des sc appliquees");
		MainController.setInfoOfUser("identifiant", "adresse", "8 rue des oiseaux");
		assertEquals(MainController.getInfoOfUser("identifiant","adresse"),"8 rue des oiseaux");

		/*Try to set the password of an existing user -> supposed to change the password of the user*/
		assertEquals(MainController.getInfoOfUser("identifiant", "mot de passe"),"abracadabra");
		MainController.setInfoOfUser("identifiant", "mot de passe", "nouveauPassword");
		assertEquals(MainController.getInfoOfUser("identifiant","mot de passe"),"nouveauPassword");

	}
	
	
}
