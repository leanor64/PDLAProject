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
			
			
			this.setTitle("Bienvenue");
			this.setSize(500,500);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			
			Color custom = new Color(204, 153, 255);
			JPanel pan = new JPanel();
			pan.setLayout(null);
			pan.setBackground(custom);
			add(pan);
			
			labtitre = new JLabel("Voulez-vous aider ce bénéficiaire?");
			labtitre.setBounds(50,10,600,70);
			labtitre.setFont(new Font("Arial",Font.BOLD,22));
			labtitre.setForeground(Color.black);
			pan.add(labtitre);
			
			try {
			labtitredemande = new JLabel("Titre : " + MainController.getInfoOfDemand(numDemande, "titre"));
			labtitredemande.setBounds(100,50,600,70);
			labtitredemande.setFont(new Font("Arial",Font.BOLD,22));
			labtitredemande.setForeground(Color.black);
			pan.add(labtitredemande);
			
			labdemande = new JLabel("Détail : " + MainController.getInfoOfDemand(numDemande, "explication"));
			labdemande.setBounds(100,30,100,200);
			labdemande.setFont(new Font("Arial",Font.BOLD,22));
			labdemande.setForeground(Color.black);
			pan.add(labdemande);
			
			labbenef = new JLabel("Bénéficiaire : " + MainController.getInfoOfDemand(numDemande, "beneficiaire"));
			labbenef.setBounds(150,250,600,30);
			labbenef.setFont(new Font("Arial",Font.BOLD,22));
			labbenef.setForeground(Color.black);
			pan.add(labbenef);
			
			labville = new JLabel("Ville : " + MainController.getInfoOfDemand(numDemande, "ville"));
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
			if (e.getSource().equals(btoui)) {
				try {
				MainController.setStatusOfDemand(numDemande,StatutDemande.ACCEPTEE);
				this.setVisible(false);
				ViewBenevole vb = new ViewBenevole(idbenevole);
				vb.setVisible(true);
				} catch (UnexistingDemandException exc) {
					System.out.println("Erreur getStatusOfDemand");
				}
			}
			if (e.getSource().equals(btnon)) {
				this.setVisible(false);
				ViewBenevole vb = new ViewBenevole(idbenevole);
				vb.setVisible(true);
			}
			
		}
		
		public static void main(String[] args) {
			int num = 1;			
			String bene = "test1";
			ViewDemandeBenevole vdb = new ViewDemandeBenevole(bene,1);
		    vdb.setVisible(true);
			}

}
