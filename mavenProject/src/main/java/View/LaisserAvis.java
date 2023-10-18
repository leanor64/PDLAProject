package View;
import java.awt.*;

import javax.swing.*;
import Model.*;


public class LaisserAvis extends JFrame {
	
	//Attributs
	//Beneficiaire benef;
	//Benevole benev;
	JLabel labtitre, labavis;
	JTextField jtfavis;
	JButton btajout;
	
	//Constructeur
	public LaisserAvis(/*Beneficiaire benef, Benevole benev*/) {
		//this.benef = benef;
		//this.benev = benev;
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
		jtfavis.setBounds(220,60,300,300);
		pan.add(jtfavis);
		
		btajout = new JButton("Enregistrer");
		btajout.setBounds(150,420,150,30);
		btajout.setBackground(Color.white);
		btajout.setFont(new Font("Arial",Font.BOLD,18));
		btajout.setForeground(Color.black);
		pan.add(btajout);
		
		
		
	}
	
	
	public static void main(String[] args) {
		
	     LaisserAvis avis = new LaisserAvis();
	     avis.setVisible(true);
		}

}
