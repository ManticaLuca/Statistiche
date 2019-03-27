/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mantica_luca
 */
public class SharedData {

    public Semaphore semLetturaPunti, semLetturaSpazi, semScritturaPunti, semScritturaSpazi, semVisualizza, semVisualizzato, semJoin;
    private char[] buffer;
    private int numeroLettereDaGenerare, numeroLettereDaLeggere, lunghezzaBuffer;
    private int numSpaziInseriti, numPuntiInseriti, numSpaziLetti, numPuntiLetti;

    private final boolean[] finito;

    public SharedData(int num) {
        semLetturaPunti = new Semaphore(1);
        semLetturaSpazi = new Semaphore(1);
        semScritturaPunti = new Semaphore(0);
        semScritturaSpazi = new Semaphore(0);
        semVisualizza = new Semaphore(0);
        semVisualizzato = new Semaphore(0);
        semJoin = new Semaphore(0);

        buffer = new char[10];
        numeroLettereDaGenerare = num;
        numeroLettereDaLeggere = 0;
        lunghezzaBuffer = 0;
        numSpaziInseriti = 0;
        numPuntiInseriti = 0;
        numPuntiLetti = 0;
        numSpaziLetti = 0;
        finito = new boolean[]{false, false, false};
    }

    public int getNumSpaziInseriti() {
        return numSpaziInseriti;
    }

    public int getNumPuntiInseriti() {
        return numPuntiInseriti;
    }

    public int getNumSpaziLetti() {
        return numSpaziLetti;
    }

    public int getNumPuntiLetti() {
        return numPuntiLetti;
    }

    synchronized public void incNumSpaziInseriti() {
        numSpaziInseriti++;
    }

    synchronized public void incNumPuntiInseriti() {
        numPuntiInseriti++;
    }

    synchronized public void incNumSpaziLetti() {
        numSpaziLetti++;
    }

    synchronized public void incNumPuntiLetti() {
        numPuntiLetti++;
    }

    synchronized public int getNumeroLettereDaGenerare() {
        return numeroLettereDaGenerare;
    }

    synchronized public void setNumeroLettereDaGenerare(int numeroLettereDaGenerare) {
        this.numeroLettereDaGenerare = numeroLettereDaGenerare;
    }

    synchronized public int getNumeroLettereDaLeggere() {
        return numeroLettereDaLeggere;
    }

    synchronized public void setNumeroLettereDaLeggere(int numeroLettereDaLeggere) {
        this.numeroLettereDaLeggere = numeroLettereDaLeggere;
    }

    synchronized public int getLunghezzaBuffer() {
        return lunghezzaBuffer;
    }

    synchronized public void setLunghezzaBuffer(int lunghezzaBuffer) {
        this.lunghezzaBuffer = lunghezzaBuffer;
    }

    synchronized public char[] getBuffer() {
        return buffer;
    }

    synchronized public void setBufferAt(int index, char val) {
        buffer[index] = val;
    }

    synchronized public char getBufferAt(int index) {
        return buffer[index];
    }

    synchronized public void clearBuffer() {
        buffer = new char[lunghezzaBuffer];
    }

    synchronized public void visualizza() {
        System.out.println("Punti inseriti: " + numPuntiInseriti);
        System.out.println("Punti letti: " + numPuntiLetti);
        System.out.println("Spazi inseriti: " + numSpaziInseriti);
        System.out.println("Spazi letti: " + numSpaziLetti);
        System.out.println("-------------");
    }

    synchronized public boolean isEstrazioneTerminata() {
        return finito[0];
    }

    synchronized public void setEstrazioneTerminata(boolean estrazioneTerminata) {
        this.finito[0] = estrazioneTerminata;
    }

    synchronized public boolean isRicercaTerminata() {
        return finito[1] && finito[2];
    }

    synchronized public void setRicercaTerminata(boolean val, boolean index) {
        int pos = index ? 1 : 2;
        finito[pos] = val;
    }

    synchronized public Semaphore getSemLetturaPunti() {
        return semLetturaPunti;
    }

    synchronized public Semaphore getSemLetturaSpazi() {
        return semLetturaSpazi;
    }

    synchronized public Semaphore getSemScritturaPunti() {
        return semScritturaPunti;
    }

    synchronized public Semaphore getSemScritturaSpazi() {
        return semScritturaSpazi;
    }

    synchronized public Semaphore getSemVisualizza() {
        return semVisualizza;
    }

    synchronized public Semaphore getSemVisualizzato() {
        return semVisualizzato;
    }

    synchronized public Semaphore getSemJoin() {
        return semJoin;
    }
}
