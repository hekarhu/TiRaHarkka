    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiraharkka;

import java.util.Scanner;

/**
 *
 * @author hekarhu
 */
public class PakettiKontti {

    private Scanner lukija = new Scanner(System.in);
    int leveys = 0;
    int korkeus = 0;
    int syvyys = 0;

    /**
     *
     * Konstruktori jolla luodaan kontti, kun tiedossa sekä korkeus, leveys,että
     * syvyys
     *
     * @param leveys
     * @param korkeus
     * @param syvyys
     */
    public PakettiKontti() {
        this.leveys = kysyMitta("leveys");
        this.korkeus = kysyMitta("korkeus");
        this.syvyys = kysyMitta("syvyys");
    }

    /**
     *
     * @return palauttaa tiedon paketin leveydestä
     */
    public int getLeveys() {
        return this.leveys;
    }

    /**
     *
     * @return palauttaa tiedon paketin korkeudesta
     */
    public int getKorkeus() {
        return this.korkeus;
    }

    /**
     *
     * @return palauttaa tiedon paketin syvyydestä
     */
    public int getSyvyys() {
        return this.syvyys;
    }

    private int kysyMitta(String mikaMitta) {
        System.out.println("Anna tieto kontin " + mikaMitta + ": ");
        return lukija.nextInt();
    }
}
