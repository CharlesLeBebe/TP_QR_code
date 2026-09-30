package vue;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
    private JTextField champTexte;
    private JButton boutonGenerer;

    public Fenetre() {
        
        setTitle("Générateur de pdf et QR Code");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 


        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

    
        JLabel label = new JLabel("Entrez votre texte ou lien :");
        champTexte = new JTextField(25); 
        boutonGenerer = new JButton("Générer"); 

        add(label);
        add(champTexte);
        add(boutonGenerer);
    }

    public String getTexteSaisi() {
        return champTexte.getText();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Fenetre fenetre = new Fenetre();
            fenetre.setVisible(true);
        });
    }
}