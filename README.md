# Générateur de QR Code et PDF

## Introduction

Ce projet consiste à développer une application Java permettant de générer un QR Code à partir d'un texte ou d'un lien saisi par l'utilisateur. Le QR Code généré est ensuite intégré automatiquement dans un fichier PDF.

L'application possède une interface graphique réalisée avec Java Swing et utilise l'architecture MVC afin de séparer les différentes parties du programme.

Le projet utilise également les bibliothèques ZXing pour la génération des QR Codes, iText pour la création des fichiers PDF et JUnit pour réaliser les tests unitaires.

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
    │   ├── GenerateurPDFTest.java
    │   ├── GenerateurQRCode.java
    │   └──  GenerateurQRCodeTest.java    
    ├── vue/
        └── Fenetre.java        
