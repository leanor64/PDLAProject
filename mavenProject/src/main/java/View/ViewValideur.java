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
		
		
		for (int demande : MainController.getDemandsofStatus(StatutDemande.EN_ATTENTE)){
			try {
				demandes.addElement(Integer.toString(demande) + "          " +
				MainController.getInfoOfDemand(demande, "titre") + "          " +
				MainController.getInfoOfDemand(demande, "beneficiaire") + "          " +
				MainController.getInfoOfDemand(demande, "jour"));
			} catch (UnexistingInfoException exc1) {
				System.out.println("erreur Info Inexistante");
			} catch (UnexistingDemandException exc2) {
				System.out.println("erreur demande Inexistante");
			}
		}
		if (demandes.getSize() == 0) {
			demandes.addElement("Aucune annonce");			
		}
		
		choixDemande = new JComboBox<>(demandes);
		pan.add(choixDemande);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		choixDemande.setSelectedIndex(0);
		choixDemande.setBounds(90,230,420,30);
		choixDemande.addItemListener(this);
		
		
		btsuiv = new JButton("Suivant");
		btsuiv.setBounds(230,270,150,30);
		btsuiv.setBackground(Color.white);
		btsuiv.setFont(new Font("Arial",Font.BOLD,18));
		btsuiv.setForeground(Color.black);
		pan.add(btsuiv);
		btsuiv.addActionListener(this);
		
		btbene = new JButton("Accéder à votre profil bénévole");
		btbene.setBounds(100,400,400,30);
		btbene.setBackground(Color.white);
		btbene.setFont(new Font("Arial",Font.BOLD,18));
		btbene.setForeground(Color.black);
		pan.add(btbene);
		btbene.addActionListener(this);
		
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
	
	public void itemStateChanged(ItemEvent i) {
		//null
	}
	
	public void actionPerformed(ActionEvent e) {
		String[] word = ((String) choixDemande.getSelectedItem()).split("          ");
		if (e.getSource().equals(btsuiv)) {
			if (((String)choixDemande.getSelectedItem()).equals("Aucune annonce")){
				afficherAucuneAnnonce();
			} else {
				this.setVisible(false);
				ViewDemande vd = new ViewDemande(idvalideur, Integer.parseInt(word[0]));
				vd.setVisible(true);
			}
		}
		if (e.getSource().equals(btdeconnexion)) {
			confirmationDeconnexion();
		}
		if (e.getSource().equals(btbene)) {
			this.setVisible(false);
			ViewBenevole vbene = new ViewBenevole(idvalideur);
			vbene.setVisible(true);
		}
		
	}
	
	
	public static void main(String[] args) {
		String valideur;
		valideur = "test3";
		
		ViewValideur vva = new ViewValideur(valideur);
	    vva.setVisible(true);
		}

}
