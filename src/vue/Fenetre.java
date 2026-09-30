package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {
    private JTextField champTexte;
    private JButton boutonGenerer;

    public Fenetre() {
        setTitle("Générateur pdf et QR Code");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JLabel label = new JLabel("Entrez votre texte :");
        champTexte = new JTextField(25);
        boutonGenerer = new JButton("Générer le PDF");

        add(label);
        add(champTexte);
        add(boutonGenerer);
    }

    public String getTexteSaisi() {
        return champTexte.getText();
    }

    public void addGenererListener(ActionListener listener) {
        boutonGenerer.addActionListener(listener);
    }
}