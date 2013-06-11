/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiraharkka;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author hekarhu
 */
class PaketitLaatikkoon {

    private TiedotTiedostosta tiedot;
    private ArrayList<Paketti> lista;
    private final int paketinkoko = 5;
    private final int laatikonkoko = 20;
    private char laatikko[][][] = new char[laatikonkoko][laatikonkoko][laatikonkoko];

    public enum Mitenpain {

        EIKAANNETTY, KAANNETTYXAKSELINSUUNTAISESTIKERRAN, KAANNETTYXAKSELINSUUNTAISESTIKAHDESTI, KAANNETTYYAKSELINSUUNTAISESTIKERRAN, KAANNETTYYAKSELINSUUNTAISESTIKAHDESTI
    }

    public enum Suunta {

        VAAKA, PYSTY, KOHTISUORAAN
    }

    public PaketitLaatikkoon(TiedotTiedostosta tiedot) {
        this.tiedot = tiedot;
        this.lista = tiedot.getLista();
        asetaLaatikkoTyhjaksi();
        
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

        for (Paketti paketti : this.lista) {
            moneskoLaatikko++;
            System.out.println("moneskolaatikko " + moneskoLaatikko);
            sovitus(paketti);

        }
    }

    private void sovitus(Paketti paketti) {
        for (int vaakaIndeksi = 0; vaakaIndeksi < laatikonkoko; vaakaIndeksi++) {
            for (int pystyIndeksi = 0; pystyIndeksi < laatikonkoko; pystyIndeksi++) {
                for (int syvyysIndeksi = 0; syvyysIndeksi < laatikonkoko; syvyysIndeksi++) {
                    if (this.laatikko[vaakaIndeksi][pystyIndeksi][syvyysIndeksi] == 'o' && tarkistaTilanne(this.laatikko, vaakaIndeksi, pystyIndeksi, syvyysIndeksi, paketti)) {
                        if (sijoitaLaatikkoon(this.laatikko, vaakaIndeksi, pystyIndeksi, syvyysIndeksi, paketti)) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private boolean sijoitaLaatikkoon(char[][][] laatikko, int vaakaIndeksi, int pystyIndeksi, int syvyysIndeksi, Paketti paketti) {
        for (int k = 0; k < paketti.getKorkeus(); k++) {
            for (int l = 0; l < paketti.getLeveys(); l++) {
                for (int n = 0; n < paketti.getSyvyys(); n++) {
                    laatikko[vaakaIndeksi][pystyIndeksi][syvyysIndeksi] = paketti.getAakkonen();
                    syvyysIndeksi++;
                }
                pystyIndeksi++;
                syvyysIndeksi = syvyysIndeksi - 5;
            }
            vaakaIndeksi++;
            pystyIndeksi = pystyIndeksi - 5;
        }
        return true;
    }

    public boolean tarkistaTilanne(char[][][] laatikko, int vaakaRivi, int sarake, int syvyys, Paketti paketti) {
        for (Suunta suunta : Suunta.values()) {
            if (tarkistaNaapurit(1, vaakaRivi, sarake, syvyys, suunta, paketti)) {
                return true;
            }
        }
        return false;
    }

    private boolean tarkistaNaapurit(int montakosuorassa, int vaakaRivi, int sarake, int syvyys, Suunta suunta, Paketti paketti) {
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
            return tarkistaNaapurit(montakosuorassa, seuraavaVaakarivi, seuraavaSarake, seuraavaSyvyys, suunta, paketti);
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
            case VAAKA:
            case PYSTY:
            default:
                return syvyys;

        }
    }
    // itse järjestämistä koskevat metodit ovat tämä yläpuolella
    
    

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
}
