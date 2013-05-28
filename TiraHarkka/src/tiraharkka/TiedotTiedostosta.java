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
     *ladataan paketit tiedostosta
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
        int leveys;
        int korkeus;
        Scanner lukija = new Scanner(System.in);
        System.out.print("anna leveys");
        leveys = lukija.nextInt();
        while (leveys != 0) {
            System.out.print("anna korkeus");
            korkeus = lukija.nextInt();
            paketit.add(new Paketti(leveys, korkeus));
            System.out.print("anna leveys");
            leveys = lukija.nextInt();

        }
    }
/**
 * tulostaa esityksen paketeista jotka ovat listalla
 */
    void tulostaListaPaketeista() {
        for (Paketti paketti : paketit) {
            System.out.println("leveys: " + paketti.getLeveys() + ", korkeus: " + paketti.getKorkeus());
        }
    }
    ArrayList getLista(){
        return paketit;
    }
}
