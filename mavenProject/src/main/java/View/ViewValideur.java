package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import Controller.*;
import Model.StatutDemande;
import Model.UnexistingDemandException;
import Model.UnexistingInfoException;

//interface du profil principal d'un valideur
public class ViewValideur extends JFrame implements ItemListener, ActionListener{


	//Attributs
	String idvalideur;
	JLabel labtitre;
	JButton btsuiv, btdeconnexion, btbene;
		
	DefaultComboBoxModel<String> demandes;
	JComboBox<String> choixDemande;
	
	
	//Constructeur
	public ViewValideur(String idvalideur) {
		
		this.idvalideur = idvalideur;
		
		this.setTitle("Bienvenue " + idvalideur);
		this.setSize(600,600);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(Color.white);
		add(pan);
		
		labtitre = new JLabel("Sélectionnez une annonce");
		labtitre.setBounds(150,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre);
		
		demandes = new DefaultComboBoxModel<>();
		
		//liste des annonces en attente de validation
		for (int demande : MainController.getDemandsofStatus(StatutDemande.EN_ATTENTE)){
			try {
				demandes.addElement(Integer.toString(demande) + "          " +
				MainController.getInfoOfDemand(demande, "titre") + "          " +
				MainController.getInfoOfDemand(demande, "beneficiaire") + "          " +
				MainController.getInfoOfDemand(demande, "jour"));
			} catch (UnexistingInfoException exc1) {
				System.out.println("erreur " + exc1.getMessage());
				dispose();
			} catch (UnexistingDemandException exc2) {
				System.out.println("erreur " + exc2.getMessage());
				dispose();
			}
		}
		if (demandes.getSize() == 0) { //si aucune annonce
			demandes.addElement("Aucune annonce");			
		}
		
		//affichage des demandes en attente
		choixDemande = new JComboBox<>(demandes);
		pan.add(choixDemande);
		choixDemande.setSelectedIndex(0);
		choixDemande.setBounds(90,230,420,30);
		choixDemande.addItemListener(this);
		
		//bouton pour voir une annonce plus en détail et la valider ou non
		btsuiv = new JButton("Suivant");
		btsuiv.setBounds(230,270,150,30);
		btsuiv.setBackground(Color.white);
		btsuiv.setFont(new Font("Arial",Font.BOLD,18));
		btsuiv.setForeground(Color.black);
		pan.add(btsuiv);
		btsuiv.addActionListener(this);
		
		//bouton pour que le valideur puisse accéder à son profil bénévole
		btbene = new JButton("Accéder à votre profil bénévole");
		btbene.setBounds(100,400,400,30);
		btbene.setBackground(Color.white);
		btbene.setFont(new Font("Arial",Font.BOLD,18));
		btbene.setForeground(Color.black);
		pan.add(btbene);
		btbene.addActionListener(this);
		
		//bouton pour la déconnexion
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
		JOptionPane.showMessageDialog(null, "Vous n'avez aucune annonce correspondante");
	}
	
	//méthode pour confirmer la déconnexion (affichage pop up)
	public void confirmationDeconnexion() {
		String[] options = {"oui", "non"};
		int choix = JOptionPane.showOptionDialog(null, "Voulez-vous vraiment vous déconnecter?",
            "Confirmation Déconnexion",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    	if (choix == 0) {
			FormulaireConnexion fco = new FormulaireConnexion();
			fco.setVisible(true);
			dispose();
    	} else if (choix == 1) {
    		//on ne fait rien
    	}
	}
	
	//méthode gérant ItemListener
	public void itemStateChanged(ItemEvent i) {
		//null
	}
	
	//méthode gérant les différents boutons
	public void actionPerformed(ActionEvent e) {
		//récupération du numéro de la demande
		String[] word = ((String) choixDemande.getSelectedItem()).split("          ");
		if (e.getSource().equals(btsuiv)) {
			if (((String)choixDemande.getSelectedItem()).equals("Aucune annonce")){
				afficherAucuneAnnonce();// affichage pop up si aucune annonce sélectionnée
			} else {
				//affichage de la demande plus en détail
				ViewDemande vd = new ViewDemande(idvalideur, Integer.parseInt(word[0]));
				vd.setVisible(true);
				dispose();
			}
		}
		if (e.getSource().equals(btdeconnexion)) {
			confirmationDeconnexion();//confirmation déconnexion
		}
		if (e.getSource().equals(btbene)) {
			//affichage interface du profil bénévole 
			ViewBenevole vbene = new ViewBenevole(idvalideur);
			vbene.setVisible(true);
			dispose();
		}
		
	}
	//TODO : a enlever
	public static void main(String[] args) {
		String valideur;
		valideur = "test3";
		
		ViewValideur vva = new ViewValideur(valideur);
	    vva.setVisible(true);
		}

}
