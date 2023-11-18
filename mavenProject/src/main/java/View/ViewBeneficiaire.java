package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ViewBeneficiaire extends JFrame implements ActionListener{

	
	//Attributs
	
	String idbeneficiaire, idbenevole;
	JLabel labtitre;
	JButton btposterannonce, btdonneravis, btdeconnexion;
	//peut voir ses annonces
	
	//Constructeur
	public ViewBeneficiaire(String idbeneficiaire) {
		this.idbeneficiaire = idbeneficiaire;
		
		this.setTitle("Bienvenue " + idbeneficiaire);
		this.setSize(700,700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		Color custom = new Color(204, 153, 255);
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(custom);
		add(pan);
		
		labtitre = new JLabel("Votre compte");
		labtitre.setBounds(270,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre);
		
		btposterannonce = new JButton("POSTER UNE ANNONCE");
		btposterannonce.setBounds(360,250,300,70);
		btposterannonce.setBackground(Color.white);
		btposterannonce.setFont(new Font("Arial",Font.BOLD,18));
		btposterannonce.setForeground(Color.black);
		pan.add(btposterannonce);
		btposterannonce.addActionListener(this);
		
		btdonneravis = new JButton("DONNER UN AVIS");
		btdonneravis.setBounds(20,250,300,70);
		btdonneravis.setBackground(Color.white);
		btdonneravis.setFont(new Font("Arial",Font.BOLD,18));
		btdonneravis.setForeground(Color.black);
		pan.add(btdonneravis);
		btdonneravis.addActionListener(this);
		
		btdeconnexion = new JButton("DECONNEXION");
		btdeconnexion.setBounds(190,600,300,30);
		btdeconnexion.setBackground(Color.white);
		btdeconnexion.setFont(new Font("Arial",Font.BOLD,18));
		btdeconnexion.setForeground(Color.black);
		pan.add(btdeconnexion);
		btdeconnexion.addActionListener(this);
	}
	
	//Méthodes
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btposterannonce) {
			FormulaireDemande fd = new FormulaireDemande(idbeneficiaire);
			fd.setVisible(true);
		}
		if (e.getSource() == btdonneravis) {
			//setVisible une Frame avec la liste des annonces terminées pas évaluées
			//Selectionne celle qu'il veut (get le benevole → idbenevole)
			//LaisserAvis la = new LaisserAvis(idbeneficiaire,idbenevole);
			//la.setVisible(true);
		}
		if (e.getSource() == btdeconnexion) {
			this.setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		String beneficiaire;
		beneficiaire = "benef";
		
		ViewBeneficiaire vbf = new ViewBeneficiaire(beneficiaire);
	    vbf.setVisible(true);
		}
}
