package modele;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DonneesTest {

    @Test
    public void testCreationDonneesValide() {
        String texte = "https://example.com";
        Donnees donnees = new Donnees(texte);
        assertEquals(texte, donnees.getTexte(), "Le texte stocké doit correspondre au texte saisi.");
    }

    @Test
    public void testDonneesChaineVide() {
        Donnees donnees = new Donnees("");
        assertEquals("", donnees.getTexte(), "Le texte vide doit être accepté par le modèle.");
    }
}