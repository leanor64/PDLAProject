package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.*;

import Controller.NewUser;
import Model.User;

public class FormulaireConnexion extends JFrame {

	//Attributs
		JLabel labtitre, labid, labpassword;
		JTextField jtfid;
		JPasswordField jpfpassword;
		JButton btajout;

		//Constructeur
		public FormulaireConnexion(){
			this.setTitle("Connexion");
			this.setSize(400,260);
			this.setLocationRelativeTo(null);
			JPanel pan = new JPanel();
			pan.setLayout(null);
			pan.setBackground(Color.orange);
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

		}
		
		//Méthode
		
		public void afficherMDPIncorrect(String mdp) {
		
		}
		
		public String toString(char[] password) {
			String result = "";
			for (int i = 0;i<password.length;i++) {
				result += password[i];
			}
			return result;
		}
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btajout) {
				System.out.println("vous avez cliqué sur le bouton s'inscrire");
				/*try {
					User us = new User (jtfid.getText(), toString(jpfpassword.getPassword()), jtfnom.getText(), jtfprenom.getText(), Integer.parseInt(jtfage.getText()), jtfemail.getText(), jtftelephone.getText(), jtfville.getText(), jtfadresse.getText(), 0);
					NewUser u = new NewUser(us);
				} catch (SQLIntegrityConstraintViolationException exc) {
					afficherMDPIncorrect(jtfid.getText());
				}*/
			}
		}

		public static void main(String[] args) {

	     FormulaireConnexion connex = new FormulaireConnexion();
	     connex.setVisible(true);
		}

}