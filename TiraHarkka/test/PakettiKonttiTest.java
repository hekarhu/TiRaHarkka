/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiraharkka.Paketti;
import tiraharkka.PakettiKontti;

/**
 *
 * @author hekarhu
 */
public class PakettiKonttiTest {
    
    public PakettiKonttiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
        @Test
    public void testaaKontinOikeaPalautus(){
        PakettiKontti paketti = new PakettiKontti(3, 3);
        assertTrue(paketti.getKorkeus() == 3);
        assertTrue(paketti.getLeveys() == 3);
        
    }
    @Test
    public void testaaKontinnOikeaPalautus2(){
        PakettiKontti paketti = new PakettiKontti(3, 3, 3);
        assertTrue(paketti.getKorkeus() == 3);
        assertTrue(paketti.getLeveys() == 3);
        assertTrue(paketti.getSyvyys() == 3);
    }
    @Test
    public void testaaKontinOikeaPalautus3(){
        PakettiKontti paketti = new PakettiKontti(3, 4, 5);
        assertTrue(paketti.getKorkeus() == 4);
        assertTrue(paketti.getLeveys() == 3);
        assertTrue(paketti.getSyvyys() == 5);
    }
}