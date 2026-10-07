# Générateur de QR Code et PDF

## Introduction

Ce projet consiste à développer une application Java permettant de générer un QR Code à partir d'un texte ou d'un lien saisi par l'utilisateur. Le QR Code généré est ensuite intégré automatiquement dans un fichier PDF.

L'application possède une interface graphique réalisée avec Java Swing et utilise l'architecture MVC afin de séparer les différentes parties du programme.

Le projet utilise également les bibliothèques ZXing pour la génération des QR Codes, iText pour la création des fichiers ET pour les polices d'écriture PDF et JUnit pour réaliser les tests unitaires.

## Arborescence du projet

```text
tp_qr_code/
├── pom.xml
├── README.md
└── src/
    ├── controleur/
    │   └── Controleur.java
    ├── modele/
    │   ├── Donnees.java
    │   ├── DonneesTest.java
    │   ├── GenerateurPDF.java
    │   ├── GenerateurQRCode.java
    │   ├── GestionnaireFichiers.java
    │   ├── GestionnaireFichiersTest.java
    │   ├── ProfilStyle.java
    │   ├── ProfilStyleTest.java
    │   ├── Projet.java
    │   └── ProjetTest.java
    └── vue/
        └── Fenetre.java      
```

# 1. Architecture Globale — Le motif MVC

L'application est découpée selon l'architecture **Modèle-Vue-Contrôleur (MVC)** afin de séparer la logique de données, l'affichage graphique et les événements.

## Le Modèle (`modele`)

Contient les données métier :

- `Donnees`
- `Projet`
- `ProfilStyle`

Contient également :

- La logique de génération :
  - `GenerateurQRCode`
  - `GenerateurPDF`
- La logique de sauvegarde :
  - `GestionnaireFichiers`

Le modèle ne connaît pas l'interface graphique.

## La Vue (`vue/Fenetre.java`)

Construit la fenêtre Java Swing :

- Champs de texte
- Boutons
- Listes déroulantes
- Sélecteur de couleur
- Barre de progression

Elle récupère la saisie de l'utilisateur et affiche les messages d'erreur ou de succès via des fenêtres pop-up (`JOptionPane`).

## Le Contrôleur (`controleur/Controleur.java`)

Fait le lien entre la Vue et le Modèle.

Il est responsable de :

- Répondre aux clics sur les boutons.
- Lancer la génération en arrière-plan via un `SwingWorker`.
- Mettre à jour la barre de progression.

# 2. Fonctionnement de la Génération — Étape par Étape

Quand l'utilisateur clique sur le bouton **« Générer »** :

## 1. Validation

Le contrôleur vérifie que le champ texte n'est pas vide.

- Si le champ est vide → affichage d'un pop-up d'erreur.
- Sinon → la génération peut commencer.

## 2. Démarrage asynchrone

Le contrôleur lance un `SwingWorker`.

L'IHM gèle les boutons afin d'empêcher le double-clic et le lancement de plusieurs générations simultanément.

## 3. Génération du QR Code — ZXing

- Le texte est encodé sous forme d'une matrice d'octets.
- Une image PNG temporaire (`qrcode.png`) est enregistrée sur le disque.
- La barre de progression passe à **50 %**.

## 4. Génération du PDF — iText

Un document PDF (`document_avec_qr.pdf`) est créé.

Le document contient :

- Le texte personnalisé.
- La police sélectionnée.
- La couleur sélectionnée.
- L'image du QR Code.
- L'image d'illustration, si une image a été choisie.

L'image est insérée avec :

- Son alignement : **Gauche, Centre ou Droite**.
- Une largeur maximale.
- Le respect de ses proportions.

La barre de progression passe à **80 %**.

## 5. Finalisation

- La barre de progression passe à **100 %**.
- Les boutons sont réactivés.
- Un message de confirmation apparaît.

En cas de problème, par exemple si le fichier PDF est déjà ouvert dans Acrobat, une exception est attrapée et une pop-up d'erreur claire est affichée.

# 3. Liste Détaillée des Fonctionnalités

## A. Génération de Contenu

### Encodage QR Code

Prise en charge de :

- Texte court
- Texte long
- URL (`https://...`)

### Création de PDF

Assemblage automatique :

- Du texte
- Du QR Code
- De l'image optionnelle

### Redimensionnement d'Image

L'image ajoutée conserve ses proportions en limitant uniquement sa largeur maximale.

## B. Personnalisation Visuelle

### Choix de la Police

Choix dynamique parmi toutes les polices installées sur le système de l'utilisateur grâce à `GraphicsEnvironment`.

### Sélecteur de Couleur

Utilisation d'un `JColorChooser` pour choisir la couleur exacte du texte dans le PDF.

### Alignement de l'Image

Choix entre :

- **Gauche**
- **Centre**
- **Droite**

## C. Sauvegarde & Chargement — Sérialisation

### Profils de Style (`.style`)

Sauvegarde uniquement la charte graphique :

- Police
- Taille
- Couleur

Cela permet de réutiliser le même style sur d'autres projets.

### Projets Complets (`.ser`)

Sauvegarde l'intégralité du travail en cours :

- Texte saisi
- Chemin de l'image
- Alignement
- Dimensions
- Style

### Fichiers Binaires

Utilisation de :

- `ObjectOutputStream`
- `ObjectInputStream`

pour sauvegarder des objets Java directement sur le disque.

## D. Ergonomie & Robustesse — UX / Error Handling

### Barre de Progression

Indique en direct l'avancement :

**20 % → 50 % → 80 % → 100 %**

### Exécution Asynchrone

Grâce au `SwingWorker`, l'interface reste réactive et fluide pendant la création des fichiers.

### Gestion des Erreurs

Toutes les exceptions, notamment :

- `IOException`
- `FileNotFoundException`
- etc.

sont interceptées et expliquées proprement à l'utilisateur par des messages pop-up.
