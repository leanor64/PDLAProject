package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.*;
import Model.BadLengthException;
import Model.StatutDemande;
import Model.UnexistingDemandException;
import Model.UnexistingUserException;


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
		
		this.setTitle("Votre retour d'expérience") /* à " + benev.getPrenom())*/;
		this.setSize(600,600);
		this.setResizable(false);
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
		
		jtfavis = new JTextField();
		jtfavis.setBounds(220,150,300,100);
		jtfavis.addKeyListener(this);
		pan.add(jtfavis);
		
		labnote = new JLabel("Laissez une note :");
		labnote.setBounds(20,350,300,30);
		labnote.setFont(new Font("Arial",Font.BOLD,18));
		labnote.setForeground(Color.black);
		pan.add(labnote);
		
		liste = new JList<>(choix);
		liste.setBounds(220,350,300,110);
		liste.addListSelectionListener(this);
		etiquette = new JLabel(" ");
		pan.add(etiquette);
		pan.add(liste);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		etiquette.setText((String)liste.getSelectedValue());
			
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
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btajout)) {
			try {
				System.out.println("avis :" + jtfavis.getText());
				System.out.println("benev :" + idbenev);
				System.out.println("benef :" + idbenef);
				System.out.println("benef :" + (String)liste.getSelectedValue());
			MainController.NewAvis(jtfavis.getText(),idbenev,idbenef,Integer.parseInt(((String)liste.getSelectedValue())));
			avisOK = true;
			MainController.setStatusOfDemand(nodemande, StatutDemande.TERMINEE_EVALUEE);
			} catch (BadLengthException exc1) {
				afficherTailleNonValide(exc1.getMessage());
			} catch (UnexistingUserException exc2) {
				System.out.println("Erreur connexion");
			} catch (UnexistingDemandException exc3) {
				System.out.println("Erreur demande inexistante");
			}
		}
		if (avisOK == true) {
			this.setVisible(false);
		
		}
	}
	
	public void valueChanged(ListSelectionEvent evt) { 
		 etiquette.setText((String)liste.getSelectedValue());
	}
	
	
	public static void main(String[] args) {
		String benev, benef;
		int no;
		benev = "test0";
		benef = "test1";
		no = 7;
	    
		LaisserAvis avis = new LaisserAvis(no,benef,benev);
	    avis.setVisible(true);
	}

	

}
