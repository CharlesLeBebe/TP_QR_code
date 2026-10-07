package modele;

import java.io.*;

public class GestionnaireFichiers {

    public static void sauvegarderObjet(Object objet, String cheminFichier) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cheminFichier))) {
            oos.writeObject(objet);
        }
    }

    public static Object chargerObjet(String cheminFichier) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cheminFichier))) {
            return ois.readObject();
        }
    }
}