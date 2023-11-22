package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.*;
import Model.StatutDemande;

public class ViewValideur extends JFrame implements ListSelectionListener, ActionListener{

	MainController controller = new MainController();
	
	//Attributs
	String idvalideur;
	JLabel labtitre;
	JButton btsuiv, btdeconnexion, btbene;
	
	JList liste = new JList();
	DefaultListModel<String> listModel;
	JLabel etiquette = new JLabel(" ");
	//ArrayList<String> choix = new ArrayList<String>(); //{"Titre de l'annonce    Bénéficiaire   Date    Ville"};

	
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
		
		listModel = new DefaultListModel<String>();
		//mettre plutot sous forme de JLabel pour pas que le benevole puisse cliquer sur cette ligne
		listModel.addElement("Titre                                                        Beneficiaire                                 Date                                Ville");
		for (int demande : controller.getListOfDemands(StatutDemande.EN_ATTENTE)){
			
			//try {
			listModel.addElement(Integer.toString(demande)); /* + "                |                  " + controller.getInfoOfDemand(demande, "beneficiaire") + "                                 |                    " + 
					controller.getInfoOfDemand(demande, "date") + "|" + controller.getInfoOfDemand(demande, "ville"));*/
			//} catch (UnexistingInfoException exc1) {
				//System.out.println("erreur getInfoOfDemand()");
			//} catch (UnexistingDemandException exc2) {
			//	System.out.println("erreur getInfoOfDemand()");
			//}
		}
		
		liste = new JList(listModel);
		liste.setBounds(150,80,600,550);
		liste.addListSelectionListener(this);
		pan.add(etiquette);
		pan.add(liste);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		etiquette.setText((String)liste.getSelectedValue());
			
		btsuiv = new JButton("Suivant");
		btsuiv.setBounds(370,650,150,30);
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
	
	public void valueChanged(ListSelectionEvent evt) { 
		 etiquette.setText((String)liste.getSelectedValue());
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btsuiv) {
			ViewDemande vd = new ViewDemande(Integer.parseInt((String)liste.getSelectedValue()));
			vd.setVisible(true);
		}
		if (e.getSource() == btdeconnexion) {
			this.setVisible(false);
			FormulaireConnexion fco = new FormulaireConnexion();
			fco.setVisible(true);
		}
		if (e.getSource() == btbene) {
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
