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
        final int BUFFER_SIZE = sharedData.getLunghezzaBuffer();
        int daGenerare = sharedData.getNumeroLettereDaGenerare();
        while (daGenerare > 0) {
            try {
                int daEstrarre = Math.min(daGenerare, BUFFER_SIZE);
                sharedData.getSemLetturaPunti().acquire();
                sharedData.getSemLetturaSpazi().acquire();
                sharedData.clearBuffer();
                for (int i = 0; i < daEstrarre; i++) {
                    Random rnd = new Random();
                    char a;
                    int next = rnd.nextInt(25) + 65;
                    switch (next) {
                        case 91:
                            a = ' ';
                            sharedData.getSemVisualizzato().acquire();
                            sharedData.incNumSpaziInseriti();
                            sharedData.getSemVisualizza().release();
                            break;
                        case 92:
                            a = '.';
                            sharedData.getSemVisualizzato().acquire();
                            sharedData.incNumPuntiInseriti();
                            sharedData.getSemVisualizza().release();
                            break;
                        default:
                            a = (char) next;
                            break;
                    }
                    sharedData.getSemVisualizzato().acquire();
                    sharedData.setBufferAt(i, a);
                    sharedData.getSemVisualizza().release();
                }
                sharedData.setNumeroLettereDaLeggere(daEstrarre);
                sharedData.getSemScritturaPunti().release();
                sharedData.getSemScritturaSpazi().release();
                daGenerare -= BUFFER_SIZE;
            } catch (InterruptedException ex) {
                Logger.getLogger(ThGen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sharedData.setEstrazioneTerminata(true);
        sharedData.getSemJoin().release();
    }
}
