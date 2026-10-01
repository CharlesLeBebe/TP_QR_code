package modele;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class GenerateurPDF {

    public static void creerPDF(String texteSaisi, String cheminQRCode, String cheminSortiePDF) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(cheminSortiePDF));
            document.open();
            
            document.add(new Paragraph("TP Java - PDF avec QR Code"));
            document.add(new Paragraph("Texte d'origine : " + texteSaisi));
            document.add(new Paragraph(" ")); 
            
            Image qrImage = Image.getInstance(cheminQRCode);
            qrImage.scaleToFit(120, 120); 
            qrImage.setAlignment(Image.ALIGN_CENTER); 
            document.add(qrImage);
            
            document.close();
            System.out.println("PDF avec QR code généré avec succès !");
            
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}