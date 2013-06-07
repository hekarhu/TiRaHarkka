/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiraharkka;

import java.util.ArrayList;

/**
 *
 * @author hekarhu
 */
class PaketitLaatikkoon {

    public enum Suunta {

        VAAKA, PYSTY, ETUKENO, KOHTISUORAAN
    }
    TiedotTiedostosta tiedot;
    ArrayList<Paketti> lista;
    final int paketinkoko = 5;
    final int laatikonkoko = 20;
    char laatikko[][][] = new char[laatikonkoko][laatikonkoko][laatikonkoko];

    public PaketitLaatikkoon(TiedotTiedostosta tiedot) {
        this.tiedot = tiedot;
        this.lista = tiedot.getLista();
        asetaLaatikkoTyhjaksi();
    }

    public void piirraLaatikkoPaalta() {
        System.out.println("laatikko päältä katsottuna");
        for (int i = 0; i < laatikonkoko; i++) {
            for (int j = 0; j < laatikonkoko; j++) {
                System.out.print(this.laatikko[i][j][0]);
            }
            System.out.println("");
        }
    }

    public void piirraLaatikkoSivulta() {
        System.out.println("laatikko sivulta katsottuna:");
        for (int i = 0; i < laatikonkoko; i++) {

                for (int k = 0; k < laatikonkoko; k++) {                    
                        System.out.print(this.laatikko[i][0][k]);
                }
            
            System.out.println("");
        }
    }

    private void asetaLaatikkoTyhjaksi() {
        for (int i = 0; i < laatikonkoko; i++) {
            for (int j = 0; j < laatikonkoko; j++) {
                for (int k = 0; k < laatikonkoko; k++) {
                    this.laatikko[i][j][k] = 'o';
                }

            }
        }
    }

    public void sovitetaanLaatikkoon() {

        int moneskoLaatikko = 0;
        for (Paketti paketti : lista) {
            moneskoLaatikko++;
            System.out.println("moneskolaatikko " + moneskoLaatikko);
            sovitus(paketti);

        }
    }

    private void sovitus(Paketti paketti) {
        for (int i = 0; i < laatikonkoko; i++) {
            for (int j = 0; j < laatikonkoko; j++) {
                for (int k = 0; k < laatikonkoko; k++) {
                    if (this.laatikko[i][j][k] == 'o' && tarkistaTilanne(this.laatikko, i, j, k)) {
                        if (sijoitaLaatikkoon(this.laatikko, i, j, k,paketti)) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private boolean sijoitaLaatikkoon(char[][][] laatikko1, int i, int j, int s, Paketti paketti) {
        for (int k = 0; k < paketti.getKorkeus(); k++) {
            for (int l = 0; l < paketti.getLeveys(); l++) {
                for (int n = 0; n < paketti.getSyvyys(); n++) {
                    laatikko[i][j][s] = paketti.getAakkonen();
                    s++;
                }
                j++;
                s = s - 5;
            }
            i++;
            j = j - 5;
        }
        return true;
    }

    public boolean tarkistaTilanne(char[][][] laatikko, int vaakaRivi, int sarake, int syvyys) {
        for (Suunta suunta : Suunta.values()) {
            if (tarkistaNaapurit(1, vaakaRivi, sarake, syvyys, suunta)) {
                return true;
            }
        }
        return false;
    }

    private boolean tarkistaNaapurit(int montakosuorassa, int vaakaRivi, int sarake, int syvyys, Suunta suunta) {
        int seuraavaVaakarivi = laskeVaakarivi(suunta, vaakaRivi);
        int seuraavaSarake = laskeSarake(suunta, sarake);
        int seuraavaSyvyys = laskeSyvyys(suunta, syvyys);
        boolean naapuriSama = false;
        if (onkoLaatikonSisalla(seuraavaVaakarivi, seuraavaSarake)) {
            naapuriSama = onkoSamatNaapurit(vaakaRivi, sarake, syvyys, seuraavaVaakarivi, seuraavaSarake, seuraavaSyvyys);
        }
        if (naapuriSama) {
            montakosuorassa++;
            if (montakosuorassa == this.paketinkoko) {
                return true;
            }
            return tarkistaNaapurit(montakosuorassa, seuraavaVaakarivi, seuraavaSarake, seuraavaSyvyys, suunta);
        }
        return false;
    }

    private boolean onkoLaatikonSisalla(int seuraavaVaakarivi, int seuraavaSarake) {
        return (onkoSisalla(seuraavaVaakarivi) && onkoSisalla(seuraavaSarake));

    }

    private boolean onkoSisalla(int indeksi) {
        return (indeksi >= 0 && indeksi < this.laatikonkoko);

    }

    private boolean onkoSamatNaapurit(int vaakaRivi, int sarake, int syvyys, int seuraavaVaakarivi, int seuraavaSarake, int seuraavaSyvyys) {
        return (laatikko[vaakaRivi][sarake][syvyys] == laatikko[seuraavaVaakarivi][seuraavaSarake][syvyys]);
    }

    private static int laskeVaakarivi(Suunta suunta, int vaakaRivi) {
        switch (suunta) {
            case ETUKENO:
            case PYSTY:
                return vaakaRivi + 1;
            case KOHTISUORAAN:
            case VAAKA:
            default:
                return vaakaRivi;
        }
    }

    private static int laskeSarake(Suunta suunta, int sarake) {
        switch (suunta) {
            case ETUKENO:
            case VAAKA:
                return sarake + 1;
            case PYSTY:
            case KOHTISUORAAN:
            default:
                return sarake;

        }
    }

    private int laskeSyvyys(Suunta suunta, int syvyys) {
        switch (suunta) {
            case KOHTISUORAAN:
                return syvyys + 1;
            case ETUKENO:
            case VAAKA:
            case PYSTY:
            default:
                return syvyys;

        }
    }
}
