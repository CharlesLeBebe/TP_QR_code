package controleur;

import modele.*;
import vue.Fenetre;

import javax.swing.*;

public class Controleur {
    private Fenetre fenetre;

    public Controleur(Fenetre fenetre) {
        this.fenetre = fenetre;

        this.fenetre.addGenererListener(e -> declencherGenerationGlobale());
        
        this.fenetre.addSauvegarderProjetListener(e -> sauvegarderProjet());
        this.fenetre.addChargerProjetListener(e -> chargerProjet());

        this.fenetre.addSauvegarderProfilListener(e -> sauvegarderProfil());
        this.fenetre.addChargerProfilListener(e -> chargerProfil());
    }

    private void declencherGenerationGlobale() {
        String saisie = fenetre.getTexteSaisi();

        if (saisie == null || saisie.trim().isEmpty()) {
            JOptionPane.showMessageDialog(fenetre, "Veuillez saisir du texte ou un lien !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Donnees donnees = new Donnees(saisie);
        ProfilStyle style = new ProfilStyle(fenetre.getPoliceSelectionnee(), 12, fenetre.getCouleurChoisie());

        String cheminQRCode = "qrcode.png";
        String cheminPDF = "document_avec_qr.pdf";

        try {
            GenerateurQRCode.creerQRCode(donnees.getTexte(), 200, 200, cheminQRCode);
            GenerateurPDF.creerPDF(
                donnees.getTexte(),
                cheminQRCode,
                fenetre.getCheminImageChoisie(),
                fenetre.getLargeurMaxImage(),
                fenetre.getAlignementImage(),
                style,
                cheminPDF
            );

            JOptionPane.showMessageDialog(fenetre, "Succès ! Le PDF a été généré.", "Opération réussie", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(fenetre, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sauvegarderProfil() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sauvegarder le profil de style");
        if (fileChooser.showSaveDialog(fenetre) == JFileChooser.APPROVE_OPTION) {
            String chemin = fileChooser.getSelectedFile().getAbsolutePath();
            if (!chemin.endsWith(".style")) chemin += ".style";

            ProfilStyle style = new ProfilStyle(
                fenetre.getPoliceSelectionnee(),
                12,
                fenetre.getCouleurChoisie()
            );

            try {
                GestionnaireFichiers.sauvegarderObjet(style, chemin);
                JOptionPane.showMessageDialog(fenetre, "Profil de style sauvegardé !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(fenetre, "Erreur lors de la sauvegarde du profil : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void chargerProfil() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Charger un profil de style");
        if (fileChooser.showOpenDialog(fenetre) == JFileChooser.APPROVE_OPTION) {
            try {
                ProfilStyle style = (ProfilStyle) GestionnaireFichiers.chargerObjet(fileChooser.getSelectedFile().getAbsolutePath());

                fenetre.setPoliceSelectionnee(style.getNomPolice());
                fenetre.setCouleurChoisie(style.getCouleurAwt());

                JOptionPane.showMessageDialog(fenetre, "Profil de style appliqué avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(fenetre, "Fichier de profil invalide : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void sauvegarderProjet() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sauvegarder le projet");
        if (fileChooser.showSaveDialog(fenetre) == JFileChooser.APPROVE_OPTION) {
            String chemin = fileChooser.getSelectedFile().getAbsolutePath();
            if (!chemin.endsWith(".ser")) chemin += ".ser";

            ProfilStyle style = new ProfilStyle(fenetre.getPoliceSelectionnee(), 12, fenetre.getCouleurChoisie());
            Projet projet = new Projet(
                fenetre.getTexteSaisi(),
                fenetre.getCheminImageChoisie(),
                fenetre.getLargeurMaxImage(),
                fenetre.getAlignementImage(),
                style
            );

            try {
                GestionnaireFichiers.sauvegarderObjet(projet, chemin);
                JOptionPane.showMessageDialog(fenetre, "Projet sauvegardé !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(fenetre, "Erreur de sauvegarde : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void chargerProjet() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Charger un projet");
        if (fileChooser.showOpenDialog(fenetre) == JFileChooser.APPROVE_OPTION) {
            try {
                Projet projet = (Projet) GestionnaireFichiers.chargerObjet(fileChooser.getSelectedFile().getAbsolutePath());

                fenetre.setTexteSaisi(projet.getTexte());
                fenetre.setCheminImageChoisie(projet.getCheminImageComplementaire());
                fenetre.setLargeurMaxImage(projet.getLargeurMaxImage());
                fenetre.setAlignementImage(projet.getAlignementImage());

                if (projet.getProfilStyle() != null) {
                    fenetre.setPoliceSelectionnee(projet.getProfilStyle().getNomPolice());
                    fenetre.setCouleurChoisie(projet.getProfilStyle().getCouleurAwt());
                }

                JOptionPane.showMessageDialog(fenetre, "Projet chargé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(fenetre, "Erreur de chargement : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Fenetre fenetre = new Fenetre();
            new Controleur(fenetre);
            fenetre.setVisible(true);
        });
    }
}