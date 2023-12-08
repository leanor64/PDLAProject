package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import Controller.*;
import Model.StatutDemande;
import Model.UnexistingDemandException;
import Model.UnexistingInfoException;
import Model.UnexistingUserException;

//interface du profil d'un bénévole
public class ViewBenevole extends JFrame implements ActionListener, ItemListener{

	
	//Attributs
	String idbenevole;
	JLabel labtitre, labannonce;
	JButton btdeconnexion, btvalideur, btsuivant, btavis, btchangerinfos;
	
	DefaultComboBoxModel<String> demandes;
	JComboBox<String> choixDemande;
	
	//Constructeur
	public ViewBenevole(String idbenevole) {
		this.idbenevole = idbenevole;
		
		this.setTitle("Bienvenue " + idbenevole);
		this.setSize(600,600);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(Color.white);
		add(pan);
		
		labtitre = new JLabel("Votre compte");
		labtitre.setBounds(220,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre);
		
		labannonce = new JLabel("Annonces en attente de bénévole");
		labannonce.setBounds(120,100,400,30);
		labannonce.setFont(new Font("Arial",Font.BOLD,22));
		labannonce.setForeground(Color.black);
		pan.add(labannonce);
		
		try {
			//Si utilisateur est un valideur il peut également avoir un profil bénévole
			if (MainController.getTypeOfUser(idbenevole) == 2) {
				btvalideur = new JButton("Accéder à votre profil Valideur");
				btvalideur.setBounds(110,450,400,30);
				btvalideur.setBackground(Color.white);
				btvalideur.setFont(new Font("Arial",Font.BOLD,18));
				btvalideur.setForeground(Color.black);
				pan.add(btvalideur);
				btvalideur.addActionListener(this);
			}
		} catch (UnexistingUserException excp) {
			System.out.println("Erreur " + excp.getMessage());
			dispose();
		}
		
		demandes = new DefaultComboBoxModel<>();
		
		//liste des demandes disponibles
		for (int demande : MainController.getDemandsofStatus(StatutDemande.VALIDEE)){
			try {
				demandes.addElement(demande + "          " + 
				MainController.getInfoOfDemand(demande, "titre") + "          " +
				MainController.getInfoOfDemand(demande, "beneficiaire") + "          " + 
				MainController.getInfoOfDemand(demande, "jour") + "          " +
				MainController.getInfoOfDemand(demande, "ville"));
			} catch (UnexistingInfoException exc1) {
				System.out.println("erreur " + exc1.getMessage());
				dispose();
			} catch (UnexistingDemandException exc2) {
				System.out.println("erreur " + exc2.getMessage());
				dispose();
			} 
		}
		if (demandes.getSize() == 0) {//si aucune demande disponible
			demandes.addElement("Aucune annonce");			
		}
		
		//affichage des annonces dispo
		choixDemande = new JComboBox<>(demandes);
		pan.add(choixDemande);
		choixDemande.setSelectedIndex(0);
		choixDemande.setBounds(100,150,400,30);
		choixDemande.addItemListener(this);
		
		//bouton pour valider la selection d'une annonce
		btsuivant = new JButton("SUIVANT");
		btsuivant.setBounds(200,190,200,30);
		btsuivant.setBackground(Color.white);
		btsuivant.setFont(new Font("Arial",Font.BOLD,18));
		btsuivant.setForeground(Color.black);
		pan.add(btsuivant);
		btsuivant.addActionListener(this);
		
		//bouton pour que le bénévole accède à ses avis
		btavis = new JButton("VOIR MES AVIS");
		btavis.setBounds(200,350,200,30);
		btavis.setBackground(Color.white);
		btavis.setFont(new Font("Arial",Font.BOLD,18));
		btavis.setForeground(Color.black);
		pan.add(btavis);
		btavis.addActionListener(this);
		
		//bouton pour que le bénévole puisse changer ses infos personnelles
		btchangerinfos = new JButton("CHANGER VOS INFOS PERSONNELLES");
		btchangerinfos.setBounds(150,450,300,30);
		btchangerinfos.setBackground(Color.white);
		btchangerinfos.setFont(new Font("Arial",Font.BOLD,13));
		btchangerinfos.setForeground(Color.black);
		pan.add(btchangerinfos);
		btchangerinfos.addActionListener(this);		
		
		//bouton de déconnexion
		btdeconnexion = new JButton("DECONNEXION");
		btdeconnexion.setBounds(200,500,200,30);
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
	
	//méthode pour affichant pop up pour confirmer la déconnexion
	public void confirmationDeconnexion() {
		String[] options = {"oui", "non"};
		int choix = JOptionPane.showOptionDialog(null, "Voulez-vous vraiment vous déconnecter?",
            "Confirmation Déconnexion",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    	if (choix == 0) {
			ViewMain vm = new ViewMain();
			vm.setVisible(true);
			dispose();
    	} else if (choix == 1) {
    		//on ne fait rien
    	}
	}
	
	//gestion ItemListener
	public void itemStateChanged(ItemEvent i) {
		//null
	}
	
	//gestion des différents boutons
	public void actionPerformed(ActionEvent e) {
		//pour récupérer le numéro de la demande dans la chaine de caractères
		String[] word = ((String) choixDemande.getSelectedItem()).split(" ");
		if (e.getSource().equals(btsuivant)) {
			if ((String)choixDemande.getSelectedItem() == "Aucune annonce"){
				afficherAucuneAnnonce(); //pop up pour signaler au bénévole qu'aucune annonce n'a été sélectionné
			} else {
				//affichage de l'annonce plus en détails
				ViewDemandeBenevole vdb = new ViewDemandeBenevole(idbenevole,Integer.parseInt(word[0]));
				vdb.setVisible(true);
				demandes.removeElement(choixDemande.getSelectedItem());
        		if (demandes.getSize() == 0) {//si aucune annonce 
        			demandes.addElement("Aucune annonce");
        		}
			}
		} else {
			if (e.getSource().equals(btdeconnexion)) {
				confirmationDeconnexion(); //confiamation de la deconnexion
			}
			if (e.getSource().equals(btvalideur)) {
				//affichage du profil valideur du bénévole
				ViewValideur vva = new ViewValideur(idbenevole);
				vva.setVisible(true);
				dispose();
			}
			if (e.getSource().equals(btavis)) {
				//affichage de l'interface ViewAvis
				ViewAvis va = new ViewAvis(idbenevole);
				va.setVisible(true);
			}
			if (e.getSource().equals(btchangerinfos)) {
				//affichage de l'interface ChangementInfos
				ChangementInfos ci = new ChangementInfos(idbenevole);
				ci.setVisible(true);
			}
		}
	}

}
