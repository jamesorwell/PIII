/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.twocollectionsmodel;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import de.umass.lastfm.Artist;
import static de.umass.lastfm.Artist.getInfo;
import static de.umass.lastfm.Artist.getTopTracks;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import de.umass.lastfm.Track;
import de.umass.lastfm.scrobble.ScrobbleResult;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.music.MusicService;
import ku.piii.music.MusicServiceFactory;
import ku.piii.mp3.MP3PathToMusicMapperImpl;

/**
 *
 * @author ku14009
 */
public class TwoCollectionsModel {

    MP3PathToMusicMapperImpl mp3map = new MP3PathToMusicMapperImpl();
    public String selectedArtist;
    @FXML
    private TextArea textarea;
    @FXML
    private Label toptrackslabel;
    @FXML
    private ListView toptrackslist;

    @FXML
    private Label selectCheck;
    @FXML
    private TableView<MusicMedia> tableView;
    @FXML
    private TableView tableView2;
    @FXML

    MusicMediaCollection firstCollection;
    MusicMediaCollection secondCollection;
    MusicService myMusicService
            = MusicServiceFactory.getMusicServiceInstance();

    public TwoCollectionsModel() {
        firstCollection = null;
        secondCollection = null;
    }

    public boolean haveFirstCollection() {
        if (firstCollection == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean haveSecondCollection() {
        if (secondCollection == null) {
            return false;
        } else {
            return true;
        }
    }

    public void clearFirstCollection() {
        firstCollection = null;
    }

    public void loadFirstCollection(String collectionFilename) {
        firstCollection = myMusicService.loadMusicMediaCollection(
                Paths.get(collectionFilename));
    }

    public void createFirstCollection(String root) {

        firstCollection = myMusicService.createMusicMediaCollection(
                Paths.get(root));
    }

    public void saveFirstCollection(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MusicMediaCollection getFirstCollection() {
        return firstCollection;
    }

    public void clearSecondCollection() {
        secondCollection = null;
    }

    public void loadSecondCollection(String collectionFilename) {
        secondCollection = myMusicService.loadMusicMediaCollection(
                Paths.get(collectionFilename));
    }

    public void createSecondCollection(String root) {
        secondCollection = myMusicService.createMusicMediaCollection(
                Paths.get(root));
    }

    public void saveSecondCollection(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MusicMediaCollection getSecondCollection() {
        return secondCollection;
    }

    public void setFirstCollection(List<MusicMedia> thisList) {
        firstCollection = new MusicMediaCollection();
        firstCollection.setMusic(thisList);
    }

    public void setSecondCollection(List<MusicMedia> thisList) {
        secondCollection = new MusicMediaCollection();
        secondCollection.setMusic(thisList);
    }

    public void swap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
    }

    @FXML
    public ObservableList<String> topTracks(String artist, String key) throws IOException {

        Track t;
        int i = 0;
        MusicMedia mm = new MusicMedia();
        ObservableList<String> toptracks = FXCollections.observableArrayList();
        Collection<Track> info = getTopTracks(artist, key);
        Iterator iterator = info.iterator();
        while (iterator.hasNext() && i != 10) {
            t = (Track) iterator.next();
            System.out.println(t);
            mm.setTitle(t.getName());
            toptracks.add(mm.getTitle());
            i++;
        }
        selectedArtist = artist;
        return toptracks;
    }

    public String artistInfo(String artistName, String key) {
        System.out.println("artist info called");
        Artist a = getInfo(artistName, key);

        if (a != null) {
            return a.getWikiSummary();
        } else {
            return "Sorry, an artist bio could not be found for this artist, checl the name and try again. ";
        }

    }

    public String getSelectedArtist() {
        return selectedArtist;
    }

    public void setSelectedArtist(String selectedArtist) {
        this.selectedArtist = selectedArtist;
    }

    @FXML
    public String VisitURL(String track, String key) throws URISyntaxException, IOException {
        // Track t = Track.getInfo(selectedArtist, track, key);
        if (selectedArtist == null) {
            selectedArtist = "stellardrone";    //mostly for testing purposes :\
        }
        Track t = Track.getInfo(selectedArtist, track, key);
        System.out.println("selected artist is " + selectedArtist);
        System.out.println("selected track is  " + track);
        System.out.println("selected url is  " + t.getUrl());
        if (t.getUrl() != null) {
            return t.getUrl();
        }

        return "couldnt find url";

    }

    public ObservableList<MusicMedia> addMoreFiles(File file) {
        File[] fid = file.listFiles();
        MusicMedia music = new MusicMedia();
        ObservableList<MusicMedia> dataForTableView = FXCollections.observableArrayList();
        if (file.exists()) {
            int i = 0;
            for (File m : fid) {
                if (m.getPath().endsWith(".mp3")) {
                    music.setPath(m.getPath());
                    //   System.out.println(m.getPath());
                    music = mp3map.mapPath(m.toPath());
                    /// System.out.println(music.getTitle());
                    dataForTableView.add(music);
                    System.out.println(dataForTableView.get(i).getPath());
                    i++;
                }

            }

        }
        System.out.println("title issss    :" + dataForTableView.get(0).getTitle());

        return dataForTableView;

    }

    public ObservableList<MusicMedia> addFile(File file) {

        MusicMedia music = new MusicMedia();
        ObservableList<MusicMedia> dataForTableView = FXCollections.observableArrayList();
        if (file.exists()) {

            dataForTableView.add(music = mp3map.mapPath(file.toPath()));
            System.out.println("file iss" + file.toString());
            System.out.println("file exists");
            System.out.println(dataForTableView.size());
            System.out.println("title is " + dataForTableView.get(0).getPath());
            return dataForTableView;
        } else {
            System.out.println("file doesnt exist");
            return dataForTableView;
        }

    }

    public void makeFavourite(MusicMedia selectedItem, int i) throws IOException, UnsupportedTagException, InvalidDataException, NotSupportedException {

//        System.out.println("selected item is " + selectedItem.getTitle());
        if (selectedItem.getPath() != null) {
            File oldFile = new File(selectedItem.getPath());
            File newFile = new File(selectedItem.getPath().replace(".mp3", "zzzzz.mp3") + File.separator);
            Mp3File mp3 = new Mp3File(selectedItem.getPath());
            ID3v2 idTag2;

            if (mp3.hasId3v2Tag()) {

                idTag2 = mp3.getId3v2Tag();
                if (i == 1) {
                    System.out.println("setting " + selectedItem.getPath() + " to favourite");
                    String commentFav = idTag2.getComment();
                    commentFav = commentFav + "favourite";
                    idTag2.setComment(commentFav);
                    mp3.setId3v2Tag(idTag2);
                } else if (i == 2) {
                    System.out.println("setting " + selectedItem.getPath() + " to favourite");
                    String commentFav = idTag2.getComment();
                    commentFav = commentFav.substring(0, commentFav.length() - 4);
                    idTag2.setComment(commentFav);
                    mp3.setId3v2Tag(idTag2);
                }

                mp3.setId3v2Tag(idTag2);
                System.out.println("saving fiile");
                mp3.save(newFile.toString());
                oldFile.delete();
                newFile.renameTo(oldFile);

            }
        }

    }

}
