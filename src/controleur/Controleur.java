package controleur;

import modele.Donnees;
import modele.GenerateurPDF;
import modele.GenerateurQRCode;
import modele.ProfilStyle;
import vue.Fenetre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controleur {
    private Fenetre fenetre;

    public Controleur(Fenetre fenetre) {
        this.fenetre = fenetre;

        this.fenetre.addGenererListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                declencherGenerationGlobale();
            }
        });
    }

    private void declencherGenerationGlobale() {
        String saisie = fenetre.getTexteSaisi();

        if (saisie == null || saisie.trim().isEmpty()) {
            JOptionPane.showMessageDialog(fenetre, "Veuillez saisir du texte ou un lien !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Donnees donnees = new Donnees(saisie);

        ProfilStyle style = new ProfilStyle(
            fenetre.getPoliceSelectionnee(),
            12,
            fenetre.getCouleurChoisie()
        );

        String cheminQRCode = "qrcode.png";
        String cheminPDF = "document_avec_qr.pdf";
        String cheminImageComplementaire = fenetre.getCheminImageChoisie();

        try {
            GenerateurQRCode.creerQRCode(donnees.getTexte(), 200, 200, cheminQRCode);

            GenerateurPDF.creerPDF(
                donnees.getTexte(),
                cheminQRCode,
                cheminImageComplementaire,
                style,
                cheminPDF
            );

            JOptionPane.showMessageDialog(fenetre,
                "Succès ! Le PDF stylisé a été généré.",
                "Opération réussie",
                JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(fenetre,
                "Une erreur est survenue lors de la génération : " + ex.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
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