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

/**
 *
 * @author hekarhu
 */
public class PakettiTest {
    
    public PakettiTest() {
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
    public void testaaPaketinOikeaPalautus(){
        Paketti paketti = new Paketti(3, 3);
        assertTrue(paketti.getKorkeus() == 3);
        assertTrue(paketti.getLeveys() == 3);
        
    }
    @Test
    public void testaaPaketinOikeaPalautus2(){
        Paketti paketti = new Paketti(3, 3, 3);
        assertTrue(paketti.getKorkeus() == 3);
        assertTrue(paketti.getLeveys() == 3);
        assertTrue(paketti.getSyvyys() == 3);
    }
    @Test
    public void testaaPaketinOikeaPalautus3(){
        Paketti paketti = new Paketti(3, 4, 5);
        assertTrue(paketti.getKorkeus() == 4);
        assertTrue(paketti.getLeveys() == 3);
        assertTrue(paketti.getSyvyys() == 5);
    }
}