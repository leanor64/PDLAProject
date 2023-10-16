package mavenProject;
import javax.swing.*;
import java.awt.*;

public class Main {
	
	public static void main(String[] args) {
		
		// Définition du frame
        JFrame frame = new JFrame("Béné'VOLE");
    
        JLabel label = new JLabel("Bienvenue sur l'application Béné'VOLE", JLabel.CENTER);
        frame.add(label);
        
        // Création des boutons radio
        ButtonGroup group = new ButtonGroup();     
        JRadioButton radio1 = new JRadioButton("ON", true);
        JRadioButton radio2 = new JRadioButton("OFF", false);
        
        // Ajout les boutons radio au groupe
        group.add(radio1);
        group.add(radio2);
        
        // Définition du panel
        JPanel panel = new JPanel();
        
        // Définition du menu principal
        JMenuBar menu = new JMenuBar();
        JMenu signin = new JMenu("M'inscrire");
        JMenu connect = new JMenu("Me connecter");
        JMenu general = new JMenu("Menu");
         
        // Définition du sous-menu pour M'inscrire
        JMenuItem create = new JMenuItem("Créer une annonce");
        JMenuItem help = new JMenuItem("Aider");
 
        general.add(create);
        general.add(help);
         
        menu.add(signin);
        menu.add(connect);
        menu.add(general);
         
        frame.setLayout(new GridLayout(5, 1));
    
        frame.add(menu);
        frame.add(panel);
        frame.pack();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
