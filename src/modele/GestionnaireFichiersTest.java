package modele;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.awt.Color;
import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class GestionnaireFichiersTest {

    @Test
    public void testSauvegarderEtChargerProfilStyle(@TempDir Path tempDir) {
        File fichierTemp = tempDir.resolve("test_profil.style").toFile();
        ProfilStyle styleOriginal = new ProfilStyle("Courier", 16, Color.RED);

        assertDoesNotThrow(() -> {
            GestionnaireFichiers.sauvegarderObjet(styleOriginal, fichierTemp.getAbsolutePath());
        }, "La sauvegarde du profil ne doit pas lever d'exception.");

        assertTrue(fichierTemp.exists(), "Le fichier de sauvegarde doit exister.");

        assertDoesNotThrow(() -> {
            ProfilStyle styleCharge = (ProfilStyle) GestionnaireFichiers.chargerObjet(fichierTemp.getAbsolutePath());
            assertNotNull(styleCharge);
            assertEquals(styleOriginal.getNomPolice(), styleCharge.getNomPolice());
            assertEquals(styleOriginal.getCouleurAwt(), styleCharge.getCouleurAwt());
        }, "Le chargement du profil ne doit pas lever d'exception.");
    }
}