package Model;

public class User {
	
	//Attributs
	
		private int idUser;
		private String password;
		private String nom;
		private String prenom;
		private int age;
		private String email;
		private String telephone;
		private String ville;
		private String adresse;
		
		
	//Constructeur
		
		public User(int idUser, String password, String nom, String prenom, int age, String email, String telephone, String ville, String adresse) {
			this.idUser = idUser;
			this.password = password;
			this.nom = nom;
			this.prenom = prenom;
			this.age = age;
			this.email = email;
			this.telephone = telephone;
			this.ville = ville;
			this.adresse = adresse;
		}
		
	//Méthodes
		
		public int getIdUser() {
			return this.idUser;
		}
		
		public String getPassword() {
			return this.password;
		}
		
		public String getNom() {
			return this.nom;
		}
		
		public String getPrenom() {
			return this.prenom;
		}
		
		public int getAge() {
			return this.age;
		}
		
		public String getEmail() {
			return this.email;
		}
		
		public String getTelephone() {
			return this.telephone;
		}
		
		public String getVille() {
			return this.ville;
		}
		
		public String getAdresse() {
			return this.adresse;
		}
		
		public void setNom(String nom) {
			this.nom = nom;
		}
		
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		
		public void setVille(String ville) {
			this.ville = ville;
		}
		
		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}
		
		public void creerAvis (String commentaire, User utilisateurVise, int note) {
			
		}
		
		
		

}
