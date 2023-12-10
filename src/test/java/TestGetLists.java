import Controller.MainController;
import Model.*;
import com.sun.tools.javac.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static Controller.MainController.getDemandsofStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetLists {

	@BeforeAll
	public static void FillDB() {
		try {
			MainController.EmptyDB("Avis");
			MainController.EmptyDB("DemandeAide");
			MainController.EmptyDB("Person");

			MainController.NewUser ("identifiant", "abracadabra", "Camus", "Albert", 28, "albert@gmail.com", "0123456789", "Toulouse", "160 allée des sc appliquees", 1);
			MainController.NewUser ("identifiant2", "youpiyoupi", "Verlaine", "Paul", 17, "verlaine@gmail.com", "0123456789", "Assas", "9 rue des poissons rouges", 1);
			MainController.NewUser ("identifiant3", "motdepasse", "Dujardin", "Jean", 40, "dujardin@gmail.com", "0123456789", "Paris", "8 rue des lilas", 0);
			MainController.NewUser("benevole2", "motdepassesecurise", "Muffat", "Camille", 40, "muffat@gmail.com", "0123456789", "Nice", "8 rue des anges", 0);
			MainController.NewUser("benevole3", "password3", "Dupont", "Antoine", 26, "dupont@gmail.com", "0123456789", "Toulouse", "22 avenue du stade", 0);

			MainController.NewDemande ("Besoin daide 1", "Détails de la demande 1", "identifiant2", "08/12/2023", "Paris");
			MainController.NewDemande ("Besoin daide 2", "Détails de la demande 2", "identifiant", "08/12/2023", "Paris");
			MainController.NewDemande ("Besoin daide 3", "Détails de la demande 3", "identifiant2", "08/12/2023", "Paris");
			MainController.NewDemande ("Besoin daide 4", "Détails de la demande 4", "identifiant", "08/12/2023", "Paris");

			MainController.NewAvis ("Détails avis 1", "identifiant3", "identifiant", 5);
			MainController.NewAvis ("Détails avis 2", "identifiant3", "identifiant", 5);
			MainController.NewAvis ("Détails avis 3", "benevole2", "identifiant", 5);

		} catch (Exception e){
			System.out.println("Erreur add user, demande et avis");
		}
	}


	@Test
	void testListDemandsStatus() throws UnexistingDemandException {

		/*Get the list of Demands with state = REFUSEE_INAPPROPRIEE -> supposed to return []*/
		ArrayList<Integer> l1 = new ArrayList<>();
		assertEquals (getDemandsofStatus(StatutDemande.REFUSEE_INAPPROPRIEE),l1);

		/*Get the list of Demands with state = EN_ATTENTE -> supposed to return [1,2,3,4]*/
		ArrayList<Integer> l2 = new ArrayList<>(List.of(1,2,3,4));
		assertEquals (l2, getDemandsofStatus(StatutDemande.EN_ATTENTE));

		MainController.setStatusOfDemand(1, StatutDemande.ACCEPTEE);
		MainController.setStatusOfDemand(2, StatutDemande.VALIDEE);
		MainController.setStatusOfDemand(4, StatutDemande.ACCEPTEE);

		/*Get the list of Demands with state = ACCEPTEE -> supposed to return only few demands : [1,4]*/
		ArrayList<Integer> l3 = new ArrayList<>(List.of(1,4));
		assertEquals (l3, getDemandsofStatus(StatutDemande.ACCEPTEE));

		/*Get the list of Demands with state = VALIDEE -> supposed to return only one demand : [2]*/
		ArrayList<Integer> l4 = new ArrayList<>(List.of(2));
		assertEquals (l4, getDemandsofStatus(StatutDemande.VALIDEE));
	}
	
	@Test
	void testListDemandsBeneficiary() throws UnexistingUserException {

		/*Try to get the demands of an unexisting user -> supposed to throw an UnexistingUserException*/
		try {
			MainController.getDemandsOfBeneficiaire("idInexistant");
			assert false ;
		} catch (UnexistingUserException e){
			//OK
		}

		/*Get the demands of identifiant3 (no demands) -> supposed to return []*/
		ArrayList<Integer> l1 = new ArrayList<>();
		assertEquals(l1,MainController.getDemandsOfBeneficiaire("identifiant3"));

		/*Get the demands of identifiant -> supposed to return [2,4,5,7,8]*/
		ArrayList<Integer> l2 = new ArrayList<>(List.of(1,3));
		assertEquals(l2, MainController.getDemandsOfBeneficiaire("identifiant2"));

	}
	
	@Test
	void testListAvis() throws UnexistingUserException {

		/*Try to get the demands of an unexisting user -> supposed to throw an UnexistingUserException*/
		try {
			MainController.getListOfAvis("idInexistant");
			assert false ;
		} catch (UnexistingUserException e){
			//OK
		}

		/*Get the Avis of benevole3 (no avis) -> supposed to return []*/
		ArrayList<Integer> l1 = new ArrayList<>();
		assertEquals(l1, MainController.getListOfAvis("benevole3"));

		/*Get the Avis of benevole2 -> supposed to return [1,2]*/
		ArrayList<Integer> l2 = new ArrayList<>(List.of(1,2));
		assertEquals(l2, MainController.getListOfAvis("identifiant3"));

	}

}
