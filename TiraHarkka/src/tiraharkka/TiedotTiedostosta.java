/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiraharkka;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author hekarhu
 */
public class TiedotTiedostosta implements Serializable {

    /**
     * taulukko jossa on tieto laatikoista jolla ohjelmaa ajetaan
     */
    public ArrayList<Paketti> paketit = new ArrayList<>();
    private final String pakettienTiedostoNimi = "paketit.bin";

    /**
     * ladataan paketit tiedostosta
     *
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public TiedotTiedostosta() throws ClassNotFoundException, FileNotFoundException, IOException {
        lataaPakettiLista();

    }

    private void lataaPakettiLista() throws FileNotFoundException, IOException, ClassNotFoundException {
        File paketitTiedosto = new File(pakettienTiedostoNimi);
        if (paketitTiedosto.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(paketitTiedosto))) {
                paketit = (ArrayList<Paketti>) ois.readObject();
            }
        }
    }

    /**
     * Tallennetetaan lista paketeista tiedostoon
     *
     * @throws IOException
     */
    void lopetusToimenpiteet() throws IOException {
        File paketitTiedosto = new File(pakettienTiedostoNimi);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(paketitTiedosto))) {
            oos.writeObject(paketit);
        }
    }

    /**
     * lisää halutun määrän paketteja listalle
     */
    void lisaaPaketti() {
        System.out.println("Aluksi lisätään listaan halutut paketit, jos et halua lisätä, paina vain nolla");
        int merkinIndeksi = 0;
        int leveys;
        int korkeus;
        int syvyys;
        Scanner lukija = new Scanner(System.in);
        System.out.print("anna leveys");
        leveys = lukija.nextInt();
        while (leveys != 0) {
            System.out.print("anna korkeus");
            korkeus = lukija.nextInt();
            System.out.print("anna syvyys");
            syvyys = lukija.nextInt();
            this.paketit.add(new Paketti(leveys, korkeus,syvyys,merkinIndeksi));
            System.out.print("anna leveys, tai valitse nolla lopettaaksesi lisäyksen");
            leveys = lukija.nextInt();
            merkinIndeksi++;
        }
        paketitKokoJarjestykseen(this.paketit);
        
    }

    /**
     * tulostaa esityksen paketeista jotka ovat listalla
     */
    void tulostaListaPaketeista() {
        System.out.println("seuraavat paketit ollaan lisäämässä:");
        for (Paketti paketti : this.paketit) {
            System.out.println("leveys: " + paketti.getLeveys() + ", korkeus: " + paketti.getKorkeus()+", syvyys: "+paketti.getSyvyys()+"tilavuus: "+paketti.getTilavuus());
        }
    }

    ArrayList getLista() {
        return paketit;
    }
    private ArrayList paketitKokoJarjestykseen(ArrayList<Paketti> paketit) {
        Paketti[] paketitkokojarjestyksessa = new Paketti[paketit.size()];
        int foreachapu = 0;
        for (Paketti paketti : paketit) {
            paketitkokojarjestyksessa[foreachapu] = paketti;
            foreachapu++;
        }
        paketit.clear();
        for (int i = 1; i < paketitkokojarjestyksessa.length; ++i) {
            Paketti apu = paketitkokojarjestyksessa[i];
            int j = i;
            while (j > 0 && paketitkokojarjestyksessa[j - 1].getTilavuus() > apu.getTilavuus()) {
                paketitkokojarjestyksessa[j] = paketitkokojarjestyksessa[j - 1];
                --j;
            }
            paketitkokojarjestyksessa[j] = apu;
        }
        paketit.addAll(Arrays.asList(paketitkokojarjestyksessa));
        Collections.reverse(paketit);
        return paketit;
    }
}
