package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.*;
import Model.StatutDemande;
import Model.UnexistingDemandException;
import Model.UnexistingInfoException;

//Interface pour que le valideur puisse voir une annonce plus en détails
public class ViewDemande extends JFrame implements ActionListener{

	
	//Attributs
	
		JLabel labtitre, labtitredemande, labdemande, labbenef, labville;
		JTextField jtfmotif;
		JButton btoui, btnon;
		int askmotif;
		int numDemande;
		String idvalideur;
		
		//Constructeur
		public ViewDemande(String idvalideur, int numDemande) {
			this.numDemande = numDemande;
			this.idvalideur = idvalideur;
			
			this.setTitle("Bienvenue");
			this.setSize(600,600);
			this.setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);

			JPanel pan = new JPanel();
			pan.setLayout(null);
			pan.setBackground(Color.white);
			add(pan);
			
			labtitre = new JLabel("Voulez-vous valider cette annonce?");
			labtitre.setBounds(130,10,600,70);
			labtitre.setFont(new Font("Arial",Font.BOLD,18));
			labtitre.setForeground(Color.black);
			pan.add(labtitre);
			
			try {
				//titre de la demande
				labtitredemande = new JLabel("Titre : " + MainController.getInfoOfDemand(numDemande, "titre"));
				labtitredemande.setBounds(100,100,400,30);
				labtitredemande.setFont(new Font("Arial", Font.BOLD, 18));
				labtitredemande.setForeground(Color.black);
				pan.add(labtitredemande);

				//détails de la demande
				labdemande = new JLabel("Détail : " + MainController.getInfoOfDemand(numDemande, "explication"));
				labdemande.setBounds(100, 180, 400, 200);
				labdemande.setFont(new Font("Arial", Font.BOLD, 18));
				labdemande.setForeground(Color.black);
				labdemande.setHorizontalAlignment(JLabel.LEFT); // Alignement à gauche
				labdemande.setVerticalAlignment(JLabel.TOP); // Alignement en haut
				labdemande.setVerticalTextPosition(JLabel.TOP); // Position du texte en haut
				pan.add(labdemande);
			
				//bénéficiare demandant de l'aide
				labbenef = new JLabel("Bénéficiaire : " + MainController.getInfoOfDemand(numDemande, "beneficiaire"));
				labbenef.setBounds(100,260,600,30);
				labbenef.setFont(new Font("Arial",Font.BOLD,18));
				labbenef.setForeground(Color.black);
				pan.add(labbenef);
			
				//localisation de la demande
				labville = new JLabel("Ville : " + MainController.getInfoOfDemand(numDemande, "ville"));
				labville.setBounds(100,340,600,30);
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
			
			//bouton pour valider accepter cette demande
			btoui = new JButton("OUI");
			btoui.setBounds(360,500,150,30);
			btoui.setBackground(Color.white);
			btoui.setFont(new Font("Arial",Font.BOLD,18));
			btoui.setForeground(Color.black);
			pan.add(btoui);
			btoui.addActionListener(this);
			
			//bouton pour refuser cette demande
			btnon = new JButton("NON");
			btnon.setBounds(80,500,150,30);
			btnon.setBackground(Color.white);
			btnon.setFont(new Font("Arial",Font.BOLD,18));
			btnon.setForeground(Color.black);
			pan.add(btnon);
			btnon.addActionListener(this);
			
		}
		
		//Méthodes
			
		//gestion des différents boutons
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btoui)) { //si validation
				try {
					//changement du statut de la demande dans la database 
				MainController.setStatusOfDemand(numDemande,StatutDemande.VALIDEE);
				} catch (UnexistingDemandException exc) {
					System.out.println("Erreur " + exc.getMessage());
					dispose();
				}
				//retour sur le profil du valideur
				ViewValideur vv = new ViewValideur(idvalideur);
				vv.setVisible(true);
				dispose();
			}
			if (e.getSource().equals(btnon)) { //si refus
				//affichage d'un pop up de confimation et demande du motif du refus
				String[] options = {"expirée", "inappropriée", "autre"};
		        int motif = JOptionPane.showOptionDialog(null, "Pourquoi estimez-vous que cette annonce est invalidable?",
		                "Choisissez un motif",
		                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		        try {
		        	//selon le motif sélectionner par le valideru, changement du statut de la demande dans la database
		        	if (motif == 0) {
		        		MainController.setStatusOfDemand(numDemande,StatutDemande.REFUSEE_EXPIREE);
		        	} else if (motif == 1) {
		        		MainController.setStatusOfDemand(numDemande,StatutDemande.REFUSEE_INAPPROPRIEE);
		        	} else {
		        		MainController.setStatusOfDemand(numDemande,StatutDemande.REFUSE_AUTRE);
		        	}
		        } catch (UnexistingDemandException exc) {
					System.out.println("Erreur " + exc.getMessage());
					dispose();
		        }
		        //retour au profil du valideur
		        dispose();
			}
			
		}
		//TODO :  a enlever
		public static void main(String[] args) {
			String valideur = "test2";
			int num = 1;			
			ViewDemande vde = new ViewDemande(valideur,1);
		    vde.setVisible(true);
			}

}
