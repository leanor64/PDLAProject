package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Controller.*;
import Model.UnexistingInfoException;
import Model.UnexistingUserException;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//interface permettant à l'utilisateur de modifier ses informations personnelles
public class ChangementInfos  extends JFrame implements ActionListener, KeyListener, DocumentListener{

	
	//Attributs
	JLabel labtitre, labnom, labprenom, labage, labemail, labtelephone, labville, labadresse, labpassword, labcar;
	JTextField jtfnom,jtfprenom,jtfage, jtfemail, jtftelephone, jtfville, jtfadresse;
	int type;
	JPasswordField jpfpassword;
	JButton btajout, btannuler;
	String idUser;

	
	//Constructeur
	public ChangementInfos(String idUser){
		this.idUser = idUser;
		
		this.setTitle("Modifier vos informations personnelles");
		this.setSize(600,600);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(Color.white);
		add(pan);
		
		//Les différentes zones de textes contenant les informations de l'utilisateur
		
		labtitre = new JLabel("Vos données personnelles");
		labtitre.setBounds(170,10,300,50);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre);
		
		labnom = new JLabel("Nom :");
		labnom.setBounds(20,60,300,30);
		labnom.setFont(new Font("Arial",Font.BOLD,18));
		labnom.setForeground(Color.black);
		pan.add(labnom);
		
		jtfnom = new JTextField();
		jtfnom.setBounds(160,60,200,25);
		jtfnom.addKeyListener(this);
		jtfnom.getDocument().addDocumentListener(this);
		pan.add(jtfnom);
		
		labprenom = new JLabel("Prénom :");
		labprenom.setBounds(20,100,300,30);
		labprenom.setFont(new Font("Arial",Font.BOLD,18));
		labprenom.setForeground(Color.black);
		pan.add(labprenom);
		
		jtfprenom = new JTextField();
		jtfprenom.setBounds(160,100,200,25);
		jtfprenom.addKeyListener(this);
		pan.add(jtfprenom);
		
		labage = new JLabel("Age :");
		labage.setBounds(20,140,300,30);
		labage.setFont(new Font("Arial",Font.BOLD,18));
		labage.setForeground(Color.black);
		pan.add(labage);
		
		jtfage=new JTextField();
		jtfage.setBounds(160,140,200,25);
		jtfage.addKeyListener(this);
		pan.add(jtfage);
		
		labemail = new JLabel("Email :");
		labemail.setBounds(20,180,300,30);
		labemail.setFont(new Font("Arial",Font.BOLD,18));
		labemail.setForeground(Color.black);
		pan.add(labemail);
		
		jtfemail = new JTextField();
		jtfemail.setBounds(160,180,200,25);
		jtfemail.addKeyListener(this);
		pan.add(jtfemail);
		
		labtelephone = new JLabel("Telephone :");
		labtelephone.setBounds(20,220,300,30);
		labtelephone.setFont(new Font("Arial",Font.BOLD,18));
		labtelephone.setForeground(Color.black);
		pan.add(labtelephone);
		
		jtftelephone = new JTextField();
		jtftelephone.setBounds(160,220,200,25);
		jtftelephone.addKeyListener(this);
		pan.add(jtftelephone);
		
		labville = new JLabel("Ville :");
		labville.setBounds(20,260,300,30);
		labville.setFont(new Font("Arial",Font.BOLD,18));
		labville.setForeground(Color.black);
		pan.add(labville);
		
		jtfville = new JTextField();
		jtfville.setBounds(160,260,200,25);
		jtfville.addKeyListener(this);
		pan.add(jtfville);
		
		labadresse = new JLabel("Adresse :");
		labadresse.setBounds(20,300,300,30);
		labadresse.setFont(new Font("Arial",Font.BOLD,18));
		labadresse.setForeground(Color.black);
		pan.add(labadresse);
		
		jtfadresse = new JTextField();
		jtfadresse.setBounds(160,300,200,25);
		jtfadresse.addKeyListener(this);
		pan.add(jtfadresse);
		
		labpassword = new JLabel("Mot de passe :"); //Vérifier que cet id n'existe pas déjà
		labpassword.setBounds(20,340,300,30);
		labpassword.setFont(new Font("Arial",Font.BOLD,18));
		labpassword.setForeground(Color.black);
		pan.add(labpassword);
		
		jpfpassword = new JPasswordField();
		jpfpassword.setBounds(160,340,200,25);
		jpfpassword.addKeyListener(this);
		pan.add(jpfpassword);
		
		labcar = new JLabel("(9 caractères minimum)");
		labcar.setBounds(380,340,300,30);
		labcar.setFont(new Font("Arial",Font.BOLD,10));
		labcar.setForeground(Color.black);
		pan.add(labcar);
		
		
		//bouton pour annuler les modifications
		btannuler = new JButton("Annuler");
		btannuler.setBounds(370,500,150,30);
		btannuler.setBackground(Color.white);
		btannuler.setFont(new Font("Arial",Font.BOLD,18));
		btannuler.setForeground(Color.black);
		pan.add(btannuler);
		btannuler.addActionListener(this);
		
		//bouton pour enregistrer les nouvelles infos de l'utilisateur
		btajout = new JButton("Enregistrer");
		btajout.setBounds(50,500,150,30);
		btajout.setBackground(Color.white);
		btajout.setFont(new Font("Arial",Font.BOLD,18));
		btajout.setForeground(Color.black);
		pan.add(btajout);
		btajout.addActionListener(this);
		
		//ajout des infos de l'utilisateur dans les zones de texte
		updateFields();
		
	}
		
	//Méthodes
	
	
	public void afficherTailleNonValide(String info) {
		JOptionPane.showMessageDialog(this,info + " invalide, attention à respecter le nombre de caractères");
	}
	
	public void afficherAgeNonValide() {
		JOptionPane.showMessageDialog(this, "Age invalide, veuillez rentrer un nombre entier");
	}
	
	//Si l'utilisateur appuie sur le bouton "enregistrer" on affiche un pop up de confirmation
	//si confirmation on change ses infos dans la database
	//sinon on ne fait rien
	public void afficherValidationEnregistrer() {
		String[] options = {"oui", "non"};
		int choix = JOptionPane.showOptionDialog(null, "Voulez-vous vraiment enregistrer ces modifications?",
            "Confirmation enregistrement",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    	if (choix == 0) {
			try {
				MainController.setInfoOfUser(idUser,"nom",jtfnom.getText());
				MainController.setInfoOfUser(idUser,"prenom",jtfprenom.getText());
				MainController.setInfoOfUser(idUser,"age",jtfage.getText());
				MainController.setInfoOfUser(idUser,"email",jtfemail.getText());
				MainController.setInfoOfUser(idUser,"adresse",jtfadresse.getText());
				MainController.setInfoOfUser(idUser,"ville",jtfville.getText());
				MainController.setInfoOfUser(idUser,"telephone",jtftelephone.getText());
				MainController.setInfoOfUser(idUser,"mot de passe",toString(jpfpassword.getPassword()));
				dispose();
			} catch (UnexistingInfoException exc1) {
				System.out.println("erreur " + exc1.getMessage());
				dispose();
			} catch (UnexistingUserException exc2) {
				System.out.println("erreur " + exc2.getMessage());
				dispose();
			}
    	} else if (choix == 1) {
    		//on ne fait rien
    	}
	}
	
	//si l'utilisateru appuie sur le bouton "annuler" on affiche un pop up de confirmation
	//si confiramation, on revient sur son profil principal
	//sinon on ne fait rien
	public void afficherValidationAnnuler() {
		String[] options = {"oui", "non"};
		int choix = JOptionPane.showOptionDialog(null, "Voulez-vous vraiment vous vraiment annuler vos modifications?",
            "Confiramation annulation",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    	if (choix == 0) {
    		dispose();
    	} else if (choix == 1) {
    		//on ne fait rien
    	}
	}
	
	//Pour convertir le mot de passe en String
	public String toString(char[] password) {
		String result = "";
		for (int i = 0;i<password.length;i++) {
			result += password[i];
		}
		return result;
	}
	
	//pour mettre à jour les zones de texte avec les infos du user déjà enregistrées dans la database
	private void updateFields() {
		try {
			System.out.println("nom : " + MainController.getInfoOfUser(idUser,"nom"));
			jtfnom.setText(MainController.getInfoOfUser(idUser,"nom"));
			jtfprenom.setText(MainController.getInfoOfUser(idUser,"prenom"));
			jtfage.setText(MainController.getInfoOfUser(idUser,"age")); 
			jtfemail.setText(MainController.getInfoOfUser(idUser,"email"));
			jtfadresse.setText(MainController.getInfoOfUser(idUser,"adresse"));
			jtftelephone.setText(MainController.getInfoOfUser(idUser,"telephone"));
			jtfville.setText(MainController.getInfoOfUser(idUser,"ville"));
			jpfpassword.setText(MainController.getInfoOfUser(idUser,"mot de passe"));
		} catch (UnexistingInfoException exc1) {
			System.out.println("erreur " + exc1.getMessage());
			dispose();
		} catch (UnexistingUserException exc2) {
			System.out.println("erreur " + exc2.getMessage());
			dispose();
		}
	}
	public void insertUpdate(DocumentEvent e) {
        //updateFields();
    }
    public void removeUpdate(DocumentEvent e) {
        //updateFields();
    }
    public void changedUpdate(DocumentEvent e) {
        //Ne rien faire
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
	
	//pour gérer les différents boutons
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btajout)) {
			afficherValidationEnregistrer();
		}
		if (e.getSource().equals(btannuler)) {
			afficherValidationAnnuler();
		}	
	}

}



