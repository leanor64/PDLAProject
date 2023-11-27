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

public class ViewAvis extends JFrame implements ActionListener{
	
	//Attributs
	
	JLabel labtitre, labtitredemande, labdemande, labbenef, labville;
	JTextField jtfmotif;
	JButton btretour;
	String idbenevole;
	
	DefaultTableModel avis;
	JTable tableavis;
	JScrollPane scrollPane;
	
			
	//Constructeur
	public ViewAvis(String idbenevole) {
		this.idbenevole = idbenevole;
				
		this.setTitle("Bienvenue " + idbenevole);
		this.setSize(600,600);
		this.setResizable(false);
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
			for (int noavis : MainController.getListOfAvis(idbenevole)) {
				try {
					avis.addRow(new String[]{MainController.getInfoOfAvis(noavis,"emetteur"),MainController.getInfoOfAvis(noavis,"commentaire"),MainController.getInfoOfAvis(noavis,"note")});
				} catch (UnexistingInfoException exc1) {
					System.out.println("Erreur Info inexistante");
				} catch (UnexistingAvisException exc2 ) {
					System.out.println("Erreur Avis inexistant");
				}
			}
		} catch (UnexistingUserException exc) {
			System.out.println("Erreur User inconnu");
		}
        if ((avis).getRowCount() == 0) {
			avis.addRow(new String[]{"Aucun avis","pour","le moment"});
		}
        
        tableavis = new JTable(avis);
        tableavis.setBounds(70,250,450,70);
        scrollPane = new JScrollPane(tableavis);
        scrollPane.setBounds(70,250,450,70);
        pan.add(scrollPane);
				
		btretour = new JButton("RETOUR");
		btretour.setBounds(220,500,150,30);
		btretour.setBackground(Color.white);
		btretour.setFont(new Font("Arial",Font.BOLD,18));
		btretour.setForeground(Color.black);
		pan.add(btretour);
		btretour.addActionListener(this);
				
		
				
	}
			
	//Méthodes
				
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btretour)) {
			this.setVisible(false);			
		}
	}
			
	public static void main(String[] args) {
		String benevole = "test0";		
		ViewAvis va = new ViewAvis(benevole);
		va.setVisible(true);
	}


}
