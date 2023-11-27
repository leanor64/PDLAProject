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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.MainController;
import Model.StatutDemande;
import Model.UnexistingDemandException;
import Model.UnexistingInfoException;

public class ViewBeneficiaire extends JFrame implements ActionListener, ItemListener, ListSelectionListener {

	//TODO : bouton actualiser pour actualiser lors d'un changement de statut
	//Attributs
	
	String idbeneficiaire, idbenevole;
	JLabel labtitre, labaevaluer, labacceptee;
	JButton btposterannonce, btdonneravis, btdeconnexion, btterminee, btvoiravis, btoptionok, btretour;
	
	JList<String> liste;
	JLabel etiquette;
	String choix[] = {"Enregistrer une annonce comme terminée & voir les avis d'un bénévole", "Evaluer un bénévole"};
	
	DefaultComboBoxModel<String> demandesAcceptees;
	JComboBox<String> choixDemandesAcceptees;
	DefaultComboBoxModel<String> demandesAEvaluer;
	JComboBox<String> choixDemandesAEvaluer;
	
	//Constructeur
	public ViewBeneficiaire(String idbeneficiaire) {
		this.idbeneficiaire = idbeneficiaire;
		
		this.setTitle("Bienvenue " + idbeneficiaire);
		this.setSize(600,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(Color.white);
		add(pan);
		
		labtitre = new JLabel("Votre compte");
		labtitre.setBounds(220,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre);
		
		btposterannonce = new JButton("POSTER UNE ANNONCE");
		btposterannonce.setBounds(150,100,300,50);
		btposterannonce.setBackground(Color.white);
		btposterannonce.setFont(new Font("Arial",Font.BOLD,18));
		btposterannonce.setForeground(Color.black);
		pan.add(btposterannonce);
		btposterannonce.addActionListener(this);
		
		liste = new JList<>(choix);
		liste.setBounds(100,250,500,55);
		liste.addListSelectionListener(this);
		etiquette = new JLabel(" ");
		pan.add(etiquette);
		pan.add(liste);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		etiquette.setText((String)liste.getSelectedValue());
		
		btoptionok = new JButton("SUIVANT");
		btoptionok.setBounds(220,320,150,30);
		btoptionok.setBackground(Color.white);
		btoptionok.setFont(new Font("Arial",Font.BOLD,18));
		btoptionok.setForeground(Color.black);
		pan.add(btoptionok);
		btoptionok.addActionListener(this);
		
		// Beneficiaire peut modifier l'état d'une annonce à TERMINEE
		
		labacceptee = new JLabel("Vos annonces en cours");
		labacceptee.setBounds(170,250,300,30);
		labacceptee.setFont(new Font("Arial",Font.BOLD,22));
		labacceptee.setForeground(Color.black);
		pan.add(labacceptee);
		labacceptee.setVisible(false);
		
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
		choixDemandesAcceptees.setBounds(120,300,320,30);
		choixDemandesAcceptees.addItemListener(this);
		choixDemandesAcceptees.setVisible(false);
		
		btterminee = new JButton("MARQUER COMME TERMINEE");
		btterminee.setBounds(30,350,250,30);
		btterminee.setBackground(Color.white);
		btterminee.setFont(new Font("Arial",Font.BOLD,13));
		btterminee.setForeground(Color.black);
		pan.add(btterminee);
		btterminee.addActionListener(this);
		btterminee.setVisible(false);
		
		btvoiravis = new JButton("VOIR AVIS BENEVOLE");
		btvoiravis.setBounds(320,350,250,30);
		btvoiravis.setBackground(Color.white);
		btvoiravis.setFont(new Font("Arial",Font.BOLD,13));
		btvoiravis.setForeground(Color.black);
		pan.add(btvoiravis);
		btvoiravis.addActionListener(this);
		btvoiravis.setVisible(false);
		
		
		// Bénéficiaire peut donner un avis à un bénévole d'une annonce TERMINEE
		
		
		labaevaluer = new JLabel("Vos annonces à évaluer");
		labaevaluer.setBounds(170,250,300,30);
		labaevaluer.setFont(new Font("Arial",Font.BOLD,22));
		labaevaluer.setForeground(Color.black);
		pan.add(labaevaluer);
		labaevaluer.setVisible(false);
		
		demandesAEvaluer = new DefaultComboBoxModel<>();
		
		for (int demande : MainController.getDemandsOfBeneficiaire(idbeneficiaire)){
			try {
				if (MainController.getInfoOfDemand(demande, "statut").equals("TERMINEE_PAS_EVALUEE")) {
					demandesAEvaluer.addElement(demande + "          " + 
					MainController.getInfoOfDemand(demande, "titre") + "          " + 
					MainController.getInfoOfDemand(demande, "benevole") + "          " + 
					MainController.getInfoOfDemand(demande, "jour"));
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
		choixDemandesAEvaluer.setBounds(150,300,320,30);
		choixDemandesAEvaluer.addItemListener(this);
		choixDemandesAEvaluer.setVisible(false);
		
		btdonneravis = new JButton("DONNER UN AVIS");
		btdonneravis.setBounds(170,350,250,30);
		btdonneravis.setBackground(Color.white);
		btdonneravis.setFont(new Font("Arial",Font.BOLD,13));
		btdonneravis.setForeground(Color.black);
		pan.add(btdonneravis);
		btdonneravis.addActionListener(this);
		btdonneravis.setVisible(false);
		
		btretour = new JButton("RETOUR PAGE PRECEDENTE");
		btretour.setBounds(150,400,300,40);
		btretour.setBackground(Color.white);
		btretour.setFont(new Font("Arial",Font.BOLD,13));
		btretour.setForeground(Color.black);
		pan.add(btretour);
		btretour.addActionListener(this);
		btretour.setVisible(false);
		
		btdeconnexion = new JButton("DECONNEXION");
		btdeconnexion.setBounds(150,500,300,30);
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
	
	public void afficherAucuneOption() {
		JOptionPane.showMessageDialog(this, "Vous n'avez selectionné aucune option");
	}
	
	public void confirmationDeconnexion() {
		String[] options = {"oui", "non"};
		int choix = JOptionPane.showOptionDialog(null, "Voulez-vous vraiment vous déconnecter?",
            "Confiramation Déconnexion",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    	if (choix == 0) {
    		this.setVisible(false);
			FormulaireConnexion fco = new FormulaireConnexion();
			fco.setVisible(true);
    	} else if (choix == 1) {
    		//on ne fait rien
    	}
	}
	
	public void valueChanged(ListSelectionEvent evt) { 
		 etiquette.setText((String)liste.getSelectedValue());
	}
	
	public void itemStateChanged(ItemEvent i) {
		//null
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btposterannonce)) {
			FormulaireDemande fd = new FormulaireDemande(idbeneficiaire);
			fd.setVisible(true);
		}
				
		if (e.getSource().equals(btoptionok)) {
			if (liste.isSelectionEmpty()) {
				afficherAucuneOption();
			} else {
				liste.setVisible(false);
				etiquette.setVisible(false);
				btoptionok.setVisible(false);
				labtitre.setVisible(false);
					if (liste.getSelectedValue().equals("Enregistrer une annonce comme terminée & voir les avis d'un bénévole")) {
						labacceptee.setVisible(true);
						choixDemandesAcceptees.setVisible(true);
						btvoiravis.setVisible(true);
						btterminee.setVisible(true);
						btretour.setVisible(true);
					} else if (liste.getSelectedValue().equals("Evaluer un bénévole")) {
						labaevaluer.setVisible(true);
						choixDemandesAEvaluer.setVisible(true);
						btdonneravis.setVisible(true);
						btretour.setVisible(true);
					}
			}
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
			if (e.getSource().equals(btvoiravis)) {
				if ((String)choixDemandesAEvaluer.getSelectedItem() == "Aucune annonce"){
					afficherAucuneAnnonce();
				} else {
					ViewAvis va = new ViewAvis(MainController.getInfoOfDemand(Integer.parseInt(wordAC[0]),"benevole"));
					va.setVisible(true);
				}
			}
			
			if (e.getSource().equals(btretour)) {
				if (liste.getSelectedValue().equals("Enregistrer une annonce comme terminée & voir les avis d'un bénévole")) {
					labacceptee.setVisible(false);
					choixDemandesAcceptees.setVisible(false);
					btvoiravis.setVisible(false);
					btterminee.setVisible(false);
					btretour.setVisible(false);
				} else if (liste.getSelectedValue().equals("Evaluer un bénévole")) {
					labaevaluer.setVisible(false);
					choixDemandesAEvaluer.setVisible(false);
					btdonneravis.setVisible(false);
					btretour.setVisible(false);
				}
				liste.setVisible(true);
				etiquette.setVisible(true);
				btoptionok.setVisible(true);
				labtitre.setVisible(true);
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
			confirmationDeconnexion();
		}
	}
	
	public static void main(String[] args) {
		String beneficiaire;
		beneficiaire = "benef";
		
		ViewBeneficiaire vbf = new ViewBeneficiaire(beneficiaire);
	    vbf.setVisible(true);
		}
}
