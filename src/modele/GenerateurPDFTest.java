package modele;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class GenerateurPDFTest {

    @Test
    void testCreationPDF() throws Exception {


        Path qrCode = Files.createTempFile("test-qrcode-", ".png");

        GenerateurQRCode.creerQRCode(
                "Test PDF",
                200,
                200,
                qrCode.toString()
        );

        Path pdf = Files.createTempFile("test-pdf-", ".pdf");

        GenerateurPDF.creerPDF(
                "Test PDF",
                qrCode.toString(),
                pdf.toString()
        );

        assertTrue(Files.exists(pdf));
        assertTrue(Files.size(pdf) > 0);

        Files.deleteIfExists(qrCode);
        Files.deleteIfExists(pdf);
    }
}