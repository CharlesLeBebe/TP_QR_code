package modele;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class GenerateurQRCodeTest {

    @Test
    void testCreationQRCode() throws Exception {

        Path fichierTest = Files.createTempFile("test-qrcode-", ".png");

        GenerateurQRCode.creerQRCode(
                "CharlesLeBebe",
                200,
                200,
                fichierTest.toString()
        );

        assertTrue(Files.exists(fichierTest));
        assertTrue(Files.size(fichierTest) > 0);

        Files.deleteIfExists(fichierTest);
    }
}