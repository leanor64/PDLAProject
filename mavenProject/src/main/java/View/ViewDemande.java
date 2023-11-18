package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewDemande extends JFrame implements ActionListener{
	
	//Attributs
	
		JLabel labtitre;
		JTextField jtfmotif;
		JButton btoui, btnon;
		int askmotif;
		
		//Constructeur
		public ViewDemande() {
			//this.idbeneficiaire = idbeneficiaire;
			
			this.setTitle("Bienvenue");
			this.setSize(500,500);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			
			Color custom = new Color(204, 153, 255);
			JPanel pan = new JPanel();
			pan.setLayout(null);
			pan.setBackground(custom);
			add(pan);
			
			labtitre = new JLabel("Voulez-vous valider cette annonce?");
			labtitre.setBounds(50,10,600,70);
			labtitre.setFont(new Font("Arial",Font.BOLD,22));
			labtitre.setForeground(Color.black);
			pan.add(labtitre);
			
			btoui = new JButton("OUI");
			btoui.setBounds(310,350,150,30);
			btoui.setBackground(Color.white);
			btoui.setFont(new Font("Arial",Font.BOLD,18));
			btoui.setForeground(Color.black);
			pan.add(btoui);
			btoui.addActionListener(this);
			
			btnon = new JButton("NON");
			btnon.setBounds(20,350,150,30);
			btnon.setBackground(Color.white);
			btnon.setFont(new Font("Arial",Font.BOLD,18));
			btnon.setForeground(Color.black);
			pan.add(btnon);
			btnon.addActionListener(this);
			
		}
		
		//Méthodes
			
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btoui) {
				//changer l'état de l'annonce à validée
				this.setVisible(false);
			}
			if (e.getSource() == btnon) {
				//changer statut de l'annonce à refusée
				String[] options = {"abusive", "date passée", "autre"};
		        int motif = JOptionPane.showOptionDialog(null, "Pourquoi estimez-vous que cette annonce est invalidable?",
		                "Choisissez un motif",
		                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		        System.out.println(motif);
		        //prendre en compte le motif
		        this.setVisible(false);
			}
			
		}
		
		public static void main(String[] args) {
						
			ViewDemande vde = new ViewDemande();
		    vde.setVisible(true);
			}

}
