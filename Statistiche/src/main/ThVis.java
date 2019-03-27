/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca Mantica
 */
public class ThVis extends Thread {

    private final SharedData sharedData;

    public ThVis(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        while (!sharedData.isRicercaTerminata()) {
            try {
                sharedData.getSemVisualizzato().release();
                sharedData.getSemVisualizza().acquire();
                sharedData.visualizza();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThVis.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sharedData.getSemJoin().release();
    }
}
