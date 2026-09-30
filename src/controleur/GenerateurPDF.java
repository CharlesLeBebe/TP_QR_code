package controleur;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;

 class GenerateurPDF {
    public static void creerPDF(String texteSaisi, String cheminSortie) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(cheminSortie));
            document.open();
            document.add(new Paragraph("TP Java - PDF Généré"));
            document.add(new Paragraph("Texte : " + texteSaisi));
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}