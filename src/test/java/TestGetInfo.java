import Controller.MainController;
import Model.UnexistingAvisException;
import Model.UnexistingDemandException;
import Model.UnexistingInfoException;
import Model.UnexistingUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetInfo {

	@BeforeEach
	public void EmptyDB() {
		MainController.EmptyDB("Avis");
		MainController.EmptyDB("DemandeAide");
		MainController.EmptyDB("Person");
	}

	@Test
	void testGetInfoUser() throws UnexistingInfoException, UnexistingUserException {

		try{
			MainController.NewUser ("identifiant1", "motdepasse", "Dujardin", "Jean", 40, "dujardin@gmail.com", "0123456789", "Paris", "8 rue des lilas", 0);
			MainController.NewUser ("identifiant2", "youpiyoupi", "Verlaine", "Paul", 17, "verlaine@gmail.com", "0123456789", "Assas", "9 rue des poissons rouges", 1);
		} catch (Exception e) {
			System.out.println("Erreur add User");
		}

		/*Try to get info of an unexisting user -> supposed to throw an UnexistingUserException*/
		try {
			assert !MainController.ExistsInDB("Person", "idInexistant");
			String s = MainController.getInfoOfUser("idInexistant", "nom");
			assert false ;
		} catch (UnexistingUserException e) {
			//OK
		}

		/*Try to get unexisting info of a user -> supposed to throw an UnexistingInfoException*/
		try {
			assert MainController.ExistsInDB("Person", "identifiant1");
			String s = MainController.getInfoOfUser("identifiant1", "infoInexistante");
			assert false ;
		} catch (UnexistingInfoException e) {
			//OK
		}

		/*Try to get the name of the user "identifiant1" -> supposed to return Dujardin */
		assertEquals(MainController.getInfoOfUser("identifiant1", "nom"), "Dujardin");

		/*Try to get the firstname of the user "identifiant1" -> supposed to return Jean */
		assertEquals(MainController.getInfoOfUser("identifiant1", "prenom"), "Jean");

		/*Try to get the age of the user "identifiant2" -> supposed to return 17*/
		assertEquals(MainController.getInfoOfUser("identifiant2", "age"), "17");

		/*Try to get the email of the user "identifiant2" -> supposed to return verlaine@gmail.com*/
		assertEquals(MainController.getInfoOfUser("identifiant2", "email"), "verlaine@gmail.com");

		/*Try to get the phone number of the user "identifiant1" -> supposed to return 0123456789*/
		assertEquals(MainController.getInfoOfUser("identifiant1", "telephone"), "0123456789");

		/*Try to get the city of the user "identifiant1" -> supposed to return Paris*/
		assertEquals(MainController.getInfoOfUser("identifiant1", "ville"), "Paris");

		/*Try to get the adress of the user "identifiant2" -> supposed to return 9 rue des poissons rouges*/
		assertEquals(MainController.getInfoOfUser("identifiant2", "adresse"), "9 rue des poissons rouges");

		/*Try to get the note of the user "identifiant2" -> supposed to return 0.0*/
		assertEquals(MainController.getInfoOfUser("identifiant2", "note"), "0.0");

		/*Try to get the password of the user "identifiant1" -> supposed to return motdepasse*/
		assertEquals(MainController.getInfoOfUser("identifiant1", "mot de passe"), "motdepasse");

		/*Try to get the number of Avis of the user "identifiant1" -> supposed to return 0 */
		assertEquals(MainController.getInfoOfUser("identifiant1", "nbAvis"), "0");

	}
	
	@Test
	void testGetInfoDemand() throws UnexistingInfoException, UnexistingDemandException {
		try{
			MainController.NewUser ("identifiant", "abracadabra", "Camus", "Albert", 28, "albert@gmail.com", "0123456789", "Toulouse", "160 allée des sc appliquees", 1);
			MainController.NewUser ("identifiant2", "youpiyoupi", "Verlaine", "Paul", 17, "verlaine@gmail.com", "0123456789", "Assas", "9 rue des poissons rouges", 2);
			MainController.NewDemande ("Besoin daide 1", "Détails de la demande", "identifiant2", "09/12/2023", "Lyon");
			MainController.NewDemande ("Besoin daide 2", "Détails de la demande 2", "identifiant", "08/12/2023", "Paris");
		} catch (Exception e) {
			System.out.println("Erreur add User et Demand");
		}

		/*Try to get info of an unexisting demand -> supposed to throw an UnexistingDemandException*/
		try {
			assert !MainController.ExistsInDB("DemandeAide", "3");
			String s = MainController.getInfoOfDemand(3, "titre");
			assert false ;
		} catch (UnexistingDemandException e) {
			//OK
		}

		/*Try to get unexisting info of a demand -> supposed to throw an UnexistingInfoException*/
		try {
			assert MainController.ExistsInDB("DemandeAide", "1");
			String s = MainController.getInfoOfDemand(1, "infoInexistante");
			assert false ;
		} catch (UnexistingInfoException e) {
			//OK
		}

		/*Try to get the title of the demand 1" -> supposed to return Besoin daide 1*/
		assertEquals(MainController.getInfoOfDemand(1, "titre"), "Besoin daide 1");

		/*Try to get the explanation of the demand 2" -> supposed to return Détails de la demande 2*/
		assertEquals(MainController.getInfoOfDemand(2, "explication"), "Détails de la demande 2");

		/*Try to get the state of the demand 1" -> supposed to return EN_ATTENTE*/
		assertEquals(MainController.getInfoOfDemand(1, "statut"), "EN_ATTENTE");

		/*Try to get the date of the demand 2" -> supposed to return 08/12/2023 */
		assertEquals(MainController.getInfoOfDemand(2, "jour"), "08/12/2023");

		/*Try to get the city of the demand 1" -> supposed to return Lyon */
		assertEquals(MainController.getInfoOfDemand(1, "ville"), "Lyon");

		/*Try to get the beneficiary of the demand 2" -> supposed to return identifiant*/
		assertEquals(MainController.getInfoOfDemand(2, "beneficiaire"), "identifiant");

		/*Try to get the benevole of the demand 1" -> supposed to return nothing*/
		assertEquals(MainController.getInfoOfDemand(1, "benevole"), "");

	}
	
	@Test
	void testGetInfoAvis() throws UnexistingInfoException, UnexistingAvisException {
		try{
			MainController.NewUser ("identifiant", "abracadabra", "Camus", "Albert", 28, "albert@gmail.com", "0123456789", "Toulouse", "160 allée des sc appliquees", 1);
			MainController.NewUser ("identifiant2", "youpiyoupi", "Verlaine", "Paul", 17, "verlaine@gmail.com", "0123456789", "Assas", "9 rue des poissons rouges", 2);
			MainController.NewAvis ("Détails avis 1", "identifiant", "identifiant2", 5);
			MainController.NewAvis ("Détails avis 2", "identifiant", "identifiant2", 3);
		} catch (Exception e) {
			System.out.println("Erreur add User et Avis");
		}

		/*Try to get info of an unexisting Avis -> supposed to throw an UnexistingAvisException*/
		try {
			assert !MainController.ExistsInDB("Avis", "3");
			String s = MainController.getInfoOfAvis(3, "destinataire");
			assert false ;
		} catch (UnexistingAvisException e) {
			//OK
		}

		/*Try to get unexisting info of an Avis -> supposed to throw an UnexistingInfoException*/
		try {
			assert MainController.ExistsInDB("Avis", "1");
			String s = MainController.getInfoOfAvis(1, "infoInexistante");
			assert false ;
		} catch (UnexistingInfoException e) {
			//OK
		}

		/*Try to get the recipient of the Avis 1" -> supposed to return identifiant*/
		assertEquals(MainController.getInfoOfAvis(1, "destinataire"), "identifiant");

		/*Try to get the emitter of the Avis 1" -> supposed to return identifiant2*/
		assertEquals(MainController.getInfoOfAvis(1, "emetteur"), "identifiant2");

		/*Try to get the message of the Avis 2" -> supposed to return Détails avis 2 */
		assertEquals(MainController.getInfoOfAvis(2, "commentaire"), "Détails avis 2");

		/*Try to get the note of the Avis 2" -> supposed to return 3.0*/
		assertEquals(MainController.getInfoOfAvis(2, "note"), "3.0");



	}
	
	
	
	
	
	
	
}
