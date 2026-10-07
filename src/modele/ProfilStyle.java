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
        FontFactory.COURIER,
        FontFactory.TIMES_ROMAN,
        FontFactory.SYMBOL,
        FontFactory.ZAPFDINGBATS
    };

    private String nomPolice;
    private int taillePolice;
    private int r; 
    private int g; 
    private int b; 

    public ProfilStyle() {
     
        this.nomPolice = FontFactory.HELVETICA;
        this.taillePolice = 12;
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    public ProfilStyle(String nomPolice, int taillePolice, Color couleur) {
        this.nomPolice = nomPolice;
        this.taillePolice = taillePolice;
        if (couleur != null) {
            this.r = couleur.getRed();
            this.g = couleur.getGreen();
            this.b = couleur.getBlue();
        } else {
            this.r = 0;
            this.g = 0;
            this.b = 0;
        }
    }

    public String getNomPolice() {
        return nomPolice;
    }

    public void setNomPolice(String nomPolice) {
        this.nomPolice = nomPolice;
    }

    public int getTaillePolice() {
        return taillePolice;
    }

    public void setTaillePolice(int taillePolice) {
        this.taillePolice = taillePolice;
    }

    public Color getCouleurAwt() {
        return new Color(r, g, b);
    }

    public void setCouleurAwt(Color couleur) {
        if (couleur != null) {
            this.r = couleur.getRed();
            this.g = couleur.getGreen();
            this.b = couleur.getBlue();
        }
    }

    public BaseColor getBaseColorIText() {
        return new BaseColor(r, g, b);
    }

    public Font getFontIText(int style, float taille) {
        return FontFactory.getFont(nomPolice, taille, style, getBaseColorIText());
    }
}