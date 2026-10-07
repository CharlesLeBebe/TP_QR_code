package modele;

import org.junit.jupiter.api.Test;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class ProjetTest {

    @Test
    public void testCreationProjetComplet() {
        ProfilStyle style = new ProfilStyle("Times New Roman", 12, Color.BLACK);
        Projet projet = new Projet("Mon Titre", "logo.png", 150, "CENTRE", style);

        assertEquals("Mon Titre", projet.getTexte());
        assertEquals("logo.png", projet.getCheminImageComplementaire());
        assertEquals(150, projet.getLargeurMaxImage());
        assertEquals("CENTRE", projet.getAlignementImage());
        assertNotNull(projet.getProfilStyle());
        assertEquals("Times New Roman", projet.getProfilStyle().getNomPolice());
    }
}