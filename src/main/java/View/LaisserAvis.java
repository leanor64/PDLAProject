package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.*;
import Model.BadLengthException;
import Model.StatutDemande;
import Model.UnexistingDemandException;
import Model.UnexistingUserException;

//interface via laquelle un bénéficiare peut laisser un avis à un bénéficiaire
public class LaisserAvis extends JFrame implements ListSelectionListener, ActionListener, KeyListener{
	

	//Attributs
	int nodemande;
	String idbenef;
	String idbenev;
	JLabel labtitre, labavis, labnote, etiquette;
	JTextField jtfavis;
	JList<String> liste;
	String choix[] = {"0", "1", "2", "3", "4", "5"};
	JButton btajout;
	boolean avisOK;
	
	//Constructeur
	public LaisserAvis(int nodemande,String idbenef, String idbenev) {
		this.nodemande = nodemande;
		this.idbenef = idbenef;
		this.idbenev = idbenev;
		
		avisOK = false;
		
		this.setTitle("Votre retour d'experience");
		this.setSize(600,600);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(Color.white);
		add(pan);
		
		labtitre = new JLabel("Laisser un avis à " + idbenev);
		labtitre.setBounds(170,30,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre, BorderLayout.CENTER);
		
		labavis = new JLabel("Laissez votre avis :");
		labavis.setBounds(20,150,300,30);
		labavis.setFont(new Font("Arial",Font.BOLD,18));
		labavis.setForeground(Color.black);
		pan.add(labavis);
		
		//zone de texte pour saisir l'avis
		jtfavis = new JTextField();
		jtfavis.setBounds(220,150,300,100);
		jtfavis.addKeyListener(this);
		pan.add(jtfavis);
		
		labnote = new JLabel("Laissez une note :");
		labnote.setBounds(20,350,300,30);
		labnote.setFont(new Font("Arial",Font.BOLD,18));
		labnote.setForeground(Color.black);
		pan.add(labnote);
		
		//le bénévole peut sélectionner une note entre 0 et 5
		liste = new JList<>(choix);
		liste.setBounds(220,350,300,110);
		liste.addListSelectionListener(this);
		etiquette = new JLabel(" ");
		pan.add(etiquette);
		pan.add(liste);
		etiquette.setText((String)liste.getSelectedValue());
			
		//bouton pour enregistrer l'avis
		btajout = new JButton("Enregistrer");
		btajout.setBounds(200,500,150,30);
		btajout.setBackground(Color.white);
		btajout.setFont(new Font("Arial",Font.BOLD,18));
		btajout.setForeground(Color.black);
		pan.add(btajout);
		btajout.addActionListener(this);
		
		
	}
	
	//Méthodes
	

	public void afficherTailleNonValide(String info) {
		JOptionPane.showMessageDialog(this,info + " invalide, attention à respecter le nombre de caractères");
	}
	
	//méthodes pour interdire la saisie de certains caractères spéciaux
	public boolean isCaractereAutorise(char c) {
	    return c != '"' && c != '\'' && c != '`' && c != '\\';
	}
	public void keyTyped(KeyEvent k) {
        if (!isCaractereAutorise(k.getKeyChar())) {
            k.consume();
        }
    }	
	public void keyPressed(KeyEvent e) {
	    // Ne rien faire ici, car nous n'utilisons pas keyPressed
	}
	public void keyReleased(KeyEvent e) {
	    // Ne rien faire ici, car nous n'utilisons pas keyReleased
	}
	
	//méthode pour gérer le bouton "enregistrer"
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btajout)) {
			try {
				//ajout de l'avis dans la database
				MainController.NewAvis(jtfavis.getText(),idbenev,idbenef,Integer.parseInt(((String)liste.getSelectedValue())));
				avisOK = true;
				//changement de l'état de la demande dans la database
				MainController.setStatusOfDemand(nodemande, StatutDemande.TERMINEE_EVALUEE);
			} catch (BadLengthException exc1) {
					afficherTailleNonValide(exc1.getMessage());
			} catch (UnexistingUserException exc2) {
				System.out.println("Erreur " + exc2.getMessage());
				dispose();
			} catch (UnexistingDemandException exc3) {
				System.out.println("Erreur " + exc3.getMessage());
				dispose();
			} catch (NumberFormatException exc4) {
				System.out.println("Erreur " + exc4.getMessage());
				dispose();
			}
		}
		if (avisOK == true) {
			//retour au profil principal du bénéficiaire
			dispose();
		}
	}
	
	//méthode pour gérer la note sélectionnée par le bénéficiaire
	public void valueChanged(ListSelectionEvent evt) { 
		 etiquette.setText((String)liste.getSelectedValue());
	}

}
