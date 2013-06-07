/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiraharkka;

import java.io.Serializable;

/**
 *
 * @author hekarhu
 */
public class Paketti implements Serializable{
    char[] aakkosto = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','Z'};
    int leveys = 0;
    int korkeus = 0;
    int syvyys = 0;
    char merkki;
    /**
     *Konstruktori jolla luodaan paketti, kun ei ole tiedossa kuin korkeus ja leveys
     * @param leveys
     * @param korkeus
     */
    public Paketti(int leveys, int korkeus,int indeksi){
 
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.merkki = aakkosto[indeksi];
    }
    /**
     *
     *Konstruktori jolla luodaan paketti, kun tiedossa sekä korkeus, leveys, että syvyys
     * @param leveys
     * @param korkeus
     * @param syvyys
     */
    public Paketti(int leveys, int korkeus, int syvyys,int indeksi){
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.syvyys = syvyys;
        this.merkki = aakkosto[indeksi];
    }
    /**
     *
     * @return palauttaa tiedon paketin leveydestä
     */
    public int getLeveys(){
        return this.leveys;
    }
    /**
     *
     * @return palauttaa tiedon paketin korkeudesta
     */
    public int getKorkeus(){
        return this.korkeus;
    }
    /**
     *
     * @return palauttaa tiedon paketin syvyydestä
     */
    public int getSyvyys(){
        return this.syvyys;
    }
    public char getAakkonen(){
        return this.merkki;
    }
}
