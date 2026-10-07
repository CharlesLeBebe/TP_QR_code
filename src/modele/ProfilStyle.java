package modele;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import java.awt.Color;
import java.io.Serializable;

public class ProfilStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String[] POLICES_DISPONIBLES = {
        FontFactory.HELVETICA,
        FontFactory.TIMES_ROMAN,
        FontFactory.COURIER
    };

    private String nomPolice;
    private int taillePolice;
    private Color couleurAwt;

    public ProfilStyle(String nomPolice, int taillePolice, Color couleurAwt) {
        this.nomPolice = nomPolice;
        this.taillePolice = taillePolice;
        this.couleurAwt = (couleurAwt != null) ? couleurAwt : Color.BLACK;
    }

    public Font getFontIText(int style, float taille) {
        BaseColor baseColor = new BaseColor(
            couleurAwt.getRed(),
            couleurAwt.getGreen(),
            couleurAwt.getBlue()
        );
        return FontFactory.getFont(nomPolice, taille, style, baseColor);
    }

    public String getNomPolice() { return nomPolice; }
    public void setNomPolice(String nomPolice) { this.nomPolice = nomPolice; }

    public int getTaillePolice() { return taillePolice; }
    public void setTaillePolice(int taillePolice) { this.taillePolice = taillePolice; }

    public Color getCouleurAwt() { return couleurAwt; }
    public void setCouleurAwt(Color couleurAwt) { this.couleurAwt = couleurAwt; }
}