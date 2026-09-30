package controleur;

import modele.Donnees;
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
                String saisie = fenetre.getTexteSaisi();
                if (saisie == null || saisie.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(fenetre, "Veuillez saisir du texte !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Donnees donnees = new Donnees(saisie);
                GenerateurPDF.creerPDF(donnees.getTexte(), "document.pdf");
                JOptionPane.showMessageDialog(fenetre, "PDF généré avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Fenetre fenetre = new Fenetre();
            new Controleur(fenetre);
            fenetre.setVisible(true);
        });
    }
}