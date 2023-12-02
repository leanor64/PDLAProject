package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.*;

import Controller.*;
import Model.BadLengthException;
import Model.UnexistingUserException;

//Interface pour que le bénéficiaire puisse décrire et soumettre une annonce/demande d'aide
public class FormulaireDemande extends JFrame implements ActionListener, KeyListener{
	
	//Attributs
	String idbenef;
	JLabel labtitre,labtitredemande, labdemande, labdate, labville;
	JTextField jtftitredemande, jtfdemande, jtfdate, jtfville;
	JButton btajout;
	boolean demandeOK;
	
	//Constructeur
	public FormulaireDemande(String idbenef) {

		this.idbenef = idbenef;
		
		demandeOK = false;
		
		this.setTitle("Postez une annonce");
		this.setSize(600,600);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(Color.white);
		add(pan);
		
		labtitre = new JLabel("Votre annonce");
		labtitre.setBounds(220,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre, BorderLayout.CENTER);
		
		labtitredemande = new JLabel("Saisissez le titre :");
		labtitredemande.setBounds(20,90,300,30);
		labtitredemande.setFont(new Font("Arial",Font.BOLD,18));
		labtitredemande.setForeground(Color.black);
		pan.add(labtitredemande);
		
		//zone de texte pour saisir le titre de l'annonce
		jtftitredemande = new JTextField();
		jtftitredemande.setBounds(280,90,300,30);
		jtftitredemande.addKeyListener(this);
		pan.add(jtftitredemande);
		
		labdemande = new JLabel("Saisissez votre demande :");
		labdemande.setBounds(20,130,300,30);
		labdemande.setFont(new Font("Arial",Font.BOLD,18));
		labdemande.setForeground(Color.black);
		pan.add(labdemande, BorderLayout.CENTER);
		
		//zone de texte pour saisir précisément la demande
		jtfdemande = new JTextField();
		jtfdemande.setBounds(280,130,300,100);
		jtfdemande.addKeyListener(this);
		pan.add(jtfdemande);
		
		labdate = new JLabel("Saisissez la date concernée :");
		labdate.setBounds(20,270,300,30);
		labdate.setFont(new Font("Arial",Font.BOLD,18));
		labdate.setForeground(Color.black);
		pan.add(labdate);
		
		//zone de texte pour saisir la date
		jtfdate = new JTextField();
		jtfdate.setBounds(280,270,300,25);
		jtfdate.addKeyListener(this);
		pan.add(jtfdate);
		
		labville = new JLabel("Saisissez la ville concernée :");
		labville.setBounds(20,310,300,30);
		labville.setFont(new Font("Arial",Font.BOLD,18));
		labville.setForeground(Color.black);
		pan.add(labville);
		
		//zone de texte pour saisir la ville
		jtfville = new JTextField();
		jtfville.setBounds(280,310,300,25);
		jtfville.addKeyListener(this);
		pan.add(jtfville);
		
		//bouton pour enregistrer et soumettre l'annonce
		btajout = new JButton("Enregistrer");
		btajout.setBounds(220,450,150,30);
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
	
	public void afficherInfoNulle(String info) {
		JOptionPane.showMessageDialog(this, info + " invalide, veuillez rentrer votre " + info);
	}
	
	//méthodes pour interdire certains caractères spéciaux
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
	
	//méthode pour gérer le bouton
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btajout)) {//si l'utilisateur appuie sur le bonton "enregistrer"
			try {
				//on ajoute sa demande à la base de données
				MainController.NewDemande(jtftitredemande.getText(),jtfdemande.getText(),idbenef,jtfdate.getText(), jtfville.getText());
				demandeOK = true;
			} catch (BadLengthException exc1) {
				afficherTailleNonValide("Erreur " + exc1.getMessage());
				dispose();
			} catch (UnexistingUserException exc2) {
				System.out.println("Erreur " + exc2.getMessage());
				dispose();
			}
		}
		if (demandeOK == true) {
			//on revient sur le profil principal du bénéficiaire
			dispose();
		}
	}
	
	//TODO : a enlever
	public static void main(String[] args) {
		String beneficiaire;
		beneficiaire = "test1";
	    
		FormulaireDemande demande = new FormulaireDemande(beneficiaire);
	    demande.setVisible(true);
		}

	

}