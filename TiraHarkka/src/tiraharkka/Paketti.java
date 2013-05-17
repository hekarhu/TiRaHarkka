/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiraharkka;

/**
 *
 * @author hekarhu
 */
public class Paketti {
    int leveys = 0;
    int korkeus = 0;
    int syvyys = 0;
    /**
     *Konstruktori jolla luodaan paketti, kun ei ole tiedossa kuin korkeus ja leveys
     * @param leveys
     * @param korkeus
     */
    public Paketti(int leveys, int korkeus){
        this.leveys = leveys;
        this.korkeus = korkeus;
    }
    /**
     *
     *Konstruktori jolla luodaan paketti, kun tiedossa sekä korkeus, että leveys
     * @param leveys
     * @param korkeus
     * @param syvyys
     */
    public Paketti(int leveys, int korkeus, int syvyys){
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.syvyys = syvyys;
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
}
