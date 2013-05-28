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

        VAAKA, PYSTY, ETUKENO
    }
    TiedotTiedostosta tiedot;
    ArrayList<Paketti> lista;
    final int paketinkoko = 5;
    final int laatikonkoko = 20;
    TyhjaTila laatikko[][] = new TyhjaTila[laatikonkoko][laatikonkoko];

    public PaketitLaatikkoon(TiedotTiedostosta tiedot) {

        this.tiedot = tiedot;
        this.lista = tiedot.getLista();
        asetaLaatikkoTyhjaksi();
    }

    public void piirraLaatikko() {
        for (int i = 0; i < laatikonkoko; i++) {
            for (int j = 0; j < laatikonkoko; j++) {
                System.out.print(this.laatikko[i][j].getStatus());
            }
            System.out.println("");
        }
    }

    private void asetaLaatikkoTyhjaksi() {
        for (int i = 0; i < laatikonkoko; i++) {
            for (int j = 0; j < laatikonkoko; j++) {
                this.laatikko[i][j] = new TyhjaTila();
            }
        }
    }

    public void sovitetaanLaatikkoon() {

        int vaadittuLeveys;
        int vaadittuKorkeus;
        int moneskoLaatikko=0;
        for (Paketti paketti : lista) {
            System.out.println("moneskolaatikko " +moneskoLaatikko);
            moneskoLaatikko++;
            vaadittuLeveys = paketti.getLeveys() / 5;
            vaadittuKorkeus = paketti.getKorkeus() / 5;
            System.out.println("leveys " + vaadittuLeveys + " korkeus " + vaadittuKorkeus);
            sovitus(vaadittuKorkeus, vaadittuLeveys);

        }
    }
     private void sovitus(int vaadittuKorkeus, int vaadittuLeveys) {
        for (int i = 0; i < laatikonkoko; i++) {
            for (int j = 0; j < laatikonkoko; j++) {
                if (this.laatikko[i][j].getStatus() == 'o' && tarkistaTilanne(this.laatikko, i, j)) {
                    System.out.println("tässä");
                    if(sijoitaLaatikkoon(this.laatikko, i, j, vaadittuKorkeus, vaadittuLeveys)){
                        return;
                    }
                }
            }
        }
    }

    private boolean sijoitaLaatikkoon(TyhjaTila[][] laatikko, int i, int j, int vaadittuKorkeus, int vaadittuLeveys) {
        int summa=0;
        System.out.println("vaadittukorkeus " + vaadittuKorkeus + " vaadittuleveys " + vaadittuLeveys + " i,j " + i + "," + j);
        for (int k = 0; k < vaadittuKorkeus; k++) {
            for (int l = 0; l < vaadittuLeveys; l++) {
                laatikko[i][j].varaaTila();
                piirraLaatikko();
                j++;
                summa++;
                System.out.println("vaadittukorkeus " + vaadittuKorkeus + " vaadittuleveys " + vaadittuLeveys + " i,j " + i + "," + j+","+summa);
            }
            i++;
            j = j-4;
        }
        return true;
    }

    public boolean tarkistaTilanne(TyhjaTila[][] laatikko, int vaakaRivi, int sarake) {
        for (Suunta suunta : Suunta.values()) {
            if (tarkistaNaapurit(1, vaakaRivi, sarake, suunta)) {
                return true;
            }
        }
        return false;
    }

    private boolean tarkistaNaapurit(int montakosuorassa, int vaakaRivi, int sarake, Suunta suunta) {
        int seuraavaVaakarivi = laskeVaakarivi(suunta, vaakaRivi);
        int seuraavaSarake = laskeSarake(suunta, sarake);
        boolean naapuriSama = false;
        if (onkoLaatikonSisalla(seuraavaVaakarivi, seuraavaSarake)) {
            naapuriSama = onkoSamatNaapurit(vaakaRivi, sarake, seuraavaVaakarivi, seuraavaSarake);
        }
        if (naapuriSama) {
            montakosuorassa++;
            if (montakosuorassa == this.paketinkoko) {
                return true;
            }
            return tarkistaNaapurit(montakosuorassa, seuraavaVaakarivi, seuraavaSarake, suunta);
        }
        return false;
    }

    private boolean onkoLaatikonSisalla(int seuraavaVaakarivi, int seuraavaSarake) {
        return (onkoSisalla(seuraavaVaakarivi) && onkoSisalla(seuraavaSarake));

    }

    private boolean onkoSisalla(int indeksi) {
        return (indeksi >= 0 && indeksi < this.laatikonkoko);

    }

    private boolean onkoSamatNaapurit(int vaakaRivi, int sarake, int seuraavaVaakarivi, int seuraavaSarake) {
        return (laatikko[vaakaRivi][sarake].getStatus() == laatikko[seuraavaVaakarivi][seuraavaSarake].getStatus());
    }

    private static int laskeVaakarivi(Suunta suunta, int vaakaRivi) {
        switch (suunta) {
            case ETUKENO:
            case PYSTY:
                return vaakaRivi + 1;

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
            default:
                return sarake;

        }
    }
}
