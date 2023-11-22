package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.*;
import Model.StatutDemande;

public class ViewBenevole extends JFrame implements ActionListener, ListSelectionListener{

	MainController controller = new MainController();
	
	//Attributs
	String idbenevole;
	JLabel labtitre;
	JButton btdeconnexion, btvalideur;
	
	JList liste = new JList();
	DefaultListModel listModel;
	JLabel etiquette = new JLabel(" ");
	ArrayList<String> choix = new ArrayList<String>(); //{"Titre de l'annonce    Bénéficiaire   Date    Ville"};
	
	
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
			if (controller.getTypeOfUser(idbenevole) == 2) {
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
		
		listModel = new DefaultListModel();
		//mettre plutot sous forme de JLabel pour pas que le benevole puisse cliquer sur cette ligne
		listModel.addElement("Titre                                                  Beneficiaire                                      Date                                    Ville");
		for (int demande : controller.getListOfDemands(StatutDemande.VALIDEE)){
			
			try {
			listModel.addElement(controller.getInfoOfDemand(demande, "titre") + controller.getInfoOfDemand(demande, "beneficiaire")/* + 
					controller.getInfoOfDemand(demande, "date")*/ + controller.getInfoOfDemand(demande, "ville"));
			} catch (UnexistingInfoException exc1) {
				System.out.println("erreur getInfoOfDemand()");
			} catch (UnexistingDemandException exc2) {
				System.out.println("erreur getInfoOfDemand()");
			}
		}
		
		liste = new JList(listModel);
		liste.setBounds(120,220,600,55);
		liste.addListSelectionListener(this);
		pan.add(etiquette);
		pan.add(liste);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		etiquette.setText((String)liste.getSelectedValue());
		
		
		
		btdeconnexion = new JButton("DECONNEXION");
		btdeconnexion.setBounds(310,700,300,30);
		btdeconnexion.setBackground(Color.white);
		btdeconnexion.setFont(new Font("Arial",Font.BOLD,18));
		btdeconnexion.setForeground(Color.black);
		pan.add(btdeconnexion);
		btdeconnexion.addActionListener(this);
	}
	
	
	//Méthodes
	
	public void valueChanged(ListSelectionEvent evt) { 
		 etiquette.setText((String)liste.getSelectedValue());
		 
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btdeconnexion) {
			this.setVisible(false);
			FormulaireConnexion fco = new FormulaireConnexion();
			fco.setVisible(true);
		}
		if (e.getSource() == btvalideur) {
			this.setVisible(false);
			ViewValideur vva = new ViewValideur(idbenevole);
			vva.setVisible(true);
		}
		
	}
	
	public static void main(String[] args) {
		String benevole;
		benevole = "benev";
		
		ViewBenevole vbn = new ViewBenevole(benevole);
	    vbn.setVisible(true);
		}
}
