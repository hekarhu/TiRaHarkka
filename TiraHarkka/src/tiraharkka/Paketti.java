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
    private char[] aakkosto = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','Z'};
    private int leveys = 0;
    private int korkeus = 0;
    private int syvyys = 0;
    private char merkki;
    private int tilavuus;
    /**
     *Konstruktori jolla luodaan paketti, kun ei ole tiedossa kuin korkeus ja leveys
     * @param leveys
     * @param korkeus
     */
    public Paketti(int leveys, int korkeus,int indeksi){
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.merkki = aakkosto[indeksi];
        this.tilavuus = (this.korkeus*this.leveys*this.syvyys);
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
        this.tilavuus = (this.korkeus*this.leveys*this.syvyys);
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
    public int getTilavuus(){
        return this.tilavuus;
    }

    void setKorkeus(int uusiKorkeus) {
        this.korkeus=uusiKorkeus;
    }
    void setLeveys(int uusiLeveys){
        this.leveys = uusiLeveys;
    }
    void setSyvyys(int uusiSyvyys){
        this.syvyys = uusiSyvyys;
    }

}
