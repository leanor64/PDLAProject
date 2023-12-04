package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controller.MainController;
import Model.StatutDemande;
import Model.UnexistingAvisException;
import Model.UnexistingInfoException;
import Model.UnexistingUserException;

//Interface pour afficher les avis antérieur d'un utilisateur
public class ViewAvis extends JFrame implements ActionListener{
	
	//Attributs
	
	JLabel labtitre, labtitredemande, labdemande, labbenef, labville;
	JTextField jtfmotif;
	JButton btretour;
	String id;
	
	DefaultTableModel avis;
	JTable tableavis;
	JScrollPane scrollPane;
	
			
	//Constructeur
	public ViewAvis(String id) {
		this.id = id;
				
		this.setTitle("Avis de " + id);
		this.setSize(600,600);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(Color.white);
		add(pan);
				
		labtitre = new JLabel("Vos avis");
		labtitre.setBounds(250,10,600,70);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre);
				
		avis = new DefaultTableModel();

        // Ajout des colonnes au modèle avis
        avis.addColumn("Emetteur");
        avis.addColumn("Commentaire");
        avis.addColumn("Note");

        try {
        	//création du tableau présentant les avis du user "id"
			for (int noavis : MainController.getListOfAvis(id)) {
				try {
					avis.addRow(new String[]{MainController.getInfoOfAvis(noavis,"emetteur"),MainController.getInfoOfAvis(noavis,"commentaire"),MainController.getInfoOfAvis(noavis,"note")});
				} catch (UnexistingInfoException exc1) {
					System.out.println("Erreur " + exc1.getMessage());
					dispose();
				} catch (UnexistingAvisException exc2 ) {
					System.out.println("Erreur " + exc2.getMessage());
					dispose();
				}
			}
		} catch (UnexistingUserException exc) {
			System.out.println("Erreur " + exc.getMessage());
			dispose();
		}
        if ((avis).getRowCount() == 0) { //si aucun avis pour le moment
			avis.addRow(new String[]{"Aucun avis","pour","le moment"});
		}
        
        //affichage de la table d'avis
        tableavis = new JTable(avis);
        tableavis.setBounds(70,250,450,70);
        scrollPane = new JScrollPane(tableavis);
        scrollPane.setBounds(70,250,450,70);
        pan.add(scrollPane);
				
        //bouton pour revenir au profil principal de l'utilisateur
		btretour = new JButton("RETOUR");
		btretour.setBounds(220,500,150,30);
		btretour.setBackground(Color.white);
		btretour.setFont(new Font("Arial",Font.BOLD,18));
		btretour.setForeground(Color.black);
		pan.add(btretour);
		btretour.addActionListener(this);
				
		
				
	}
			
	//Méthodes
				
	//méthode pour gérer le bouton "retour"
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btretour)) {
			//retour sur le profil principal de l'utilisateur
			dispose();		
		}
	}

}
