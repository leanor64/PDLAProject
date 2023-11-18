package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewBenevole extends JFrame implements ActionListener{

	
	//Attributs
	String idbenevole;
	JLabel labtitre;
	JButton btdeconnexion;
	//peut voir ses annonces
	
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
		
		btdeconnexion = new JButton("DECONNEXION");
		btdeconnexion.setBounds(310,700,300,30);
		btdeconnexion.setBackground(Color.white);
		btdeconnexion.setFont(new Font("Arial",Font.BOLD,18));
		btdeconnexion.setForeground(Color.black);
		pan.add(btdeconnexion);
		btdeconnexion.addActionListener(this);
	}
	
	
	//Méthodes
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btdeconnexion) {
			this.setVisible(false);
		}
		
	}
	
	public static void main(String[] args) {
		String benevole;
		benevole = "benev";
		
		ViewBenevole vbn = new ViewBenevole(benevole);
	    vbn.setVisible(true);
		}
}
