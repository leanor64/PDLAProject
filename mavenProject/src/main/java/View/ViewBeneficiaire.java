package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.MainController;
import Controller.UnexistingDemandException;
import Controller.UnexistingInfoException;
import Model.StatutDemande;

public class ViewBeneficiaire extends JFrame implements ActionListener, ItemListener {

	//TODO : bouton actualiser pour actualiser lors d'un changement de statut
	//Attributs
	
	String idbeneficiaire, idbenevole;
	JLabel labtitre, labaevaluer, labacceptee;
	JButton btposterannonce, btdonneravis, btdeconnexion, btterminee;
	
	DefaultComboBoxModel<String> demandesAcceptees;
	JComboBox<String> choixDemandesAcceptees;
	
	DefaultComboBoxModel<String> demandesAEvaluer;
	JComboBox<String> choixDemandesAEvaluer;
	
	//Constructeur
	public ViewBeneficiaire(String idbeneficiaire) {
		this.idbeneficiaire = idbeneficiaire;
		
		this.setTitle("Bienvenue " + idbeneficiaire);
		this.setSize(700,700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		Color custom = new Color(204, 153, 255);
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(custom);
		add(pan);
		
		labtitre = new JLabel("Votre compte");
		labtitre.setBounds(270,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre);
		
		btposterannonce = new JButton("POSTER UNE ANNONCE");
		btposterannonce.setBounds(200,150,300,50);
		btposterannonce.setBackground(Color.white);
		btposterannonce.setFont(new Font("Arial",Font.BOLD,18));
		btposterannonce.setForeground(Color.black);
		pan.add(btposterannonce);
		btposterannonce.addActionListener(this);
		
		labacceptee = new JLabel("Vos annonces en cours");
		labacceptee.setBounds(60,300,300,30);
		labacceptee.setFont(new Font("Arial",Font.BOLD,22));
		labacceptee.setForeground(Color.black);
		pan.add(labacceptee);
		
		demandesAcceptees = new DefaultComboBoxModel<>();
				

		for (int demande : MainController.getDemandsOfBeneficiaire(idbeneficiaire)){
			try {
				if (MainController.getInfoOfDemand(demande, "statut").equals("ACCEPTEE")) {
					demandesAcceptees.addElement(demande + " " + MainController.getInfoOfDemand(demande, "titre") + "      " + MainController.getInfoOfDemand(demande, "benevole"));
				}
			} catch (UnexistingInfoException exc1) {
				System.out.println("erreur getInfoOfDemand()");
			} catch (UnexistingDemandException exc2) {
				System.out.println("erreur getInfoOfDemand()");				} 
		}
		if (demandesAcceptees.getSize() == 0) {
			demandesAcceptees.addElement("Aucune annonce");
		}
	
			
		choixDemandesAcceptees = new JComboBox<>(demandesAcceptees);
		pan.add(choixDemandesAcceptees);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		choixDemandesAcceptees.setSelectedIndex(0);
		choixDemandesAcceptees.setBounds(100,400,200,30);
		choixDemandesAcceptees.addItemListener(this);
		
		btterminee = new JButton("MARQUER COMME TERMINEE");
		btterminee.setBounds(10,500,340,50);
		btterminee.setBackground(Color.white);
		btterminee.setFont(new Font("Arial",Font.BOLD,18));
		btterminee.setForeground(Color.black);
		pan.add(btterminee);
		btterminee.addActionListener(this);
		
		labaevaluer = new JLabel("Vos annonces à évaluer");
		labaevaluer.setBounds(380,300,300,30);
		labaevaluer.setFont(new Font("Arial",Font.BOLD,22));
		labaevaluer.setForeground(Color.black);
		pan.add(labaevaluer);
		
		demandesAEvaluer = new DefaultComboBoxModel<>();
		
		for (int demande : MainController.getDemandsOfBeneficiaire(idbeneficiaire)){
			try {
				if (MainController.getInfoOfDemand(demande, "statut").equals("TERMINEE_PAS_EVALUEE")) {
					demandesAEvaluer.addElement(demande + " " + MainController.getInfoOfDemand(demande, "titre") + "      " + MainController.getInfoOfDemand(demande, "benevole"));
				}
			} catch (UnexistingInfoException exc1) {
				System.out.println("erreur getInfoOfDemand()");
			} catch (UnexistingDemandException exc2) {
				System.out.println("erreur getInfoOfDemand()");
			} 
		}
		if (demandesAEvaluer.getSize() == 0) {
			demandesAEvaluer.addElement("Aucune annonce");			
		}
	
		choixDemandesAEvaluer = new JComboBox<>(demandesAEvaluer);
		pan.add(choixDemandesAEvaluer);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		choixDemandesAEvaluer.setSelectedIndex(0);
		choixDemandesAEvaluer.setBounds(390,400,200,30);
		choixDemandesAEvaluer.addItemListener(this);
		
		btdonneravis = new JButton("DONNER UN AVIS");
		btdonneravis.setBounds(380,500,300,50);
		btdonneravis.setBackground(Color.white);
		btdonneravis.setFont(new Font("Arial",Font.BOLD,18));
		btdonneravis.setForeground(Color.black);
		pan.add(btdonneravis);
		btdonneravis.addActionListener(this);
		
		btdeconnexion = new JButton("DECONNEXION");
		btdeconnexion.setBounds(190,600,300,30);
		btdeconnexion.setBackground(Color.white);
		btdeconnexion.setFont(new Font("Arial",Font.BOLD,18));
		btdeconnexion.setForeground(Color.black);
		pan.add(btdeconnexion);
		btdeconnexion.addActionListener(this);
	}
	
	//Méthodes
	
	public void afficherAucuneAnnonce() {
		JOptionPane.showMessageDialog(this, "Vous n'avez aucune annonce correspondante");
	}
	
	public void itemStateChanged(ItemEvent i) {
		//null
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btposterannonce)) {
			FormulaireDemande fd = new FormulaireDemande(idbeneficiaire);
			fd.setVisible(true);
		}
		
		try {
			String[] wordAE = ((String) choixDemandesAEvaluer.getSelectedItem()).split(" ");
			String[] wordAC = ((String) choixDemandesAcceptees.getSelectedItem()).split(" ");
			if (e.getSource().equals(btdonneravis)) {
				if ((String)choixDemandesAEvaluer.getSelectedItem() == "Aucune annonce"){
					afficherAucuneAnnonce();
				} else {
					System.out.println("benev : " + MainController.getInfoOfDemand(Integer.parseInt(wordAE[0]),"benevole"));
					LaisserAvis la = new LaisserAvis(Integer.parseInt(wordAE[0]),idbeneficiaire,MainController.getInfoOfDemand(Integer.parseInt(wordAE[0]),"benevole"));
					la.setVisible(true);
				}
			}
			if (e.getSource().equals(btterminee)) {
				if ((String)choixDemandesAcceptees.getSelectedItem() == "Aucune annonce"){
					afficherAucuneAnnonce();
				} else {
					String[] options = {"oui", "non"};
			        int validation = JOptionPane.showOptionDialog(null, "Êtes vous sûr de vouloir mettre cette annonce comme terminée?",
			                "Confirmation",
			                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			        try {
			        	if (validation == 0) {
			        		MainController.setStatusOfDemand(Integer.parseInt(wordAC[0]), StatutDemande.TERMINEE_PAS_EVALUEE);
			        	} else {
			        		//on ne fait rien
			        	}
			        } catch (UnexistingDemandException exc) {
						System.out.println("Erreur getStatusOfDemand");
			        }
				}
			}
		
		} catch (UnexistingInfoException exc1) {
			System.out.println("erreur getInfoOfDemand()");
		} catch (UnexistingDemandException exc2) {
			System.out.println("erreur getInfoOfDemand()");
		} 
			
		
		if (e.getSource().equals(btdeconnexion)) {
			this.setVisible(false);
			FormulaireConnexion fco = new FormulaireConnexion();
			fco.setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		String beneficiaire;
		beneficiaire = "benef";
		
		ViewBeneficiaire vbf = new ViewBeneficiaire(beneficiaire);
	    vbf.setVisible(true);
		}
}
