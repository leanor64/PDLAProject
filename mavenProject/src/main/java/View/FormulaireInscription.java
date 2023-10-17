package View;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class FormulaireInscription  extends JFrame{
	
	//Attributs
	JLabel labtitre, labnom, labprenom, labage, labemail, labtelephone, labville, labadresse;
	JTextField jtfnom,jtfprenom,jtfage, jtfemail, jtftelephone, jtfville, jtfadresse;
	JButton btajout;
	
	//Constructeur
	public FormulaireInscription(){
		this.setTitle("Inscription");
		this.setSize(400,480);
		this.setLocationRelativeTo(null);
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(Color.orange);
		add(pan);
		
		labtitre = new JLabel("Formulaire d'inscription");
		labtitre.setBounds(60,10,300,30);
		labtitre.setFont(new Font("Arial",Font.BOLD,22));
		labtitre.setForeground(Color.black);
		pan.add(labtitre);
		
		labnom = new JLabel("Nom :");
		labnom.setBounds(20,60,300,30);
		labnom.setFont(new Font("Arial",Font.BOLD,18));
		labnom.setForeground(Color.black);
		pan.add(labnom);
		
		jtfnom = new JTextField();
		jtfnom.setBounds(130,60,200,25);
		pan.add(jtfnom);
		
		labprenom = new JLabel("Prénom :");
		labprenom.setBounds(20,100,300,30);
		labprenom.setFont(new Font("Arial",Font.BOLD,18));
		labprenom.setForeground(Color.black);
		pan.add(labprenom);
		
		jtfprenom = new JTextField();
		jtfprenom.setBounds(130,100,200,25);
		pan.add(jtfprenom);
		
		labage = new JLabel("Age :");
		labage.setBounds(20,140,300,30);
		labage.setFont(new Font("Arial",Font.BOLD,18));
		labage.setForeground(Color.black);
		pan.add(labage);
		
		jtfage=new JTextField();
		jtfage.setBounds(130,140,200,25);
		pan.add(jtfage);
		
		labemail = new JLabel("Email :");
		labemail.setBounds(20,180,300,30);
		labemail.setFont(new Font("Arial",Font.BOLD,18));
		labemail.setForeground(Color.black);
		pan.add(labemail);
		
		jtfemail = new JTextField();
		jtfemail.setBounds(130,180,200,25);
		pan.add(jtfemail);
		
		labtelephone = new JLabel("Telephone :");
		labtelephone.setBounds(20,220,300,30);
		labtelephone.setFont(new Font("Arial",Font.BOLD,18));
		labtelephone.setForeground(Color.black);
		pan.add(labtelephone);
		
		jtftelephone = new JTextField();
		jtftelephone.setBounds(130,220,200,25);
		pan.add(jtftelephone);
		
		labville = new JLabel("Ville :");
		labville.setBounds(20,260,300,30);
		labville.setFont(new Font("Arial",Font.BOLD,18));
		labville.setForeground(Color.black);
		pan.add(labville);
		
		jtfville = new JTextField();
		jtfville.setBounds(130,260,200,25);
		pan.add(jtfville);
		
		labadresse = new JLabel("Adresse :");
		labadresse.setBounds(20,300,300,30);
		labadresse.setFont(new Font("Arial",Font.BOLD,18));
		labadresse.setForeground(Color.black);
		pan.add(labadresse);
		
		jtfadresse = new JTextField();
		jtfadresse.setBounds(130,300,200,25);
		pan.add(jtfadresse);
		
		btajout = new JButton("Enregistrer");
		btajout.setBounds(150,360,150,30);
		btajout.setBackground(Color.white);
		btajout.setFont(new Font("Arial",Font.BOLD,18));
		btajout.setForeground(Color.black);
		pan.add(btajout);
		
	}

	public static void main(String[] args) {
		
     FormulaireInscription form = new FormulaireInscription();
     form.setVisible(true);
	}

}

