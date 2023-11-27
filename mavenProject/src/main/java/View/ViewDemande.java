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
			this.setSize(500,500);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			
			Color custom = new Color(204, 153, 255);
			JPanel pan = new JPanel();
			pan.setLayout(null);
			pan.setBackground(custom);
			add(pan);
			
			labtitre = new JLabel("Voulez-vous valider cette annonce?");
			labtitre.setBounds(50,10,600,70);
			labtitre.setFont(new Font("Arial",Font.BOLD,18));
			labtitre.setForeground(Color.black);
			pan.add(labtitre);
			
			try {
				labtitredemande = new JLabel("Titre : " + MainController.getInfoOfDemand(numDemande, "titre"));
				labtitredemande.setBounds(100, 100, 400, 30);
				labtitredemande.setFont(new Font("Arial", Font.BOLD, 18));
				labtitredemande.setForeground(Color.black);
				pan.add(labtitredemande);

				labdemande = new JLabel("Détail : " + MainController.getInfoOfDemand(numDemande, "explication"));
				labdemande.setBounds(100, 150, 400, 200);
				labdemande.setFont(new Font("Arial", Font.BOLD, 18));
				labdemande.setForeground(Color.black);
				labdemande.setHorizontalAlignment(JLabel.LEFT); // Alignement à gauche
				labdemande.setVerticalAlignment(JLabel.TOP); // Alignement en haut
				labdemande.setVerticalTextPosition(JLabel.TOP); // Position du texte en haut
				pan.add(labdemande);
			
				labbenef = new JLabel("Bénéficiaire : " + MainController.getInfoOfDemand(numDemande, "beneficiaire"));
				labbenef.setBounds(100,250,600,30);
				labbenef.setFont(new Font("Arial",Font.BOLD,18));
				labbenef.setForeground(Color.black);
				pan.add(labbenef);
			
				labville = new JLabel("Ville : " + MainController.getInfoOfDemand(numDemande, "ville"));
				labville.setBounds(100,300,600,30);
				labville.setFont(new Font("Arial",Font.BOLD,18));
				labville.setForeground(Color.black);
				pan.add(labville);
			} catch (UnexistingInfoException exc1) {
				System.out.println("erreur getInfoOfDemand()");
			} catch (UnexistingDemandException exc2) {
				System.out.println("erreur getInfoOfDemand()");
			}
			
			btoui = new JButton("OUI");
			btoui.setBounds(310,350,150,30);
			btoui.setBackground(Color.white);
			btoui.setFont(new Font("Arial",Font.BOLD,18));
			btoui.setForeground(Color.black);
			pan.add(btoui);
			btoui.addActionListener(this);
			
			btnon = new JButton("NON");
			btnon.setBounds(20,350,150,30);
			btnon.setBackground(Color.white);
			btnon.setFont(new Font("Arial",Font.BOLD,18));
			btnon.setForeground(Color.black);
			pan.add(btnon);
			btnon.addActionListener(this);
			
		}
		
		//Méthodes
			
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btoui)) {
				try {
				MainController.setStatusOfDemand(numDemande,StatutDemande.VALIDEE);
				} catch (UnexistingDemandException exc) {
					System.out.println("Erreur getStatusOfDemand");
				}
				this.setVisible(false);
				ViewValideur vv = new ViewValideur(idvalideur);
				vv.setVisible(true);
			}
			if (e.getSource().equals(btnon)) {
				String[] options = {"expirée", "inappropriée", "autre"};
		        int motif = JOptionPane.showOptionDialog(null, "Pourquoi estimez-vous que cette annonce est invalidable?",
		                "Choisissez un motif",
		                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		        try {
		        	if (motif == 0) {
		        		MainController.setStatusOfDemand(numDemande,StatutDemande.REFUSEE_EXPIREE);
		        	} else if (motif == 1) {
		        		MainController.setStatusOfDemand(numDemande,StatutDemande.REFUSEE_INAPPROPRIEE);
		        	} else {
		        		MainController.setStatusOfDemand(numDemande,StatutDemande.REFUSE_AUTRE);
		        	}
		        } catch (UnexistingDemandException exc) {
					System.out.println("Erreur getStatusOfDemand");
		        }
		        this.setVisible(false);
			}
			
		}
		
		public static void main(String[] args) {
			String valideur = "test2";
			int num = 1;			
			ViewDemande vde = new ViewDemande(valideur,1);
		    vde.setVisible(true);
			}

}
