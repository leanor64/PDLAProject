package Model;

public class Avis {
	private String message;
	private User destinataire;
	private User emetteur;
	private int note ;
	private static int noAvis = 0 ;
	
	public Avis (String message, User destinataire, User emetteur, int note) throws Exception{
		noAvis ++;
		this.message = message;
		this.destinataire = destinataire;
		this.emetteur = emetteur;
		if ((note <= 5) && (note >= 0)) {
			this.note = note;
		} else {
			throw (new Exception());
		}
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public User getDest(){
		return this.destinataire;
	}
	
	public User getEmet() {
		return this.emetteur;
	}
	
	public int getNote() {
		return this.note;
	}
	
	public int getNoAvis() {
		return noAvis;
	}
	
}
