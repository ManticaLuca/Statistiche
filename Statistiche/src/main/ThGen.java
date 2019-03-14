/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Random;

/**
 *
 * @author mantica_luca
 */
public class ThGen extends Thread {

    private SharedData sharedData;
    private int num;

    public ThGen(SharedData sharedData, int num) {
        this.sharedData = sharedData;
        this.num = num;
    }
    
    @Override
    public void run(){
        if (sharedData.getNumeroLettere() < num) {
        Random rnd = new Random();
        char a ;
        int next = rnd.nextInt(26) + 66 ;
            switch (next) {
                case 91:
                    a = ' ';
                    break;
                case 92:
                    a = '.';
                    break;
                default:
                    a = (char)next;
                    break;
            }
            sharedData.getBuffer()[sharedData.getNumEl()] = a;
        }
        if (sharedData.getNumEl() == 10) {
            
        }
    }
}
