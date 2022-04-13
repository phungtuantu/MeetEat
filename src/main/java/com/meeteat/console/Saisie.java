/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author gvnge
 */
public class Saisie {
    public static String lireChaine(String invite) {
        String chaineLue = null;
        System.out.println(invite);
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            chaineLue = br.readLine();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
        return chaineLue;

    }

    public static Integer lireInteger(String invite) {
        Integer valeurLue = null;
        while (valeurLue == null) {
            try {
                valeurLue = Integer.parseInt(lireChaine(invite));
            } catch (NumberFormatException ex) {
                System.out.println("/!\\ Erreur de saisie - Nombre entier attendu /!\\");
            }
        }
        return valeurLue;
    }

    public static Integer lireInteger(String invite, List<Integer> valeursPossibles) {
        Integer valeurLue = null;
        while (valeurLue == null) {
            try {
                valeurLue = Integer.parseInt(lireChaine(invite));
            } catch (NumberFormatException ex) {
                System.out.println("/!\\ Erreur de saisie - Nombre entier attendu /!\\");
            }
            if (!(valeursPossibles.contains(valeurLue))) {
                System.out.println("/!\\ Erreur de saisie - Valeur non-autorisée /!\\");
                valeurLue = null;
            }
        }
        return valeurLue;
    }
    
    public static void pause() {
        lireChaine("--PAUSE--");
    }

}
