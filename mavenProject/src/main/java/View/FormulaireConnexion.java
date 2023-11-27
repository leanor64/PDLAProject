package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

import Controller.*;
import Model.UnexistingUserException;

public class FormulaireConnexion extends JFrame implements ActionListener {
	

	//Attributs
		JLabel labtitre, labid, labpassword;
		JTextField jtfid;
		JPasswordField jpfpassword;
		JButton btajout;
		boolean connexionOK;

		//Constructeur
		public FormulaireConnexion(){
			
			connexionOK = false;
			
			this.setTitle("Connexion");
			this.setSize(400,260);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			
			Color custom = new Color(204, 153, 255);
			JPanel pan = new JPanel();
			pan.setLayout(null);
			pan.setBackground(custom);
			add(pan);

			labtitre = new JLabel("Connexion");
			labtitre.setBounds(60,10,300,30);
			labtitre.setFont(new Font("Arial",Font.BOLD,22));
			labtitre.setForeground(Color.black);
			pan.add(labtitre);

			labid = new JLabel("Identifiant :"); //Vérifier que cet id n'existe pas déjà
			labid.setBounds(20,60,300,30);
			labid.setFont(new Font("Arial",Font.BOLD,18));
			labid.setForeground(Color.black);
			pan.add(labid);

			jtfid = new JTextField();
			jtfid.setBounds(160,60,200,25);
			pan.add(jtfid);

			labpassword = new JLabel("Mot de passe :"); //Vérifier que cet id n'existe pas déjà
			labpassword.setBounds(20,100,300,30);
			labpassword.setFont(new Font("Arial",Font.BOLD,18));
			labpassword.setForeground(Color.black);
			pan.add(labpassword);

			jpfpassword = new JPasswordField();
			jpfpassword.setBounds(160,100,200,25);
			pan.add(jpfpassword);

			btajout = new JButton("Se connecter");
			btajout.setBounds(150,150,180,30);
			btajout.setBackground(Color.white);
			btajout.setFont(new Font("Arial",Font.BOLD,18));
			btajout.setForeground(Color.black);
			pan.add(btajout);
			btajout.addActionListener(this);

		}
		
		//Méthodes
		
		public void afficherErreurConnexion() {
			JOptionPane.showMessageDialog(this, "Mot de passe ou identifiant incorrect, retentez de nouveau");
		}
		
		public String toString(char[] password) {
			String result = "";
			for (int i = 0;i<password.length;i++) {
				result += password[i];
			}
			return result;
		}
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btajout)) {
				connexionOK = MainController.CheckConnection(jtfid.getText(),toString(jpfpassword.getPassword()));
						
			}
			if (connexionOK == true) {
				this.setVisible(false);
				try {
					if (MainController.getTypeOfUser(jtfid.getText())== 0) {
						ViewBenevole viewbn = new ViewBenevole(jtfid.getText());
						viewbn.setVisible(true);
					} else if (MainController.getTypeOfUser(jtfid.getText()) == 1) {
						ViewBeneficiaire viewbf = new ViewBeneficiaire(jtfid.getText());
						viewbf.setVisible(true);
					} else {
						ViewValideur viewv = new ViewValideur(jtfid.getText());
						viewv.setVisible(true);
					}
				} catch (UnexistingUserException excp) {
					afficherErreurConnexion();
				} 
			}else {
				afficherErreurConnexion();
			}
		}

		public static void main(String[] args) {

	     FormulaireConnexion connex = new FormulaireConnexion();
	     connex.setVisible(true);
		}

}