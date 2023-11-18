package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.*;
import Model.*;


public class FormulaireDemande extends JFrame implements ActionListener{
	
	//Attributs
	Beneficiaire benef;
	JLabel labtitre,labtitredemande, labdemande, labdate, labville;
	JTextField jtftitredemande, jtfdemande, jtfdate, jtfville;
	JButton btajout;
	
	//Constructeur
	public FormulaireDemande(Beneficiaire benef) {

		this.benef = benef;
		this.setTitle("Postez une annonce");
		this.setSize(650,450);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		Color custom = new Color(204, 153, 255);
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(custom);
		add(pan, BorderLayout.CENTER);
		
		labtitre = new JLabel("Votre annonce");
		labtitre.setBounds(220,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre, BorderLayout.CENTER);
		
		labtitredemande = new JLabel("Saisissez le titre :");
		labtitredemande.setBounds(20,50,300,30);
		labtitredemande.setFont(new Font("Arial",Font.BOLD,18));
		labtitredemande.setForeground(Color.black);
		pan.add(labtitredemande);
		
		jtftitredemande = new JTextField();
		jtftitredemande.setBounds(280,50,300,30);
		pan.add(jtftitredemande);
		
		labdemande = new JLabel("Saisissez votre demande :");
		labdemande.setBounds(20,90,300,30);
		labdemande.setFont(new Font("Arial",Font.BOLD,18));
		labdemande.setForeground(Color.black);
		pan.add(labdemande, BorderLayout.CENTER);
		
		jtfdemande = new JTextField();
		jtfdemande.setBounds(280,90,300,100);
		pan.add(jtfdemande);
		
		labville = new JLabel("Saisissez la ville concernée :");
		labville.setBounds(20,230,300,30);
		labville.setFont(new Font("Arial",Font.BOLD,18));
		labville.setForeground(Color.black);
		pan.add(labville);
		
		jtfville = new JTextField();
		jtfville.setBounds(280,230,300,25);
		pan.add(jtfville);
		
		
		btajout = new JButton("Enregistrer");
		btajout.setBounds(220,300,150,30);
		btajout.setBackground(Color.white);
		btajout.setFont(new Font("Arial",Font.BOLD,18));
		btajout.setForeground(Color.black);
		pan.add(btajout, BorderLayout.CENTER);
		btajout.addActionListener(this);
		
	}
	
	//Méthodes
	

	public void afficherTailleNonValide(String info) {
		JOptionPane.showMessageDialog(this,info + " invalide, attention à respecter le nombre de caractères");
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btajout) {
			System.out.println("vous avez cliqué sur le bouton Enregistrer");
			try {
				Demande dem = new Demande(jtftitredemande.getText(),jtfdemande.getText(),benef,"en attente");
				NewDemande d = new NewDemande(dem);
			} catch (BadLengthException exc) {
				afficherTailleNonValide(exc.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		Beneficiaire beneficiaire;
		beneficiaire = new Beneficiaire("test demande", "abracadabra", "Camus", "Albert", 28, "blabla@gmail.com", "0123456789", "Toulouse", "8 allée des sc appliquees", 14);
	    
		FormulaireDemande demande = new FormulaireDemande(beneficiaire);
	    demande.setVisible(true);
		}

	

}