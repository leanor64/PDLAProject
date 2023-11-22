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

public class ViewDemande extends JFrame implements ActionListener{
	
		MainController controller = new MainController();
	
	//Attributs
	
		JLabel labtitre, labtitredemande, labdemande, labbenef, labville;
		JTextField jtfmotif;
		JButton btoui, btnon;
		int askmotif;
		int numDemande;
		
		//Constructeur
		public ViewDemande(int numDemande) {
			this.numDemande = numDemande;
			
			
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
			labtitre.setFont(new Font("Arial",Font.BOLD,22));
			labtitre.setForeground(Color.black);
			pan.add(labtitre);
			
			try {
			labtitredemande = new JLabel("Titre : " + controller.getInfoOfDemand(numDemande, "titre"));
			labtitredemande.setBounds(100,50,600,70);
			labtitredemande.setFont(new Font("Arial",Font.BOLD,22));
			labtitredemande.setForeground(Color.black);
			pan.add(labtitredemande);
			
			labdemande = new JLabel("Détail : " + controller.getInfoOfDemand(numDemande, "explication"));
			labdemande.setBounds(100,30,100,200);
			labdemande.setFont(new Font("Arial",Font.BOLD,22));
			labdemande.setForeground(Color.black);
			pan.add(labdemande);
			
			labbenef = new JLabel("Bénéficiaire : " + controller.getInfoOfDemand(numDemande, "beneficiaire"));
			labbenef.setBounds(150,250,600,30);
			labbenef.setFont(new Font("Arial",Font.BOLD,22));
			labbenef.setForeground(Color.black);
			pan.add(labbenef);
			
			labville = new JLabel("Ville : " + controller.getInfoOfDemand(numDemande, "ville"));
			labville.setBounds(150,290,600,30);
			labville.setFont(new Font("Arial",Font.BOLD,22));
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
			if (e.getSource() == btoui) {
				try {
				controller.setStatusOfDemand(numDemande,StatutDemande.VALIDEE);
				} catch (UnexistingDemandException exc) {
					System.out.println("Erreur getStatusOfDemand");
				}
				this.setVisible(false);
			}
			if (e.getSource() == btnon) {
				//changer statut de l'annonce à refusée
				String[] options = {"expirée", "inappropriée", "autre"};
		        int motif = JOptionPane.showOptionDialog(null, "Pourquoi estimez-vous que cette annonce est invalidable?",
		                "Choisissez un motif",
		                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		        try {
		        	if (motif == 0) {
		        		controller.setStatusOfDemand(numDemande,StatutDemande.REFUSEE_EXPIREE);
		        	} else if (motif == 1) {
		        		controller.setStatusOfDemand(numDemande,StatutDemande.REFUSEE_INAPPROPRIEE);
		        	} else {
		        		controller.setStatusOfDemand(numDemande,StatutDemande.REFUSE_AUTRE);
		        	}
		        } catch (UnexistingDemandException exc) {
					System.out.println("Erreur getStatusOfDemand");
		        }
		        this.setVisible(false);
			}
			
		}
		
		public static void main(String[] args) {
			int num = 1;			
			ViewDemande vde = new ViewDemande(1);
		    vde.setVisible(true);
			}

}
