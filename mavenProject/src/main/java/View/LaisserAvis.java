package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.NewUser;
import Model.*;


public class LaisserAvis extends JFrame implements ListSelectionListener, ActionListener{
	
	//Attributs
	//Beneficiaire benef;
	Benevole benev;
	JLabel labtitre, labavis, labnote;
	JTextField jtfavis;
	JList liste = new JList();
	JLabel etiquette = new JLabel(" ");
	String choix[] = {" 0", " 1", " 2", " 3", " 4", " 5"};
	JButton btajout;
	
	//Constructeur
	public LaisserAvis(/*Beneficiaire benef, Benevole benev*/) {
		//this.benef = benef;
		this.benev = benev;
		this.setTitle("Votre retour d'expérience") /* à " + benev.getPrenom())*/;
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(Color.pink);
		add(pan, BorderLayout.CENTER);
		
		labtitre = new JLabel("Laisser un avis");
		labtitre.setBounds(60,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre, BorderLayout.CENTER);
		
		labavis = new JLabel("Laissez votre avis :");
		labavis.setBounds(20,60,300,30);
		labavis.setFont(new Font("Arial",Font.BOLD,18));
		labavis.setForeground(Color.black);
		pan.add(labavis);
		
		jtfavis = new JTextField();
		jtfavis.setBounds(220,60,300,100);
		pan.add(jtfavis);
		
		labnote = new JLabel("Laissez une note :");
		labnote.setBounds(20,200,300,30);
		labnote.setFont(new Font("Arial",Font.BOLD,18));
		labnote.setForeground(Color.black);
		pan.add(labnote);
		
		liste = new JList(choix);
		liste.setBounds(220,200,300,110);
		liste.addListSelectionListener(this);
		pan.add(etiquette);
		pan.add(liste);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		etiquette.setText((String)liste.getSelectedValue());
		//Revoir le choix de la note
			
		btajout = new JButton("Enregistrer");
		btajout.setBounds(150,350,150,30);
		btajout.setBackground(Color.white);
		btajout.setFont(new Font("Arial",Font.BOLD,18));
		btajout.setForeground(Color.black);
		pan.add(btajout);
		btajout.addActionListener(this);
		
		
	}
	
	//Méthodes
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btajout) {
			System.out.println("vous avez cliqué sur le bouton Enregistrer");
		}
	}
	
	public void valueChanged(ListSelectionEvent evt) { 
		 etiquette.setText((String)liste.getSelectedValue());
	}
	
	
	public static void main(String[] args) {
		
	     LaisserAvis avis = new LaisserAvis();
	     avis.setVisible(true);
		}

	

}
