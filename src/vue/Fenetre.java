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

    private JTextField champLargeurImage;
    private JComboBox<String> comboAlignementImage;

    private JButton boutonGenerer;
    private JButton boutonSauvegarderProjet;
    private JButton boutonChargerProjet;


    private JButton boutonSauvegarderProfil;
    private JButton boutonChargerProfil;

    private Color couleurChoisie = Color.BLACK;
    private String cheminImageChoisie = "";

    public Fenetre() {
        setTitle("Générateur PDF, QR Code & Style");
        setSize(560, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
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
        add(new JLabel("Couleur :"), gbc);
        boutonCouleur = new JButton("Choisir une couleur");
        boutonCouleur.setBackground(couleurChoisie);
        boutonCouleur.setForeground(Color.WHITE);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2;
        add(boutonCouleur, gbc);

        boutonCouleur.addActionListener(e -> {
            Color couleur = JColorChooser.showDialog(this, "Sélectionnez une couleur", couleurChoisie);
            if (couleur != null) {
                setCouleurChoisie(couleur);
            }
        });

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        add(new JLabel("Image :"), gbc);
        boutonChoisirImage = new JButton("Parcourir...");
        gbc.gridx = 1; gbc.gridy = 3;
        add(boutonChoisirImage, gbc);
        labelImageChoisie = new JLabel("Aucune image");
        gbc.gridx = 2; gbc.gridy = 3;
        add(labelImageChoisie, gbc);

        boutonChoisirImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                setCheminImageChoisie(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        add(new JLabel("Largeur Max (px) :"), gbc);
        champLargeurImage = new JTextField("180", 6);
        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 2;
        add(champLargeurImage, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 1;
        add(new JLabel("Position Image :"), gbc);
        String[] positions = {"Centre", "Gauche", "Droite"};
        comboAlignementImage = new JComboBox<>(positions);
        gbc.gridx = 1; gbc.gridy = 5; gbc.gridwidth = 2;
        add(comboAlignementImage, gbc);

        JPanel panneauProfil = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        boutonSauvegarderProfil = new JButton("Sauvegarder Profil");
        boutonChargerProfil = new JButton("Charger Profil");
        panneauProfil.add(boutonSauvegarderProfil);
        panneauProfil.add(boutonChargerProfil);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 3;
        add(panneauProfil, gbc);

        boutonGenerer = new JButton("Générer le PDF");
        boutonGenerer.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 3;
        add(boutonGenerer, gbc);

        JPanel panneauProjet = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        boutonSauvegarderProjet = new JButton("Sauvegarder Projet");
        boutonChargerProjet = new JButton("Charger Projet");
        panneauProjet.add(boutonSauvegarderProjet);
        panneauProjet.add(boutonChargerProjet);

        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 3;
        add(panneauProjet, gbc);
    }

    public String getTexteSaisi() { return champTexte.getText(); }
    public void setTexteSaisi(String texte) { champTexte.setText(texte); }

    public String getPoliceSelectionnee() { return (String) comboPolices.getSelectedItem(); }
    public void setPoliceSelectionnee(String police) { comboPolices.setSelectedItem(police); }

    public Color getCouleurChoisie() { return couleurChoisie; }
    public void setCouleurChoisie(Color couleur) {
        if (couleur != null) {
            this.couleurChoisie = couleur;
            boutonCouleur.setBackground(couleur);
        }
    }

    public String getCheminImageChoisie() { return cheminImageChoisie; }
    public void setCheminImageChoisie(String chemin) {
        this.cheminImageChoisie = chemin;
        if (chemin != null && !chemin.trim().isEmpty()) {
            labelImageChoisie.setText(new java.io.File(chemin).getName());
        } else {
            labelImageChoisie.setText("Aucune image");
        }
    }

    public int getLargeurMaxImage() {
        try {
            int l = Integer.parseInt(champLargeurImage.getText().trim());
            return Math.min(Math.max(l, 20), 500);
        } catch (NumberFormatException e) { return 180; }
    }
    public void setLargeurMaxImage(int l) { champLargeurImage.setText(String.valueOf(l)); }

    public String getAlignementImage() { return (String) comboAlignementImage.getSelectedItem(); }
    public void setAlignementImage(String alignement) { comboAlignementImage.setSelectedItem(alignement); }

    public void addGenererListener(ActionListener listener) { boutonGenerer.addActionListener(listener); }
    public void addSauvegarderProjetListener(ActionListener listener) { boutonSauvegarderProjet.addActionListener(listener); }
    public void addChargerProjetListener(ActionListener listener) { boutonChargerProjet.addActionListener(listener); }
    public void addSauvegarderProfilListener(ActionListener listener) { boutonSauvegarderProfil.addActionListener(listener); }
    public void addChargerProfilListener(ActionListener listener) { boutonChargerProfil.addActionListener(listener); }
}