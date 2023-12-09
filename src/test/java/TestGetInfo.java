import Controller.MainController;
import Model.UnexistingAvisException;
import Model.UnexistingDemandException;
import Model.UnexistingInfoException;
import Model.UnexistingUserException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetInfo {

	@BeforeAll
	public static void EmptyDB() {
		MainController.EmptyDB("Avis");
		MainController.EmptyDB("DemandeAide");
		MainController.EmptyDB("Person");

		try{
			MainController.NewUser ("identifiant", "abracadabra", "Camus", "Albert", 28, "albert@gmail.com", "0123456789", "Toulouse", "160 allée des sc appliquees", 1);
			MainController.NewUser ("identifiant2", "youpiyoupi", "Verlaine", "Paul", 17, "verlaine@gmail.com", "0123456789", "Assas", "9 rue des poissons rouges", 2);

			MainController.NewDemande ("Besoin daide 1", "Détails de la demande", "identifiant2", "09/12/2023", "Lyon");
			MainController.NewDemande ("Besoin daide 2", "Détails de la demande 2", "identifiant", "08/12/2023", "Paris");

			MainController.NewAvis ("Détails avis 1", "identifiant", "identifiant2", 5);
			MainController.NewAvis ("Détails avis 2", "identifiant", "identifiant2", 3);
		} catch (Exception e) {
			System.out.println("Erreur add User et Demand");
		}
	}

	@Test
	void testGetInfoUser() throws UnexistingInfoException, UnexistingUserException {

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
			assert MainController.ExistsInDB("Person", "identifiant");
			String s = MainController.getInfoOfUser("identifiant", "infoInexistante");
			assert false ;
		} catch (UnexistingInfoException e) {
			//OK
		}

		/*Try to get the name of the user "identifiant" -> supposed to return Camus */
		assertEquals("Camus", MainController.getInfoOfUser("identifiant", "nom"));

		/*Try to get the firstname of the user "identifiant" -> supposed to return Albert */
		assertEquals("Albert", MainController.getInfoOfUser("identifiant", "prenom"));

		/*Try to get the age of the user "identifiant2" -> supposed to return 17*/
		assertEquals("17", MainController.getInfoOfUser("identifiant2", "age"));

		/*Try to get the email of the user "identifiant2" -> supposed to return verlaine@gmail.com*/
		assertEquals("verlaine@gmail.com", MainController.getInfoOfUser("identifiant2", "email"));

	}
	
	@Test
	void testGetInfoDemand() throws UnexistingInfoException, UnexistingDemandException {

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
		assertEquals("Besoin daide 1", MainController.getInfoOfDemand(1, "titre"));

		/*Try to get the explanation of the demand 2" -> supposed to return Détails de la demande 2*/
		assertEquals("Détails de la demande 2", MainController.getInfoOfDemand(2, "explication"));

		/*Try to get the state of the demand 1" -> supposed to return EN_ATTENTE*/
		assertEquals("EN_ATTENTE", MainController.getInfoOfDemand(1, "statut"));

		/*Try to get the date of the demand 2" -> supposed to return 08/12/2023 */
		assertEquals("08/12/2023", MainController.getInfoOfDemand(2, "jour"));

		/*Try to get the benevole of the demand 1" -> supposed to return nothing*/
		assertEquals("",MainController.getInfoOfDemand(1, "benevole"));

	}
	
	@Test
	void testGetInfoAvis() throws UnexistingInfoException, UnexistingAvisException {
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
		assertEquals("identifiant", MainController.getInfoOfAvis(1, "destinataire"));

		/*Try to get the emitter of the Avis 1" -> supposed to return identifiant2*/
		assertEquals("identifiant2", MainController.getInfoOfAvis(1, "emetteur"));

		/*Try to get the message of the Avis 2" -> supposed to return Détails avis 2 */
		assertEquals("Détails avis 2", MainController.getInfoOfAvis(2, "commentaire"));

		/*Try to get the note of the Avis 2" -> supposed to return 3.0*/
		assertEquals("3.0",MainController.getInfoOfAvis(2, "note"));



	}
	
	
	
	
	
	
	
}
