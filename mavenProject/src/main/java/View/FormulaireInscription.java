package View;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.NewUser;
import Model.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;

public class FormulaireInscription  extends JFrame implements ActionListener, ListSelectionListener{
	
	//Attributs
	JLabel labtitre, labnom, labprenom, labage, labemail, labtelephone, labville, labadresse, labid, labpassword, labtype;
	JTextField jtfnom,jtfprenom,jtfage, jtfemail, jtftelephone, jtfville, jtfadresse, jtfid;
	int type;
	JPasswordField jpfpassword;
	JButton btajout;
	
	JList liste = new JList();
	JLabel etiquette = new JLabel(" ");
	String choix[] = {"Bénévole", "Bénéficiaire", "Valideur"};
	
	//Constructeur
	public FormulaireInscription(){
		this.setTitle("Inscription");
		this.setSize(400,540);
		this.setLocationRelativeTo(null);
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(Color.orange);
		add(pan);
		
		labtitre = new JLabel("Formulaire d'inscription");
		labtitre.setBounds(60,10,300,30);
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
		pan.add(jtfnom);
		
		labprenom = new JLabel("Prénom :");
		labprenom.setBounds(20,100,300,30);
		labprenom.setFont(new Font("Arial",Font.BOLD,18));
		labprenom.setForeground(Color.black);
		pan.add(labprenom);
		
		jtfprenom = new JTextField();
		jtfprenom.setBounds(160,100,200,25);
		pan.add(jtfprenom);
		
		labage = new JLabel("Age :");
		labage.setBounds(20,140,300,30);
		labage.setFont(new Font("Arial",Font.BOLD,18));
		labage.setForeground(Color.black);
		pan.add(labage);
		
		jtfage=new JTextField();
		jtfage.setBounds(160,140,200,25);
		pan.add(jtfage);
		
		labemail = new JLabel("Email :");
		labemail.setBounds(20,180,300,30);
		labemail.setFont(new Font("Arial",Font.BOLD,18));
		labemail.setForeground(Color.black);
		pan.add(labemail);
		
		jtfemail = new JTextField();
		jtfemail.setBounds(160,180,200,25);
		pan.add(jtfemail);
		
		labtelephone = new JLabel("Telephone :");
		labtelephone.setBounds(20,220,300,30);
		labtelephone.setFont(new Font("Arial",Font.BOLD,18));
		labtelephone.setForeground(Color.black);
		pan.add(labtelephone);
		
		jtftelephone = new JTextField();
		jtftelephone.setBounds(160,220,200,25);
		pan.add(jtftelephone);
		
		labville = new JLabel("Ville :");
		labville.setBounds(20,260,300,30);
		labville.setFont(new Font("Arial",Font.BOLD,18));
		labville.setForeground(Color.black);
		pan.add(labville);
		
		jtfville = new JTextField();
		jtfville.setBounds(160,260,200,25);
		pan.add(jtfville);
		
		labadresse = new JLabel("Adresse :");
		labadresse.setBounds(20,300,300,30);
		labadresse.setFont(new Font("Arial",Font.BOLD,18));
		labadresse.setForeground(Color.black);
		pan.add(labadresse);
		
		jtfadresse = new JTextField();
		jtfadresse.setBounds(160,300,200,25);
		pan.add(jtfadresse);
		
		labid = new JLabel("Identifiant :"); //Vérifier que cet id n'existe pas déjà
		labid.setBounds(20,340,300,30);
		labid.setFont(new Font("Arial",Font.BOLD,18));
		labid.setForeground(Color.black);
		pan.add(labid);
		
		jtfid = new JTextField();
		jtfid.setBounds(160,340,200,25);
		pan.add(jtfid);
		
		labpassword = new JLabel("Mot de passe :"); //Vérifier que cet id n'existe pas déjà
		labpassword.setBounds(20,380,300,30);
		labpassword.setFont(new Font("Arial",Font.BOLD,18));
		labpassword.setForeground(Color.black);
		pan.add(labpassword);
		
		jpfpassword = new JPasswordField();
		jpfpassword.setBounds(160,380,200,25);
		pan.add(jpfpassword);
		
		labtype = new JLabel("Sélectionner un type de profil :");
		labtype.setBounds(20,420,300,30);
		labtype.setFont(new Font("Arial",Font.BOLD,18));
		labtype.setForeground(Color.black);
		pan.add(labtype);
		
		liste = new JList(choix);
		liste.setBounds(320,420,200,55);
		liste.addListSelectionListener(this);
		pan.add(etiquette);
		pan.add(liste);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		etiquette.setText((String)liste.getSelectedValue());
		//Revoir le choix de la note
		
		btajout = new JButton("S'inscrire");
		btajout.setBounds(150,500,150,30);
		btajout.setBackground(Color.white);
		btajout.setFont(new Font("Arial",Font.BOLD,18));
		btajout.setForeground(Color.black);
		pan.add(btajout);
		btajout.addActionListener(this);
	}
		
	//Méthodes
	
	public void AfficherIDDejaUtilise() {
		JOptionPane.showMessageDialog(this, "Identifiant déjà utilisé, veuillez en saisir un nouveau");
	}
	
	public String toString(char[] password) {
		String result = "";
		for (int i = 0;i<password.length;i++) {
			result += password[i];
		}
		return result;
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
			System.out.println("vous avez cliqué sur le bouton s'inscrire");
			try {
				NewUser u = new NewUser(jtfid.getText(), toString(jpfpassword.getPassword()), jtfnom.getText(),
						jtfprenom.getText(), Integer.parseInt(jtfage.getText()), jtfemail.getText(),
						jtftelephone.getText(), jtfville.getText(), jtfadresse.getText(), 0, type);
			} catch (SQLIntegrityConstraintViolationException exc) {
				AfficherIDDejaUtilise();
			}
		}
	}
	

	public static void main(String[] args) {
		
     FormulaireInscription form = new FormulaireInscription();
     form.setVisible(true);
	}

}



