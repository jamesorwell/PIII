/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.musicbrainztoolkit;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class ArtistFinderTest {
    
    public ArtistFinderTest() {
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

    /**
     * Test of GetArtistsFromTitle method, of class ArtistFinder.
     */
    @Test
    public void testGetArtistsFromTitle() {
        System.out.println("GetArtistsFromTitle");
        String titleToSearchWith = "I will survive";
        ArtistFinder instance = new ArtistFinder();
        List<String> expResult = null;
        List<String> result = instance.GetArtistsFromTitle(titleToSearchWith);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
