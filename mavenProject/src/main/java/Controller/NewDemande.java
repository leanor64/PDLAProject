package Controller;

import Model.Demande;

public class NewDemande {

	public NewDemande (Demande demande) {
		String commande = "INSERT into DemandeAide VALUES ('"+demande.getNoDemande()+"','"+demande.getTitle()+"','"+demande.getExlication()+"','"+demande.getBeneficiaire().getIdUser()+"','"+demande.getState()+"');";
		Action insert = new Action (commande, "Demande");
	}
}
