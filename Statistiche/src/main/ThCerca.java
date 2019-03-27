/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca Mantica
 */
public class ThCerca extends Thread {

    private final SharedData sharedData;
    private final char daCercare;

    public ThCerca(SharedData sharedData, char daCercare) {
        this.sharedData = sharedData;
        this.daCercare = daCercare;
    }

    @Override
    public void run() {
        try {
            do {
                if (daCercare == '.') {
                    sharedData.getSemScritturaPunti().acquire();
                } else {
                    sharedData.getSemScritturaSpazi().acquire();
                }
                for (int i = 0; i < sharedData.getNumeroLettereDaLeggere(); i++) {
                    char valore = sharedData.getBufferAt(i);

                    if (valore == daCercare) {
                        sharedData.getSemVisualizzato().acquire();
                        if (valore == '.') {
                            sharedData.incNumPuntiLetti();
                        } else if (valore == ' ') {
                            sharedData.incNumSpaziLetti();
                        }
                        sharedData.getSemVisualizza().release();
                    }
                }
                if (daCercare == '.') {
                    sharedData.getSemLetturaPunti().release();
                } else {
                    sharedData.getSemLetturaSpazi().release();
                }
            } while (!sharedData.isEstrazioneTerminata());
            if (daCercare == '.') {
                sharedData.setRicercaTerminata(true, true);
            } else {
                sharedData.setRicercaTerminata(true, false);
            }
            sharedData.getSemJoin().release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThCerca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
