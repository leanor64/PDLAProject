package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

import Controller.*;
import Model.UnexistingUserException;

//interface permettant à l'utilisateur de se connecter à son compte (il s'est déjà inscrit)
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
			this.setSize(600,600);
			this.setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			
			JPanel pan = new JPanel();
			pan.setLayout(null);
			pan.setBackground(Color.white);
			add(pan);

			labtitre = new JLabel("Connexion");
			labtitre.setBounds(230,20,300,30);
			labtitre.setFont(new Font("Arial",Font.BOLD,22));
			labtitre.setForeground(Color.black);
			pan.add(labtitre);

			labid = new JLabel("Identifiant :"); //Vérifier que cet id n'existe pas déjà
			labid.setBounds(90,200,300,30);
			labid.setFont(new Font("Arial",Font.BOLD,18));
			labid.setForeground(Color.black);
			pan.add(labid);

			//zone de texte pour que l'utilisateur rentre son identifiant
			jtfid = new JTextField();
			jtfid.setBounds(230,200,200,25);
			pan.add(jtfid);

			labpassword = new JLabel("Mot de passe :"); //Vérifier que cet id n'existe pas déjà
			labpassword.setBounds(90,250,300,30);
			labpassword.setFont(new Font("Arial",Font.BOLD,18));
			labpassword.setForeground(Color.black);
			pan.add(labpassword);

			//zone de texte pour que l'utilisateur rentre son mdp
			jpfpassword = new JPasswordField();
			jpfpassword.setBounds(230,250,200,25);
			pan.add(jpfpassword);

			//bouton pour connecter l'utilisateur si les champs rentrés sont corrects
			btajout = new JButton("Se connecter");
			btajout.setBounds(215,500,180,30);
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
		
		//pour convertir le mot de passe en strign
		public String toString(char[] password) {
			String result = "";
			for (int i = 0;i<password.length;i++) {
				result += password[i];
			}
			return result;
		}
		
		//pour gérer les différents boutons
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btajout)) {
				//on vérifie que les informations rentrées sont correctes
				connexionOK = MainController.CheckConnection(jtfid.getText(),toString(jpfpassword.getPassword()));
			}
			if (connexionOK == true) {
				try {
					//on regarde le type de profil de l'utilisateur pour ouvrir la vue correspondante
					if (MainController.getTypeOfUser(jtfid.getText())== 0) { //bénévole
						ViewBenevole viewbn = new ViewBenevole(jtfid.getText());
						viewbn.setVisible(true);
					} else if (MainController.getTypeOfUser(jtfid.getText()) == 1) {//bénéficiaire
						ViewBeneficiaire viewbf = new ViewBeneficiaire(jtfid.getText());
						viewbf.setVisible(true);
					} else { //valideur
						ViewValideur viewv = new ViewValideur(jtfid.getText());
						viewv.setVisible(true);
					}
					dispose();
				} catch (UnexistingUserException excp) {
					System.out.println("erreur " + excp.getMessage());
					dispose();
				} 
			}else {
				//si infos invalides on affiche un pop up pour le signaler à l'utilisateur 
				afficherErreurConnexion();
			}
		}


}