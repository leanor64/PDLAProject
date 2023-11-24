package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class ViewMain extends JFrame implements ActionListener {
	

	//Attributs
		JLabel labtitre;
		JButton btconnex, btinscr;

		//Constructeur
		public ViewMain(){

			
			this.setTitle("Bienvenue sur HelpMe");
			this.setSize(700,500);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			
			Color custom = new Color(204, 153, 255);
			JPanel pan = new JPanel();
			pan.setLayout(null);
			pan.setBackground(custom);
			add(pan);

			labtitre = new JLabel("Bonjour, que désirez-vous faire sur l'application?");
			labtitre.setBounds(70,30,600,30);
			labtitre.setFont(new Font("Arial",Font.BOLD,22));
			labtitre.setForeground(Color.black);
			pan.add(labtitre);

			btinscr = new JButton("S'inscrire");
			btinscr.setBounds(60,220,250,50);
			btinscr.setBackground(Color.white);
			btinscr.setFont(new Font("Arial",Font.BOLD,18));
			btinscr.setForeground(Color.black);
			pan.add(btinscr);
			btinscr.addActionListener(this);
			
			btconnex = new JButton("Se connecter");
			btconnex.setBounds(350,220,250,50);
			btconnex.setBackground(Color.white);
			btconnex.setFont(new Font("Arial",Font.BOLD,18));
			btconnex.setForeground(Color.black);
			pan.add(btconnex);
			btconnex.addActionListener(this);

		}
		
		//Méthodes
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btinscr)) {
				this.setVisible(false);
				FormulaireInscription fi = new FormulaireInscription();
				fi.setVisible(true);
			} else if (e.getSource().equals(btconnex)) {
				this.setVisible(false);
				FormulaireConnexion fc = new FormulaireConnexion();
				fc.setVisible(true);
			}
		}

		public static void main(String[] args) {

	     ViewMain main = new ViewMain();
	     main.setVisible(true);
		}

}