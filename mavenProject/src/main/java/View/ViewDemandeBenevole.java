package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.*;
import Model.StatutDemande;
import Model.UnexistingDemandException;
import Model.UnexistingInfoException;
import Model.UnexistingUserException;

//interface pour qu'un bénévole voit une annonce plus en détail et puisse l'accepter ou non
public class ViewDemandeBenevole extends JFrame implements ActionListener{

	
	//Attributs
	
		JLabel labtitre, labtitredemande, labdemande, labbenef, labville;
		JTextField jtfmotif;
		JButton btoui, btnon;
		int numDemande;
		String idbenevole;
		
		//Constructeur
		public ViewDemandeBenevole(String idbenevole,int numDemande) {
			this.numDemande = numDemande;
			this.idbenevole = idbenevole;
			
			
			this.setTitle("Demande n° " + numDemande);
			this.setSize(600,600);
			this.setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);

			JPanel pan = new JPanel();
			pan.setLayout(null);
			pan.setBackground(Color.white);
			add(pan);
			
			labtitre = new JLabel("Voulez-vous aider ce bénéficiaire?");
			labtitre.setBounds(120,20,600,70);
			labtitre.setFont(new Font("Arial",Font.BOLD,22));
			labtitre.setForeground(Color.black);
			pan.add(labtitre);
			
			try {
				//titre de la demande
				labtitredemande = new JLabel("Titre : " + MainController.getInfoOfDemand(numDemande, "titre"));
				labtitredemande.setBounds(100,100,600,70);
				labtitredemande.setFont(new Font("Arial",Font.BOLD,18));
				labtitredemande.setForeground(Color.black);
				pan.add(labtitredemande);
			
				//détails de la demande
				labdemande = new JLabel("Détail : " + MainController.getInfoOfDemand(numDemande, "explication"));
				labdemande.setBounds(100,150,300,200);
				labdemande.setFont(new Font("Arial",Font.BOLD,18));
				labdemande.setForeground(Color.black);
				pan.add(labdemande);
			
				//bénéficiaire demande de l'aide
				labbenef = new JLabel("Bénéficiaire : " + MainController.getInfoOfDemand(numDemande, "beneficiaire"));
				labbenef.setBounds(100,300,600,30);
				labbenef.setFont(new Font("Arial",Font.BOLD,18));
				labbenef.setForeground(Color.black);
				pan.add(labbenef);
			
				//localisation de la demande
				labville = new JLabel("Ville : " + MainController.getInfoOfDemand(numDemande, "ville"));
				labville.setBounds(100,350,400,30);
				labville.setFont(new Font("Arial",Font.BOLD,18));
				labville.setForeground(Color.black);
				pan.add(labville);
			} catch (UnexistingInfoException exc1) {
				System.out.println("erreur " + exc1.getMessage());
				dispose();
			} catch (UnexistingDemandException exc2) {
				System.out.println("erreur " + exc2.getMessage());
				dispose();
			}
			//bouton pour accepter la demande
			btoui = new JButton("OUI");
			btoui.setBounds(330,500,150,30);
			btoui.setBackground(Color.white);
			btoui.setFont(new Font("Arial",Font.BOLD,18));
			btoui.setForeground(Color.black);
			pan.add(btoui);
			btoui.addActionListener(this);
			
			//bouton pour revenir en arrière
			btnon = new JButton("NON");
			btnon.setBounds(40,500,150,30);
			btnon.setBackground(Color.white);
			btnon.setFont(new Font("Arial",Font.BOLD,18));
			btnon.setForeground(Color.black);
			pan.add(btnon);
			btnon.addActionListener(this);
			
		}
		
		//Méthodes
			
		//méthode pour gérer les différents boutons
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btoui)) {
				try {
					//changement des infos dans la database
					MainController.setBenevoleOfDemand(numDemande,idbenevole);
					MainController.getInfoOfDemand(numDemande,"benevole");
					MainController.setStatusOfDemand(numDemande,StatutDemande.ACCEPTEE);
					//retour sur le profil principal du bénévole
					this.setVisible(false);
					dispose();
				} catch (UnexistingDemandException exc) {
					System.out.println("Erreur " + exc.getMessage());
					dispose();
				} catch (UnexistingInfoException exc2) {
					System.out.println("Erreur " + exc2.getMessage());
					dispose();
				} catch (UnexistingUserException exc3) {
					System.out.println("Erreur " + exc3.getMessage());
					dispose();
				}
			}
			if (e.getSource().equals(btnon)) {
				//retour sur le profil principal du bénévole
				ViewBenevole vb = new ViewBenevole(idbenevole);
				vb.setVisible(true);
				dispose();
			}
			
		}

}
