package modele;

import org.junit.jupiter.api.Test;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class ProfilStyleTest {

    @Test
    public void testConstructeurEtGetters() {
        String police = "Arial";
        int taille = 14;
        Color couleur = Color.BLUE;

        ProfilStyle style = new ProfilStyle(police, taille, couleur);

        assertEquals("Arial", style.getNomPolice());
        assertEquals(14, style.getTaillePolice());
        assertEquals(Color.BLUE, style.getCouleurAwt());
    }

    @Test
    public void testCouleurParDefautSiNull() {
        ProfilStyle style = new ProfilStyle("Helvetica", 12, null);
        assertNotNull(style.getCouleurAwt(), "La couleur ne doit pas être null.");
    }
}