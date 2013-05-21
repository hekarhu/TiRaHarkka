/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiraharkka;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author hekarhu
 */
public class TiraHarkka {

    /**
     * @param args the command line arguments
     * @throws ClassNotFoundException
     * @throws FileNotFoundException 
     * @throws IOException  
     */
    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException {
        TiedotTiedostosta tiedot = new TiedotTiedostosta();
//        tiedot.lisaaPaketti();
        tiedot.tulostaListaPaketeista();
        tiedot.tulostaListaPaketeista();
        tiedot.lopetusToimenpiteet();
    }
}
