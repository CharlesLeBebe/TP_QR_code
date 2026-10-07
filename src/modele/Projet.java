package modele;

import java.io.Serializable;

public class Projet implements Serializable {
    private static final long serialVersionUID = 1L;

    private String texte;
    private String cheminImageComplementaire;
    private int largeurMaxImage;
    private String alignementImage;
    private ProfilStyle profilStyle;

    public Projet(String texte, String cheminImageComplementaire, int largeurMaxImage, String alignementImage, ProfilStyle profilStyle) {
        this.texte = texte;
        this.cheminImageComplementaire = cheminImageComplementaire;
        this.largeurMaxImage = largeurMaxImage;
        this.alignementImage = alignementImage;
        this.profilStyle = profilStyle;
    }

    public String getTexte() { return texte; }
    public void setTexte(String texte) { this.texte = texte; }

    public String getCheminImageComplementaire() { return cheminImageComplementaire; }
    public void setCheminImageComplementaire(String cheminImageComplementaire) { this.cheminImageComplementaire = cheminImageComplementaire; }

    public int getLargeurMaxImage() { return largeurMaxImage; }
    public void setLargeurMaxImage(int largeurMaxImage) { this.largeurMaxImage = largeurMaxImage; }

    public String getAlignementImage() { return alignementImage; }
    public void setAlignementImage(String alignementImage) { this.alignementImage = alignementImage; }

    public ProfilStyle getProfilStyle() { return profilStyle; }
    public void setProfilStyle(ProfilStyle profilStyle) { this.profilStyle = profilStyle; }
}