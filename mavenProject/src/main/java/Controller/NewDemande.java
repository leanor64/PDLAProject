package Controller;

import java.sql.SQLIntegrityConstraintViolationException;

import Model.Demande;

public class NewDemande {

	public NewDemande (Demande demande) {
		String commande = "INSERT into DemandeAide VALUES ('"+demande.getNoDemande()+"','"+demande.getTitle()+"','"+demande.getExlication()+"','"+demande.getBeneficiaire().getIdUser()+"','"+demande.getState()+"');";
		try {
			Action insert = new Action (commande, "Demande");
		} catch (SQLIntegrityConstraintViolationException e) {}
	}
}
