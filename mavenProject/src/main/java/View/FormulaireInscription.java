package View;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.*;
import Model.BadLengthException;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLIntegrityConstraintViolationException;

public class FormulaireInscription  extends JFrame implements ActionListener, ListSelectionListener, KeyListener{

	
	//Attributs
	JLabel labtitre, labnom, labprenom, labage, labemail, labtelephone, labville, labadresse, labid, labpassword,labcar, labtype;
	JTextField jtfnom,jtfprenom,jtfage, jtfemail, jtftelephone, jtfville, jtfadresse, jtfid;
	int type;
	JPasswordField jpfpassword;
	JButton btajout;
	
	JList<String> liste = new JList<>();
	JLabel etiquette = new JLabel(" ");
	String choix[] = {"Bénévole", "Bénéficiaire", "Valideur"};
	
	boolean inscriptionOK;
	
	//Constructeur
	public FormulaireInscription(){
		
		this.setTitle("Inscription");
		this.setSize(550,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		inscriptionOK = false;
		
		Color custom = new Color(204, 153, 255);
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(custom);
		add(pan);
		
		labtitre = new JLabel("Formulaire d'inscription");
		labtitre.setBounds(150,10,300,30);
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
		
		labid = new JLabel("Identifiant :"); //Vérifier que cet id n'existe pas déjà
		labid.setBounds(20,340,300,30);
		labid.setFont(new Font("Arial",Font.BOLD,18));
		labid.setForeground(Color.black);
		pan.add(labid);
		
		jtfid = new JTextField();
		jtfid.setBounds(160,340,200,25);
		jtfid.addKeyListener(this);
		pan.add(jtfid);
		
		labpassword = new JLabel("Mot de passe :"); //Vérifier que cet id n'existe pas déjà
		labpassword.setBounds(20,380,300,30);
		labpassword.setFont(new Font("Arial",Font.BOLD,18));
		labpassword.setForeground(Color.black);
		pan.add(labpassword);
		
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
		
		liste = new JList<>(choix);
		liste.setBounds(320,420,200,55);
		liste.addListSelectionListener(this);
		pan.add(etiquette);
		pan.add(liste);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		etiquette.setText((String)liste.getSelectedValue());
		
		btajout = new JButton("S'inscrire");
		btajout.setBounds(175,520,150,30);
		btajout.setBackground(Color.white);
		btajout.setFont(new Font("Arial",Font.BOLD,18));
		btajout.setForeground(Color.black);
		pan.add(btajout);
		btajout.addActionListener(this);
	}
		
	//Méthodes
	
	public void afficherIDDejaUtilise() {
		JOptionPane.showMessageDialog(this, "Identifiant déjà utilisé, veuillez en saisir un nouveau");
	}
	
	public void afficherTailleNonValide(String info) {
		JOptionPane.showMessageDialog(this,info + " invalide, attention à respecter le nombre de caractères");
	}
	
	public void afficherAgeNonValide() {
		JOptionPane.showMessageDialog(this, "Age invalide, veuillez rentrer un nombre entier");
	}
	
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
	
	
	/*
	 * 0 : Bénévole
	 * 1 : Bénéficiaire
	 * 2 : Valideur
	*/
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
		
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btajout) {
			//System.out.println("vous avez cliqué sur le bouton s'inscrire");
			try {
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
		if (inscriptionOK == true) {
			this.setVisible(false);
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
		}
	}
	

	public static void main(String[] args) {
		
     FormulaireInscription form = new FormulaireInscription();
     form.setVisible(true);
	}

}



