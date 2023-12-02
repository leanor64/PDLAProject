package View;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.*;
import Model.BadLengthException;
import Model.StatutDemande;
import Model.UnexistingDemandException;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLIntegrityConstraintViolationException;

//Interface pour l'inscription d'un nouvel utilisateur vient un formulaire
public class FormulaireInscription  extends JFrame implements ActionListener, ListSelectionListener, KeyListener{

	
	//Attributs
	JLabel labtitre, labnom, labprenom, labage, labemail, labtelephone, labville, labadresse, labid, labpassword,labcar, labtype;
	JTextField jtfnom,jtfprenom,jtfage, jtfemail, jtftelephone, jtfville, jtfadresse, jtfid;
	int type;
	JPasswordField jpfpassword;
	JButton btajout;
	
	JList<String> liste;
	JLabel etiquette;
	String choix[] = {"Bénévole", "Bénéficiaire", "Valideur"};
	
	boolean inscriptionOK;
	
	//Constructeur
	public FormulaireInscription(){
		
		this.setTitle("Inscription");
		this.setSize(600,600);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		inscriptionOK = false;
		
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(Color.white);
		add(pan);
		
		labtitre = new JLabel("Formulaire d'inscription");
		labtitre.setBounds(170,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre);
		
		labnom = new JLabel("Nom :");
		labnom.setBounds(20,60,300,30);
		labnom.setFont(new Font("Arial",Font.BOLD,18));
		labnom.setForeground(Color.black);
		pan.add(labnom);
		
		//Champ pour que l'utilisateur rentre son nom
		jtfnom = new JTextField();
		jtfnom.setBounds(160,60,200,25);
		jtfnom.addKeyListener(this);
		pan.add(jtfnom);
		
		labprenom = new JLabel("Prénom :");
		labprenom.setBounds(20,100,300,30);
		labprenom.setFont(new Font("Arial",Font.BOLD,18));
		labprenom.setForeground(Color.black);
		pan.add(labprenom);
		
		//Champ pour que l'utilisateur rentre son prénom
		jtfprenom = new JTextField();
		jtfprenom.setBounds(160,100,200,25);
		jtfprenom.addKeyListener(this);
		pan.add(jtfprenom);
		
		labage = new JLabel("Age :");
		labage.setBounds(20,140,300,30);
		labage.setFont(new Font("Arial",Font.BOLD,18));
		labage.setForeground(Color.black);
		pan.add(labage);
		
		//Champ pour que l'utilisateur rentre son age
		jtfage=new JTextField();
		jtfage.setBounds(160,140,200,25);
		jtfage.addKeyListener(this);
		pan.add(jtfage);
		
		labemail = new JLabel("Email :");
		labemail.setBounds(20,180,300,30);
		labemail.setFont(new Font("Arial",Font.BOLD,18));
		labemail.setForeground(Color.black);
		pan.add(labemail);
		
		//Champ pour que l'utilisateur rentre son email
		jtfemail = new JTextField();
		jtfemail.setBounds(160,180,200,25);
		jtfemail.addKeyListener(this);
		pan.add(jtfemail);
		
		labtelephone = new JLabel("Telephone :");
		labtelephone.setBounds(20,220,300,30);
		labtelephone.setFont(new Font("Arial",Font.BOLD,18));
		labtelephone.setForeground(Color.black);
		pan.add(labtelephone);
		
		//Champ pour que l'utilisateur rentre son telephone
		jtftelephone = new JTextField();
		jtftelephone.setBounds(160,220,200,25);
		jtftelephone.addKeyListener(this);
		pan.add(jtftelephone);
		
		labville = new JLabel("Ville :");
		labville.setBounds(20,260,300,30);
		labville.setFont(new Font("Arial",Font.BOLD,18));
		labville.setForeground(Color.black);
		pan.add(labville);
		
		//Champ pour que l'utilisateur rentre sa ville
		jtfville = new JTextField();
		jtfville.setBounds(160,260,200,25);
		jtfville.addKeyListener(this);
		pan.add(jtfville);
		
		labadresse = new JLabel("Adresse :");
		labadresse.setBounds(20,300,300,30);
		labadresse.setFont(new Font("Arial",Font.BOLD,18));
		labadresse.setForeground(Color.black);
		pan.add(labadresse);
		
		//Champ pour que l'utilisateur rentre son adresse
		jtfadresse = new JTextField();
		jtfadresse.setBounds(160,300,200,25);
		jtfadresse.addKeyListener(this);
		pan.add(jtfadresse);
		
		labid = new JLabel("Identifiant :"); //Vérifier que cet id n'existe pas déjà
		labid.setBounds(20,340,300,30);
		labid.setFont(new Font("Arial",Font.BOLD,18));
		labid.setForeground(Color.black);
		pan.add(labid);
		
		//Champ pour que l'utilisateur rentre son identifiant
		jtfid = new JTextField();
		jtfid.setBounds(160,340,200,25);
		jtfid.addKeyListener(this);
		pan.add(jtfid);
		
		labpassword = new JLabel("Mot de passe :");
		labpassword.setBounds(20,380,300,30);
		labpassword.setFont(new Font("Arial",Font.BOLD,18));
		labpassword.setForeground(Color.black);
		pan.add(labpassword);
		
		//Champ pour que l'utilisateur rentre son mot de passe (caché pour plus de sécurité) 
		jpfpassword = new JPasswordField();
		jpfpassword.setBounds(160,380,200,25);
		jpfpassword.addKeyListener(this);
		pan.add(jpfpassword);
		
		labcar = new JLabel("(9 caractères minimum)");
		labcar.setBounds(380,380,300,30);
		labcar.setFont(new Font("Arial",Font.BOLD,10));
		labcar.setForeground(Color.black);
		pan.add(labcar);
		
		labtype = new JLabel("Sélectionner un type de profil :");
		labtype.setBounds(20,420,300,30);
		labtype.setFont(new Font("Arial",Font.BOLD,18));
		labtype.setForeground(Color.black);
		pan.add(labtype);
		
		//L'utilisateur peut choisir son profil : bénévole, bénéficiaiare ou valideur
		liste = new JList<>(choix);
		liste.setBounds(320,420,200,55);
		liste.addListSelectionListener(this);
		etiquette = new JLabel(" ");
		pan.add(etiquette);
		pan.add(liste);
		etiquette.setText((String)liste.getSelectedValue());
		
		//bouton pour valider ses informations
		btajout = new JButton("S'inscrire");
		btajout.setBounds(225,520,150,30);
		btajout.setBackground(Color.white);
		btajout.setFont(new Font("Arial",Font.BOLD,18));
		btajout.setForeground(Color.black);
		pan.add(btajout);
		btajout.addActionListener(this);
	}
		
	//Méthodes
	
	//s'affiche si l'identifiant saisi est déjà utilisé
	public void afficherIDDejaUtilise() {
		JOptionPane.showMessageDialog(this, "Identifiant déjà utilisé, veuillez en saisir un nouveau");
	}
	
	//s'affiche si la chaîne de caractères saisie n'a pas une taille valide
	public void afficherTailleNonValide(String info) {
		JOptionPane.showMessageDialog(this,info + " invalide, attention à respecter le nombre de caractères");
	}
	
	//s'affiche si l'age saisi n'est pas un int
	public void afficherAgeNonValide() {
		JOptionPane.showMessageDialog(this, "Age invalide, veuillez rentrer un nombre entier");
	}
	
	//s'affiche si aucune option de profil n'est selectionnée
	public void afficherPasOption() {
		JOptionPane.showMessageDialog(this, "Veuillez choisir un rôle : bénévole, bénéficiaire ou valideur");
	}
    
	//pour convertir le mdp en String
	public String toString(char[] password) {
		String result = "";
		for (int i = 0;i<password.length;i++) {
			result += password[i];
		}
		return result;
	}
	
	//Pour empêcher l'utilisateur de rentrer certains caracères spéciaux 
	public boolean isCaractereAutorise(char c) {
	    return c != '"' && c != '\'' && c != '`' && c != '\\';
	}
	public void keyTyped(KeyEvent k) {
        if (!isCaractereAutorise(k.getKeyChar())) {
            k.consume();
        }
    }	
	public void keyPressed(KeyEvent e) {
	    // Ne rien faire ici, car nous n'utilisons pas keyPressed
	}
	public void keyReleased(KeyEvent e) {
	    // Ne rien faire ici, car nous n'utilisons pas keyReleased
	}
	
	
	/* Correspondance des types
	 * 0 : Bénévole
	 * 1 : Bénéficiaire
	 * 2 : Valideur
	*/
	
	//pour obtenir le type de profil selectionné par l'utilisateur
	public void valueChanged(ListSelectionEvent evt) { 
		 etiquette.setText((String)liste.getSelectedValue());
		 if ((String)liste.getSelectedValue() == "Bénévole"){
			 type = 0;
		 } else if ((String)liste.getSelectedValue() == "Bénéficiaire"){
			 type = 1;
			 
		 } else {
			 type = 2;
		 }
	}
	
	//pour gérer les différents boutons
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btajout)) {
			if (liste.isSelectionEmpty()) {
				afficherPasOption();
			} else {
				try {
					//création du nouvel utilisateur dans la database
					MainController.NewUser(jtfid.getText(), toString(jpfpassword.getPassword()), jtfnom.getText(),
					jtfprenom.getText(), Integer.parseInt(jtfage.getText()), jtfemail.getText(),
					jtftelephone.getText(), jtfville.getText(), jtfadresse.getText(), type);
					inscriptionOK = true;
				} catch (SQLIntegrityConstraintViolationException exc1) {
				afficherIDDejaUtilise();
				} catch (BadLengthException exc2) {
					afficherTailleNonValide(exc2.getMessage());
				} catch (NumberFormatException exc3) {
					afficherAgeNonValide();
				}
			}
		}
		if (inscriptionOK == true) {
			//affichage de la vue correspondant au type de profil de l'utilisateur
			if (type == 0) {
				ViewBenevole viewbn = new ViewBenevole(jtfid.getText());
				viewbn.setVisible(true);
			} else if (type == 1) {
				ViewBeneficiaire viewbf = new ViewBeneficiaire(jtfid.getText());
				viewbf.setVisible(true);
			} else {
				ViewValideur viewv = new ViewValideur(jtfid.getText());
				viewv.setVisible(true);
			}
			dispose();
		}
	}
	
//TODO : a enlever
	public static void main(String[] args) {
		
     FormulaireInscription form = new FormulaireInscription();
     form.setVisible(true);
	}

}



