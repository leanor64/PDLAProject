package Model;

public class Demande {
	private static int noDemande = 0;
	private String title ;
	private String explication ;
	private Beneficiaire demandeur ;
	private String state ;
	
	public Demande (String title, String explication, Beneficiaire demandeur, String state) {
		noDemande ++ ;
		this.title = title ;
		this.explication = explication ;
		this.demandeur = demandeur ;
		this.state = state ;
	}

	public String getTitle() {
		return this.title;
	}
	
	public String getExlication() {
		return this.explication;
	}
	
	public Beneficiaire getBeneficiaire() {
		return this.demandeur;
	}
	
	public String getState() {
		return this.state;
	}
	
	public int getNoDemande() {
		return noDemande;
	}
}
