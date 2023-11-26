package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import Controller.*;
import Model.StatutDemande;

public class ViewBenevole extends JFrame implements ActionListener, ItemListener{

	
	//Attributs
	String idbenevole;
	JLabel labtitre;
	JButton btdeconnexion, btvalideur, btsuivant, btavis;
	
	DefaultComboBoxModel<String> demandes;
	JComboBox<String> choixDemande;
	
	//Constructeur
	public ViewBenevole(String idbenevole) {
		this.idbenevole = idbenevole;
		
		this.setTitle("Bienvenue " + idbenevole);
		this.setSize(900,800);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		Color custom = new Color(204, 153, 255);
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(custom);
		add(pan);
		
		labtitre = new JLabel("Votre compte");
		labtitre.setBounds(370,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre);
		
		try {
			if (MainController.getTypeOfUser(idbenevole) == 2) {
				btvalideur = new JButton("Accéder à votre profil Valideur");
				btvalideur.setBounds(250,620,400,30);
				btvalideur.setBackground(Color.white);
				btvalideur.setFont(new Font("Arial",Font.BOLD,18));
				btvalideur.setForeground(Color.black);
				pan.add(btvalideur);
				btvalideur.addActionListener(this);
			}
		} catch (UnexistingUserException excp) {
			System.out.println("Erreur bouton valideur/bénévole");
		}
		
		demandes = new DefaultComboBoxModel<>();
		
		
		for (int demande : MainController.getListOfDemands(StatutDemande.VALIDEE)){
			try {
				demandes.addElement(demande + " " + MainController.getInfoOfDemand(demande, "titre") + MainController.getInfoOfDemand(demande, "beneficiaire")/* + 
				controller.getInfoOfDemand(demande, "date")*/ + MainController.getInfoOfDemand(demande, "ville"));
			} catch (UnexistingInfoException exc1) {
				System.out.println("erreur getInfoOfDemand()");
			} catch (UnexistingDemandException exc2) {
				System.out.println("erreur getInfoOfDemand()");
			} 
		}
		if (demandes.getSize() == 0) {
			demandes.addElement("Aucune annonce");			
		}
		
		choixDemande = new JComboBox<>(demandes);
		pan.add(choixDemande);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		choixDemande.setSelectedIndex(0);
		choixDemande.setBounds(270,270,420,30);
		choixDemande.addItemListener(this);
		
		btsuivant = new JButton("SUIVANT");
		btsuivant.setBounds(310,320,300,30);
		btsuivant.setBackground(Color.white);
		btsuivant.setFont(new Font("Arial",Font.BOLD,18));
		btsuivant.setForeground(Color.black);
		pan.add(btsuivant);
		btsuivant.addActionListener(this);
		
		btavis = new JButton("VOIR MES AVIS");
		btavis.setBounds(310,500,300,30);
		btavis.setBackground(Color.white);
		btavis.setFont(new Font("Arial",Font.BOLD,18));
		btavis.setForeground(Color.black);
		pan.add(btavis);
		btavis.addActionListener(this);
		
		
		btdeconnexion = new JButton("DECONNEXION");
		btdeconnexion.setBounds(310,700,300,30);
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
		String[] word = ((String) choixDemande.getSelectedItem()).split(" ");
		if (e.getSource().equals(btsuivant)) {
			if ((String)choixDemande.getSelectedItem() == "Aucune annonce"){
				afficherAucuneAnnonce();
			} else {
				this.setVisible(false);
				ViewDemandeBenevole vdb = new ViewDemandeBenevole(idbenevole,Integer.parseInt(word[0]));
				vdb.setVisible(true);
			}
		} else {
			if (e.getSource().equals(btdeconnexion)) {
				this.setVisible(false);
				FormulaireConnexion fco = new FormulaireConnexion();
				fco.setVisible(true);
			}
			if (e.getSource().equals(btvalideur)) {
				this.setVisible(false);
				ViewValideur vva = new ViewValideur(idbenevole);
				vva.setVisible(true);
			}
			if (e.getSource().equals(btavis)) {
				this.setVisible(false);
				ViewAvis va = new ViewAvis(idbenevole);
				va.setVisible(true);
			}
		}
	}
	
	public static void main(String[] args) {
		String benevole;
		benevole = "test0";
		
		ViewBenevole vbn = new ViewBenevole(benevole);
	    vbn.setVisible(true);
		}
}
