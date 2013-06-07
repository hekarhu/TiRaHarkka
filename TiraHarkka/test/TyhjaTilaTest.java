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
import tiraharkka.TyhjaTila;

/**
 *
 * @author hekarhu
 */
public class TyhjaTilaTest {
    
    public TyhjaTilaTest() {
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
    public void testaaTyhjaTila(){
        TyhjaTila tila = new TyhjaTila();
        assertTrue(tila.getStatus() == ' ');
        tila.varaaTila();
        assertTrue(tila.getStatus()  == 'x');
        assertFalse(tila.varaaTila());
    }
}