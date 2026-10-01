package modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DonneesTest {

    @Test
    void testCreationDonnees() {
        Donnees donnees = new Donnees("Bonjour");

        assertEquals("Bonjour", donnees.getTexte());
    }

    @Test
    void testModificationDonnees() {
        Donnees donnees = new Donnees("Bonjour");

        donnees.setTexte("Nouveau texte");

        assertEquals("Nouveau texte", donnees.getTexte());
    }

    @Test
    void testTexteVide() {
        Donnees donnees = new Donnees("");

        assertEquals("", donnees.getTexte());
    }
}