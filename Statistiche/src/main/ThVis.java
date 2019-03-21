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
public class ThVis extends Thread {

    private SharedData sharedData;

    public ThVis(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        //
        sharedData.visualizza();
        //
    }
}
