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
    private static final char nolla = 'o';
    private char varattu = nolla;
    boolean status = true;

    private boolean voikoVarata() {
        if (varattu == nolla) {
            this.status = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean varaaTila() {
        return voikoVarata();
    }

    public boolean getStatus() {
        return this.status;
    }
    public char getMerkki(){
        return this.varattu;
    }
}
