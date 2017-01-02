/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.twocollectionsmodel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Locale;
import javafx.collections.ObservableList;
import ku.piii.model.MusicMedia;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author James
 */
public class TwoCollectionsModelTest {

    public TwoCollectionsModelTest() {
    }

    @Test
    public void testMetestTopTrack() throws IOException, URISyntaxException {
        System.out.println("test of my method");
        TwoCollectionsModel testModel = new TwoCollectionsModel();

        String pathA = "../test-music-files/Collection-A";
        String pathB = "../test-music-files/Collection-B";
        testModel.createFirstCollection(pathA);
        testModel.createSecondCollection(pathB);

        String artist = "Stellardrone";
        String key = "db0feeb2f3625eb7400e31b982474319";
        String topTrack = "Red Giant";

        ObservableList<String> a = testModel.topTracks(artist, key);

        String track = a.get(0);
        assertEquals(track, topTrack);

    }

    @Test
    public void testURL() throws URISyntaxException, IOException {
        TwoCollectionsModel testModel = new TwoCollectionsModel();

        String pathA = "../test-music-files/Collection-A";
        String pathB = "../test-music-files/Collection-B";
        testModel.createFirstCollection(pathA);
        testModel.createSecondCollection(pathB);

        String key = "db0feeb2f3625eb7400e31b982474319";
        String topTrack = "Red Giant";
        String URL = "https://www.last.fm/music/Stellardrone/_/Red+Giant";
        testModel.setSelectedArtist("stellardrone");
        testModel.selectedArtist = "stellardrone";

        String URLTwo = testModel.VisitURL(topTrack, key);
        assertEquals(URL, URLTwo);

    }

    @Test
    public void testAddFile() {
        TwoCollectionsModel testModel = new TwoCollectionsModel();

        String pathA = "../test-music-files/Collection-A";
        String pathB = "../test-music-files/Collection-B";
        testModel.createFirstCollection(pathA);
        testModel.createSecondCollection(pathB);

        File f;
        String testPath = "E:\\uni year 3\\programming\\PIII\\test-music-files\\musicTest\\style2.mp3";
        f = new File(testPath);
        String eResult = "style";
        System.out.println("test working!!");
        System.out.println("test path is  :" +testPath);
        ObservableList<MusicMedia> testdata = testModel.addFile(f);
        ;
        int size = testdata.size();
        System.out.println("size of list" + size);
        String aResult = testdata.get(0).getTitle();
        System.out.println(aResult);

        assertEquals(eResult.toLowerCase(), aResult.toLowerCase());
    }
    
  

}
