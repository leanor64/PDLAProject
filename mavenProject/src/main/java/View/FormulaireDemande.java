package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import Controller.*;

public class FormulaireDemande extends JFrame implements ActionListener, KeyListener{
	
	MainController controller = new MainController();
	
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
		this.setSize(650,450);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		Color custom = new Color(204, 153, 255);
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(custom);
		add(pan, BorderLayout.CENTER);
		
		labtitre = new JLabel("Votre annonce");
		labtitre.setBounds(220,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre, BorderLayout.CENTER);
		
		labtitredemande = new JLabel("Saisissez le titre :");
		labtitredemande.setBounds(20,50,300,30);
		labtitredemande.setFont(new Font("Arial",Font.BOLD,18));
		labtitredemande.setForeground(Color.black);
		pan.add(labtitredemande);
		
		jtftitredemande = new JTextField();
		jtftitredemande.setBounds(280,50,300,30);
		jtftitredemande.addKeyListener(this);
		pan.add(jtftitredemande);
		
		labdemande = new JLabel("Saisissez votre demande :");
		labdemande.setBounds(20,90,300,30);
		labdemande.setFont(new Font("Arial",Font.BOLD,18));
		labdemande.setForeground(Color.black);
		pan.add(labdemande, BorderLayout.CENTER);
		
		jtfdemande = new JTextField();
		jtfdemande.setBounds(280,90,300,100);
		jtfdemande.addKeyListener(this);
		pan.add(jtfdemande);
		
		labdate = new JLabel("Saisissez la date concernée :");
		labdate.setBounds(20,230,300,30);
		labdate.setFont(new Font("Arial",Font.BOLD,18));
		labdate.setForeground(Color.black);
		pan.add(labdate);
		
		jtfdate = new JTextField();
		jtfdate.setBounds(280,230,300,25);
		jtfdate.addKeyListener(this);
		pan.add(jtfdate);
		
		labville = new JLabel("Saisissez la ville concernée :");
		labville.setBounds(20,270,300,30);
		labville.setFont(new Font("Arial",Font.BOLD,18));
		labville.setForeground(Color.black);
		pan.add(labville);
		
		jtfville = new JTextField();
		jtfville.setBounds(280,270,300,25);
		jtfville.addKeyListener(this);
		pan.add(jtfville);
		
		
		btajout = new JButton("Enregistrer");
		btajout.setBounds(220,340,150,30);
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
		if (e.getSource() == btajout) {
			//System.out.println("vous avez cliqué sur le bouton Enregistrer");
			try {
				controller.NewDemande(jtftitredemande.getText(),jtfdemande.getText(),idbenef,jtfdate.getText(), jtfville.getText());
				demandeOK = true;
			} catch (BadLengthException exc1) {
				afficherTailleNonValide(exc1.getMessage());
			//} catch (InfoNullException exc2) {
				//afficherInfoNulle(exc2.getMessage());
			}
		}
		if (demandeOK == true) {
			this.setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		String beneficiaire;
		beneficiaire = "benef";
	    
		FormulaireDemande demande = new FormulaireDemande(beneficiaire);
	    demande.setVisible(true);
		}

	

}