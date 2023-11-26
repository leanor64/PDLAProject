package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controller.MainController;
import Controller.UnexistingAvisException;
import Controller.UnexistingInfoException;
import Model.StatutDemande;

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
		this.setSize(700,700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
				
		Color custom = new Color(204, 153, 255);
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(custom);
		add(pan);
				
		labtitre = new JLabel("Vos avis");
		labtitre.setBounds(300,10,600,70);
		labtitre.setFont(new Font("Arial",Font.BOLD,18));
		labtitre.setForeground(Color.black);
		pan.add(labtitre);
				
		avis = new DefaultTableModel();

        // Ajout des colonnes au modèle
        avis.addColumn("Emetteur");
        avis.addColumn("Commentaire");
        avis.addColumn("Note");

        //TODO erreur info exception - comprendre pq il ne rentre pas dans le try alors qu'il a deja un avis
        for (int noavis : MainController.getListOfAvis(idbenevole)) {
        	
    		System.out.println("ok");
        	try {
        		System.out.println("emetteur : " + MainController.getInfoOfAvis(noavis,"Emetteur"));
        		System.out.println("com : " + MainController.getInfoOfAvis(noavis,"Commentaire"));
        		System.out.println("note : " + MainController.getInfoOfAvis(noavis,"Note"));
				avis.addRow(new String[]{MainController.getInfoOfAvis(noavis,"Emetteur"),MainController.getInfoOfAvis(noavis,"Commentaire"),MainController.getInfoOfAvis(noavis,"Note")});
			} catch (UnexistingInfoException exc1) {
				System.out.println("Erreur Info inexistante");
			} catch (UnexistingAvisException exc2 ) {
				System.out.println("Erreur Avis inexistant");
			}
        }
        
        tableavis = new JTable(avis);
        tableavis.setBounds(140,150,450,70);
        scrollPane = new JScrollPane(tableavis);
        scrollPane.setBounds(140, 150, 450, 70);  // Ajustez les coordonnées et la taille selon vos besoins
        pan.add(scrollPane);
				
		btretour = new JButton("RETOUR");
		btretour.setBounds(310,350,150,30);
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
