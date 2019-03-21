/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mantica_luca
 */
public class ThGen extends Thread {

    private SharedData sharedData;

    public ThGen(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        for (int i = 0; i < sharedData.getNumeroLettere(); i++) {
            if (sharedData.getNumEl() < 10) {
                Random rnd = new Random();
                char a;
                int next = rnd.nextInt(26) + 66;
                switch (next) {
                    case 91:
                        a = ' ';
                        sharedData.incNumSpaziInseriti();
                        break;
                    case 92:
                        a = '.';
                        sharedData.incNumPuntiInseriti();
                        break;
                    default:
                        a = (char) next;
                        break;
                }
                sharedData.getBuffer()[sharedData.getNumEl()] = a;
                sharedData.setNumEl(sharedData.getNumEl() + 1);
            }
            else{
                try {
                    sharedData.semLetturaSpazi.acquire();
                    sharedData.semLetturaPunti.acquire();
                    sharedData.clearBuffer();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThGen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
