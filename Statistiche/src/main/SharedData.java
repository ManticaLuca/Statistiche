/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.Semaphore;

/**
 *
 * @author mantica_luca
 */
public class SharedData {

    public Semaphore semLetturaPunti;
    public Semaphore semLetturaSpazi;
    public Semaphore semVis;
    public Semaphore semVis;

    private int numeroLettere;
    private char[] buffer;
    private int numEl;
    private int numSpaziInseriti;
    private int numPuntiInseriti;
    private int numSpaziLetti;
    private int numPuntiLetti;

    public SharedData(int num) {
        buffer = new char[10];
        numEl = 0;
        numeroLettere = num;
        numSpaziInseriti = 0;
        numPuntiInseriti = 0;
        numSpaziLetti = 0;
        numPuntiLetti = 0;
        //imp sem
        sem1 = new Semaphore(2);
        semVis = new Semaphore(0);
    }

    public void incNumSpaziInseriti() {
        numSpaziInseriti++;
    }

    public void incNumPuntiInseriti() {
        numPuntiInseriti++;
    }
    
    public void incNumSpaziLetti() {
        numSpaziLetti++;
    }

    public void incNumPuntiLetti() {
        numPuntiLetti++;
    }

    public int getNumeroLettere() {
        return numeroLettere;
    }

    public char[] getBuffer() {
        return buffer;
    }

    public int getNumEl() {
        return numEl;
    }

    public void setNumEl(int num) {
        numEl = num;
    }

    public synchronized void visualizza() {
        System.out.println(Arrays.toString(buffer) + numSpaziInseriti + numPuntiInseriti + numSpaziLetti + numPuntiLetti);
    }

    void clearBuffer() {
        numEl = 0;
    }
}
