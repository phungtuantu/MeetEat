/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.service;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DASI Team
 */
public class Message {
    
    private final static PrintStream OUT = System.out;
    private final static SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd~HH:mm:ss");
    private final static SimpleDateFormat HORODATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy à HH:mm:ss");
    
    private static void debut() {
        Date maintenant = new Date();
        OUT.println();
        OUT.println();
        OUT.println("---<([ MESSAGE @ " + TIMESTAMP_FORMAT.format(maintenant) + " ])>---");
        OUT.println();
    }
    
    private static void fin() {
        OUT.println();
        OUT.println("---<([ FIN DU MESSAGE ])>---");
        OUT.println();
        OUT.println();
    }
    
    public static String typeDate(Date date){
        return HORODATE_FORMAT.format(date);
    }
    
    public static void envoyerMail(String mailExpediteur, String mailDestinataire, String objet, String corps) {
        
        Date maintenant = new Date();
        Message.debut();
        OUT.println("~~~ E-mail envoyé le " + HORODATE_FORMAT.format(maintenant) + " ~~~");
        OUT.println("De : " + mailExpediteur);
        OUT.println("À  : " + mailDestinataire);
        OUT.println("Obj: " + objet);
        OUT.println();
        OUT.println(corps);
        Message.fin();
    }

    public static void envoyerNotification(String prenom, String nom, String telephoneDestinataire, String message) {
        
        Date maintenant = new Date();
        Message.debut();
        OUT.println("~~~ Notification envoyée le " + HORODATE_FORMAT.format(maintenant) + " ~~~");
        OUT.print("Pour  : " + prenom +" "+nom+", ");
        OUT.println("Tel : " + telephoneDestinataire);
        OUT.println(message);
        Message.fin();
    }
    
//    public static void main(String[] args) {
//        
//        //DebugLogger.log("Début des Tests...");
//        
//        StringWriter corps = new StringWriter();
//        PrintWriter mailWriter = new PrintWriter(corps);
//        
//        mailWriter.println("Bonjour,");
//        mailWriter.println();
//        mailWriter.println("  Ceci est un mail destiné à tester l'envoi simulé par affichage sur la console.");
//        mailWriter.println();
//        mailWriter.println("  Cordialement,");
//        mailWriter.println();
//        mailWriter.println("    Yann Gripay");
//
//        Message.envoyerMail(
//                "yann.gripay@insa-lyon.fr",
//                "etudiants.3IF@insa-lyon.fr",
//                "[DASI] Test d'envoi de e-mail",
//                corps.toString()
//            );
//        
//        
//        StringWriter message = new StringWriter();
//        PrintWriter notificationWriter = new PrintWriter(message);
//        
//        notificationWriter.println("Ceci est une notification pour prévenir de 2 choses:");
//        notificationWriter.println("1) NE PAS oublier le poly");
//        notificationWriter.println("2) TESTER au fur et à mesure du développement");
//
//        Message.envoyerNotification(
//                "0988776655",
//                message.toString()
//            );
//        
//        //DebugLogger.log("Fin des Tests...");
//        
//    }
}