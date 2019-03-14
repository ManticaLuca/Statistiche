/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Vector;
import java.util.concurrent.Semaphore;

/**
 *
 * @author mantica_luca
 */
public class SharedData {

    public Semaphore sem1;
    public Semaphore semVis;

    private int numEl;
    private int numeroLettere;
    private char[] buffer;

    private int numSpaziInseriti;
    private int numPuntiInseriti;
    private int numSpaziLetti;
    private int numPuntiLetti;

    public SharedData() {
        buffer = new char[10];
        numEl = 0;
        sem1 = new Semaphore(2);
        semVis = new Semaphore(0);
        numSpaziInseriti = 0;
        numPuntiInseriti = 0;
        numSpaziLetti = 0;
        numPuntiLetti = 0;
    }

    public void setNumSpaziInseriti() {
        numSpaziInseriti++;
    }

    public void setNumPuntiInseriti() {
        numPuntiInseriti++;
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
}
