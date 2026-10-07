package vue;

import modele.ProfilStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {
    private JTextField champTexte;
    private JComboBox<String> comboPolices;
    private JButton boutonCouleur;
    private JButton boutonChoisirImage;
    private JLabel labelImageChoisie;
    private JButton boutonGenerer;

    private Color couleurChoisie = Color.BLACK;
    private String cheminImageChoisie = "";

    public Fenetre() {
        setTitle("Générateur PDF, QR Code & Style");
        setSize(500, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Texte / Lien :"), gbc);

        champTexte = new JTextField(22);
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        add(champTexte, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        add(new JLabel("Police :"), gbc);

        comboPolices = new JComboBox<>(ProfilStyle.POLICES_DISPONIBLES);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2;
        add(comboPolices, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        add(new JLabel("Couleur du texte :"), gbc);

        boutonCouleur = new JButton("Choisir une couleur");
        boutonCouleur.setBackground(couleurChoisie);
        boutonCouleur.setForeground(Color.WHITE);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2;
        add(boutonCouleur, gbc);

        boutonCouleur.addActionListener(e -> {
            Color couleur = JColorChooser.showDialog(this, "Sélectionnez une couleur", couleurChoisie);
            if (couleur != null) {
                couleurChoisie = couleur;
                boutonCouleur.setBackground(couleurChoisie);
            }
        });

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        add(new JLabel("Image (optionnel) :"), gbc);

        boutonChoisirImage = new JButton("Parcourir...");
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 1;
        add(boutonChoisirImage, gbc);

        labelImageChoisie = new JLabel("Aucune image");
        gbc.gridx = 2; gbc.gridy = 3;
        add(labelImageChoisie, gbc);

        boutonChoisirImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                cheminImageChoisie = fileChooser.getSelectedFile().getAbsolutePath();
                labelImageChoisie.setText(fileChooser.getSelectedFile().getName());
            }
        });

        boutonGenerer = new JButton("Générer le PDF");
        boutonGenerer.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3;
        add(boutonGenerer, gbc);
    }

    public String getTexteSaisi() { return champTexte.getText(); }
    public String getPoliceSelectionnee() { return (String) comboPolices.getSelectedItem(); }
    public Color getCouleurChoisie() { return couleurChoisie; }
    public String getCheminImageChoisie() { return cheminImageChoisie; }

    public void addGenererListener(ActionListener listener) { 
        boutonGenerer.addActionListener(listener); 
    }
}