/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.musicbrainztoolkit;

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
public class AlbumFinderTest {
    
    public AlbumFinderTest() {
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
     * Test of getAlbumFromArtistAndTrack method, of class AlbumFinder.
     */
    @Test
    public void testGetAlbumFromArtistAndTrack() {
        System.out.println("getAlbumFromArtistAndTrack");
        String artistNameToSearchFor = "Lindsey Stirling";
        String trackNameToSearchFor = "";
        AlbumFinder instance = new AlbumFinder();
        String expResult = "";
        String result = instance.getAlbumFromArtistAndTrack(artistNameToSearchFor, trackNameToSearchFor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
