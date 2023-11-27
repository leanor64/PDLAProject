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
		this.setSize(900,800);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		Color custom = new Color(204, 153, 255);
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(custom);
		add(pan);
		
		labtitre = new JLabel("Sélectionnez une annonce");
		labtitre.setBounds(320,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre);
		
		demandes = new DefaultComboBoxModel<>();
		
		
		for (int demande : MainController.getDemandsofStatus(StatutDemande.EN_ATTENTE)){
			demandes.addElement(Integer.toString(demande)); 
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
		
		
		btsuiv = new JButton("Suivant");
		btsuiv.setBounds(370,400,150,30);
		btsuiv.setBackground(Color.white);
		btsuiv.setFont(new Font("Arial",Font.BOLD,18));
		btsuiv.setForeground(Color.black);
		pan.add(btsuiv);
		btsuiv.addActionListener(this);
		
		btbene = new JButton("Accéder à votre profil bénévole");
		btbene.setBounds(70,700,400,30);
		btbene.setBackground(Color.white);
		btbene.setFont(new Font("Arial",Font.BOLD,18));
		btbene.setForeground(Color.black);
		pan.add(btbene);
		btbene.addActionListener(this);
		
		btdeconnexion = new JButton("DECONNEXION");
		btdeconnexion.setBounds(490,700,300,30);
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
		if (e.getSource().equals(btsuiv)) {
			if ((String)choixDemande.getSelectedItem() == "Aucune annonce"){
				afficherAucuneAnnonce();
			} else {
				this.setVisible(false);
				ViewDemande vd = new ViewDemande(idvalideur, Integer.parseInt((String)choixDemande.getSelectedItem()));
				vd.setVisible(true);
			}
		}
		if (e.getSource().equals(btdeconnexion)) {
			this.setVisible(false);
			FormulaireConnexion fco = new FormulaireConnexion();
			fco.setVisible(true);
		}
		if (e.getSource().equals(btbene)) {
			this.setVisible(false);
			ViewBenevole vbene = new ViewBenevole(idvalideur);
			vbene.setVisible(true);
		}
		
	}
	
	
	public static void main(String[] args) {
		String valideur;
		valideur = "valid";
		
		ViewValideur vva = new ViewValideur(valideur);
	    vva.setVisible(true);
		}

}
