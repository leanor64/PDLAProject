package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.*;

public class ViewValideur extends JFrame implements ListSelectionListener, ActionListener{

	
	//Attributs
	String idvalideur;
	JLabel labtitre;
	JButton btsuiv, btdeconnexion;
	JList liste = new JList();
	JLabel etiquette = new JLabel(" ");
	String choix[] = {"Bénévole         Titre annonce"};

	
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
		
		liste = new JList(choix);
		liste.setBounds(300,200,300,110);
		liste.addListSelectionListener(this);
		pan.add(etiquette);
		pan.add(liste);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		etiquette.setText((String)liste.getSelectedValue());
			
		btsuiv = new JButton("Suivant");
		btsuiv.setBounds(370,400,150,30);
		btsuiv.setBackground(Color.white);
		btsuiv.setFont(new Font("Arial",Font.BOLD,18));
		btsuiv.setForeground(Color.black);
		pan.add(btsuiv);
		btsuiv.addActionListener(this);
		
		btdeconnexion = new JButton("DECONNEXION");
		btdeconnexion.setBounds(300,700,300,30);
		btdeconnexion.setBackground(Color.white);
		btdeconnexion.setFont(new Font("Arial",Font.BOLD,18));
		btdeconnexion.setForeground(Color.black);
		pan.add(btdeconnexion);
		btdeconnexion.addActionListener(this);
	}
	
	//Méthodes
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btsuiv) {
			//System.out.println("vous avez cliqué sur le bouton Enregistrer");
			ViewDemande vd = new ViewDemande();
			vd.setVisible(true);
		}
		if (e.getSource() == btdeconnexion) {
			this.setVisible(false);
			FormulaireConnexion fco = new FormulaireConnexion();
			fco.setVisible(true);
		}
	}
	
	public void valueChanged(ListSelectionEvent evt) { 
		 etiquette.setText((String)liste.getSelectedValue());
	}
	
	public static void main(String[] args) {
		String valideur;
		valideur = "valid";
		
		ViewValideur vva = new ViewValideur(valideur);
	    vva.setVisible(true);
		}

}
