package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

//Interface principale de l'application
public class ViewMain extends JFrame implements ActionListener {
	

	//Attributs
		JLabel labtitre;
		JButton btconnex, btinscr;

		//Constructeur
		public ViewMain(){

			
			this.setTitle("Bienvenue sur l'application d'entraide");
			this.setSize(600,600);
			this.setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);

			JPanel pan = new JPanel();
			pan.setLayout(null);
			pan.setBackground(Color.white);
			add(pan);

			labtitre = new JLabel("Bonjour, que désirez-vous faire sur l'application?");
			labtitre.setBounds(70,50,600,30);
			labtitre.setFont(new Font("Arial",Font.BOLD,18));
			labtitre.setForeground(Color.black);
			pan.add(labtitre);

			//bouton pour s'inscrire à l'application
			btinscr = new JButton("M'inscrire");
			btinscr.setBounds(60,250,200,30);
			btinscr.setBackground(Color.white);
			btinscr.setFont(new Font("Arial",Font.BOLD,18));
			btinscr.setForeground(Color.black);
			pan.add(btinscr);
			btinscr.addActionListener(this);
			
			//bouton pour se connecter à l'application
			btconnex = new JButton("Me connecter");
			btconnex.setBounds(330,250,200,30);
			btconnex.setBackground(Color.white);
			btconnex.setFont(new Font("Arial",Font.BOLD,18));
			btconnex.setForeground(Color.black);
			pan.add(btconnex);
			btconnex.addActionListener(this);

		}
		
		//Méthodes
		
		//méthode pour gérer les différents boutons
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btinscr)) {
				//affichage de l'interface d'inscription
				FormulaireInscription fi = new FormulaireInscription();
				fi.setVisible(true);
				dispose();
			} else if (e.getSource().equals(btconnex)) {
				//affichage de l'interface de la connexion
				FormulaireConnexion fc = new FormulaireConnexion();
				fc.setVisible(true);
				dispose();
			}
		}

}