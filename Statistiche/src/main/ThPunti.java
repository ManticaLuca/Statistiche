/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author mantica_luca
 */
public class ThPunti extends Thread {

    private SharedData sharedData;

    public ThPunti(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        for (int i = 0; i < sharedData.getNumEl(); i++) {
            if (sharedData.getBuffer()[i] == '.') {
                sharedData.incNumPuntiLetti();
            }
        }
        sharedData.semLetturaPunti.release();
    }
}
