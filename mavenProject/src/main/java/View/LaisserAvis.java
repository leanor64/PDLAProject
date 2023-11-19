package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.*;


public class LaisserAvis extends JFrame implements ListSelectionListener, ActionListener{
	
	MainController controller = new MainController();
	
	//Attributs
	String idbenef;
	String idbenev;
	JLabel labtitre, labavis, labnote;
	JTextField jtfavis;
	JList liste = new JList();
	JLabel etiquette = new JLabel(" ");
	String choix[] = {"0", "1", "2", "3", "4", "5"};
	JButton btajout;
	boolean avisOK;
	
	//Constructeur
	public LaisserAvis(String idbenef, String idbenev) { //SUPPRIMER BENEV ET BENEF QUAND ON AURA FAIT LA FRAME DEMANDE ...
		this.idbenef = idbenef;
		this.idbenev = idbenev;
		
		avisOK = false;
		
		this.setTitle("Votre retour d'expérience") /* à " + benev.getPrenom())*/;
		this.setSize(600,500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		Color custom = new Color(204, 153, 255);
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(custom);
		add(pan, BorderLayout.CENTER);
		
		labtitre = new JLabel("Laisser un avis à " + idbenev);
		labtitre.setBounds(150,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre, BorderLayout.CENTER);
		
		labavis = new JLabel("Laissez votre avis :");
		labavis.setBounds(20,60,300,30);
		labavis.setFont(new Font("Arial",Font.BOLD,18));
		labavis.setForeground(Color.black);
		pan.add(labavis);
		
		jtfavis = new JTextField();
		jtfavis.setBounds(220,60,300,100);
		pan.add(jtfavis);
		
		labnote = new JLabel("Laissez une note :");
		labnote.setBounds(20,200,300,30);
		labnote.setFont(new Font("Arial",Font.BOLD,18));
		labnote.setForeground(Color.black);
		pan.add(labnote);
		
		liste = new JList(choix);
		liste.setBounds(220,200,300,110);
		liste.addListSelectionListener(this);
		pan.add(etiquette);
		pan.add(liste);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		etiquette.setText((String)liste.getSelectedValue());
			
		btajout = new JButton("Enregistrer");
		btajout.setBounds(200,370,150,30);
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
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btajout) {
			System.out.println("vous avez cliqué sur le bouton Enregistrer");
			try {
			controller.NewAvis(jtfavis.getText(),idbenev,idbenef,Integer.parseInt(((String)liste.getSelectedValue())));
			avisOK = true;
			} catch (BadLengthException exc1) {
				afficherTailleNonValide(exc1.getMessage());
			} catch (UnexistingUserException exc2) {
				System.out.println("Erreur connexion");
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
		benev = "benev";
		benef = "benef";
	    
		LaisserAvis avis = new LaisserAvis(benef,benev);
	    avis.setVisible(true);
	}

	

}
