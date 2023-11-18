package Controller;

public class PseudoMain {
      
	public PseudoMain(){}
			
	public static void main(String[] args) {
		
		try {
			NewUser u1 = new NewUser ("maurice", "abracadabra", "Camus", "Albert", 28, "blabla@gmail.com", "0123456789", "Toulouse", "8 allée des sc appliquees", 14, 1);
			NewUser u = new NewUser ("albert", "abracadabra", "Camus", "Albert", 28, "blabla@gmail.com", "0123456789", "Toulouse", "8 allée des sc appliquees", 14, 0);
			NewUser v = new NewUser ("pat", "abracadabra", "Camus", "Albert", 28, "blabla@gmail.com", "0123456789", "Toulouse", "8 allée des sc appliquees", 14, 2);
		
			NewAvis a = new NewAvis ("Maurice il était top", "maurice", "albert", 5);
			NewAvis b = new NewAvis ("Jean il était top", "maurice", "albert", 4);
			NewAvis c = new NewAvis ("Patoche il était top", "maurice", "albert", 5);
			NewAvis d = new NewAvis ("AA il était top", "maurice", "albert", 4);
			
			NewDemande e = new NewDemande ("Besoin daide 1", "Besoin daide avec détails", "albert", "pas validé", "08/12/2023", "Paris");
			NewDemande f = new NewDemande ("Besoin daide 2", "Besoin daide avec détails", "albert", "pas validé", "08/12/2123", "Paris");
			NewDemande g = new NewDemande ("Besoin daide 3", "Besoin daide avec détails", "albert", "pas validé", "03/12/2023", "Paris");
			NewDemande h = new NewDemande ("Besoin daide 4", "Besoin daide avec détails", "albert", "pas validé", "08/13/2023", "Paris");
		} catch (BadLengthException exc2) {
			System.out.println ("erreur length");
		} catch (BadConnectionException exc3) {
			System.out.println ("erreur user inexistant");
		}catch (Exception exc) {
			System.out.println ("erreur");
		}
		
		try {
			new CheckConnection("albert", "abracadabra");
		} catch (Exception e1) {
			System.out.println("boulette n°1");
		}
		
		try {
			new CheckConnection("albert", "abracadabre");
		} catch (Exception e2) {
			System.out.println("boulette n°2");
		}
		
	}

	
}

	