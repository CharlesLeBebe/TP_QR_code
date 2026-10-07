package modele;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class GenerateurPDF {

    public static void creerPDF(String texteSaisi, String cheminQRCode, String cheminImageComplementaire, ProfilStyle style, String cheminSortiePDF) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(cheminSortiePDF));
            document.open();

            Font titreFont = style.getFontIText(Font.BOLD, style.getTaillePolice() + 6);
            Font texteFont = style.getFontIText(Font.NORMAL, style.getTaillePolice());

            Paragraph titre = new Paragraph("TP Java - PDF Personnalisé avec QR Code", titreFont);
            Paragraph contenu = new Paragraph("Texte d'origine : " + texteSaisi, texteFont);

            document.add(titre);
            document.add(new Paragraph(" "));
            document.add(contenu);
            document.add(new Paragraph(" "));

            if (cheminImageComplementaire != null && !cheminImageComplementaire.trim().isEmpty()) {
                try {
                    Image imageComplementaire = Image.getInstance(cheminImageComplementaire);
                    imageComplementaire.scaleToFit(200, 200);
                    imageComplementaire.setAlignment(Image.ALIGN_CENTER);
                    document.add(imageComplementaire);
                    document.add(new Paragraph(" "));
                } catch (Exception e) {
                    System.err.println("Impossible d'ajouter l'image complémentaire : " + e.getMessage());
                }
            }

            if (cheminQRCode != null && !cheminQRCode.trim().isEmpty()) {
                Image qrImage = Image.getInstance(cheminQRCode);
                qrImage.scaleToFit(120, 120);
                qrImage.setAlignment(Image.ALIGN_CENTER);
                document.add(qrImage);
            }

            document.close();
            System.out.println("PDF personnalisé généré avec succès !");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}