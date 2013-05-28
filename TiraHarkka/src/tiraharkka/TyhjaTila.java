/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiraharkka;

/**
 *
 * @author hekarhu
 */
public class TyhjaTila {

    int leveys = 5;
    int korkeus = 5;
    private static final char risti = 'x';
    private static final char nolla = 'o';
    private char varattu = nolla;

    private boolean voikoVarata() {
        if (varattu == nolla) {
            this.varattu = risti;
            return true;
        } else {
            return false;
        }
    }

    public boolean varaaTila() {
        return voikoVarata();
    }

    public char getStatus() {
        return this.varattu;
    }
}
