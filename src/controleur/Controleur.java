package controleur;

import modele.*;
import vue.Fenetre;

import javax.swing.*;
import java.io.File;

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
            fenetre.afficherErreur("Le champ 'Texte / Lien' est obligatoire pour générer le QR Code et le PDF.");
            return;
        }

        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                fenetre.setControlesActifs(false);

                fenetre.mettreAJourProgression(20, "Préparation des données...");
                Thread.sleep(200);

                Donnees donnees = new Donnees(saisie);
                ProfilStyle style = new ProfilStyle(fenetre.getPoliceSelectionnee(), 12, fenetre.getCouleurChoisie());
                String cheminQRCode = "qrcode.png";
                String cheminPDF = "document_avec_qr.pdf";

                fenetre.mettreAJourProgression(50, "Génération du QR Code...");
                GenerateurQRCode.creerQRCode(donnees.getTexte(), 200, 200, cheminQRCode);
                Thread.sleep(200);

                fenetre.mettreAJourProgression(80, "Création du document PDF...");
                GenerateurPDF.creerPDF(
                    donnees.getTexte(),
                    cheminQRCode,
                    fenetre.getCheminImageChoisie(),
                    fenetre.getLargeurMaxImage(),
                    fenetre.getAlignementImage(),
                    style,
                    cheminPDF
                );
                Thread.sleep(200);

                fenetre.mettreAJourProgression(100, "Terminé !");
                return null;
            }

            @Override
            protected void done() {
                fenetre.setControlesActifs(true);
                try {
                    get();
                    fenetre.afficherSucces("Le document PDF a été généré avec succès dans 'document_avec_qr.pdf'.");
                } catch (Exception ex) {
                    fenetre.reinitialiserProgression();
                    fenetre.afficherErreur("Erreur lors de la génération du PDF.");
                }
            }
        };

        worker.execute();
    }

    private void sauvegarderProfil() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sauvegarder le profil de style");
        if (fileChooser.showSaveDialog(fenetre) == JFileChooser.APPROVE_OPTION) {
            String chemin = fileChooser.getSelectedFile().getAbsolutePath();
            if (!chemin.endsWith(".style")) chemin += ".style";

            ProfilStyle style = new ProfilStyle(fenetre.getPoliceSelectionnee(), 12, fenetre.getCouleurChoisie());

            try {
                GestionnaireFichiers.sauvegarderObjet(style, chemin);
                fenetre.afficherSucces("Profil de style sauvegardé dans :\n" + chemin);
            } catch (Exception ex) {
                fenetre.afficherErreur("Erreur lors de la sauvegarde du profil de style.");
            }
        }
    }

    private void chargerProfil() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Charger un profil de style");
        if (fileChooser.showOpenDialog(fenetre) == JFileChooser.APPROVE_OPTION) {
            File fichier = fileChooser.getSelectedFile();
            try {
                ProfilStyle style = (ProfilStyle) GestionnaireFichiers.chargerObjet(fichier.getAbsolutePath());

                fenetre.setPoliceSelectionnee(style.getNomPolice());
                fenetre.setCouleurChoisie(style.getCouleurAwt());

                fenetre.afficherSucces("Profil de style chargé avec succès !");
            } catch (Exception ex) {
                fenetre.afficherErreur("Fichier de profil invalide ou corrompu.");
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
                fenetre.afficherSucces("Projet sauvegardé dans :\n" + chemin);
            } catch (Exception ex) {
                fenetre.afficherErreur("Erreur lors de la sauvegarde du projet.");
            }
        }
    }

    private void chargerProjet() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Charger un projet");
        if (fileChooser.showOpenDialog(fenetre) == JFileChooser.APPROVE_OPTION) {
            File fichier = fileChooser.getSelectedFile();
            try {
                Projet projet = (Projet) GestionnaireFichiers.chargerObjet(fichier.getAbsolutePath());

                fenetre.setTexteSaisi(projet.getTexte());
                fenetre.setCheminImageChoisie(projet.getCheminImageComplementaire());
                fenetre.setLargeurMaxImage(projet.getLargeurMaxImage());
                fenetre.setAlignementImage(projet.getAlignementImage());

                if (projet.getProfilStyle() != null) {
                    fenetre.setPoliceSelectionnee(projet.getProfilStyle().getNomPolice());
                    fenetre.setCouleurChoisie(projet.getProfilStyle().getCouleurAwt());
                }

                fenetre.afficherSucces("Projet chargé avec succès !");
            } catch (Exception ex) {
                fenetre.afficherErreur("Fichier de projet invalide ou corrompu.");
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