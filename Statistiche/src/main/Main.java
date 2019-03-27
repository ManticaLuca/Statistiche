/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mantica_luca
 */
public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Inserire il numero di lettere da generere:  ");
        int lettere = scan.nextInt();
        while (lettere < 1) {
            System.err.println("Inserisci un numero > 0");
            lettere = scan.nextInt();
        }
        final SharedData sharedData = new SharedData(lettere);
        final ThGen thGen = new ThGen(sharedData);
        final ThCerca thPunti = new ThCerca(sharedData, '.'), thSpazi = new ThCerca(sharedData, ' ');
        final ThVis thVis = new ThVis(sharedData);
        thVis.start();
        thGen.start();
        thPunti.start();
        thSpazi.start();

       
        try {
            sharedData.getSemJoin().acquire(4);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (sharedData.getNumPuntiInseriti()== sharedData.getNumPuntiLetti() && sharedData.getNumSpaziInseriti() == sharedData.getNumSpaziLetti()) {
            System.out.println("Generazione completata con successo");
        } else {
            System.err.println("C'è stato uno o piu' errori");
        }

    }
}
