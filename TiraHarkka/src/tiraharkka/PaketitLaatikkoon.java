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
    TyhjaTila laatikko[][][] = new TyhjaTila[laatikonkoko][laatikonkoko][laatikonkoko];

    public PaketitLaatikkoon(TiedotTiedostosta tiedot) {
        this.tiedot = tiedot;
        this.lista = tiedot.getLista();
        asetaLaatikkoTyhjaksi();
    }

    public void piirraLaatikkoPaalta() {
        System.out.println("laatikko päältä katsottuna");
        for (int i = 0; i < laatikonkoko; i++) {
            for (int j = 0; j < laatikonkoko; j++) {
                System.out.print(this.laatikko[i][j][0].getStatus());
            }
            System.out.println("");
        }
    }

    public void piirraLaatikkoSivulta() {
        System.out.println("laatikko sivulta katsottuna:");
        for (int i = 0; i < laatikonkoko; i++) {

                for (int k = 0; k < laatikonkoko; k++) {
                    System.out.print(this.laatikko[i][0][k].getStatus());
                }
            
            System.out.println("");
        }
    }

    private void asetaLaatikkoTyhjaksi() {
        for (int i = 0; i < laatikonkoko; i++) {
            for (int j = 0; j < laatikonkoko; j++) {
                for (int k = 0; k < laatikonkoko; k++) {
                    this.laatikko[i][j][k] = new TyhjaTila();
                }

            }
        }
    }

    public void sovitetaanLaatikkoon() {

        int vaadittuLeveys;
        int vaadittuKorkeus;
        int vaadittuSyvyys;
        int moneskoLaatikko = 0;
        for (Paketti paketti : lista) {
            System.out.println("moneskolaatikko " + moneskoLaatikko);
            moneskoLaatikko++;
            vaadittuLeveys = paketti.getLeveys();
            vaadittuKorkeus = paketti.getKorkeus();
            vaadittuSyvyys = paketti.getSyvyys();
            System.out.println("leveys " + vaadittuLeveys + " korkeus " + vaadittuKorkeus);
            sovitus(vaadittuKorkeus, vaadittuLeveys, vaadittuSyvyys);

        }
    }

    private void sovitus(int vaadittuKorkeus, int vaadittuLeveys,int vaadittuSyvyys) {
        for (int i = 0; i < laatikonkoko; i++) {
            for (int j = 0; j < laatikonkoko; j++) {
                for (int k = 0; k < laatikonkoko; k++) {
                    if (this.laatikko[i][j][k].getStatus() == 'o' && tarkistaTilanne(this.laatikko, i, j, k)) {
                        if (sijoitaLaatikkoon(this.laatikko, i, j, k, vaadittuKorkeus, vaadittuLeveys, vaadittuSyvyys)) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private boolean sijoitaLaatikkoon(TyhjaTila[][][] laatikko, int i, int j, int s, int vaadittuKorkeus, int vaadittuLeveys,int vaadittuSyvyys) {
        for (int k = 0; k < vaadittuKorkeus; k++) {
            for (int l = 0; l < vaadittuLeveys; l++) {
                for (int n = 0; n < vaadittuSyvyys; n++) {
                    laatikko[i][j][s].varaaTila();
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

    public boolean tarkistaTilanne(TyhjaTila[][][] laatikko, int vaakaRivi, int sarake, int syvyys) {
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
        return (laatikko[vaakaRivi][sarake][syvyys].getStatus() == laatikko[seuraavaVaakarivi][seuraavaSarake][syvyys].getStatus());
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
