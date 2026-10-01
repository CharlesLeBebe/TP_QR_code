package modele;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class GenerateurQRCode {

    public static void creerQRCode(String texte, int largeur, int hauteur, String cheminSortie) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(texte, BarcodeFormat.QR_CODE, largeur, hauteur);
            
            Path cheminFichier = FileSystems.getDefault().getPath(cheminSortie);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", cheminFichier);
            
            System.out.println("QR Code généré avec succès !");
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}