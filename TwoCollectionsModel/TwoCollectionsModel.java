/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.twocollectionsmodel;

import de.umass.lastfm.Artist;
import static de.umass.lastfm.Artist.*;
import static de.umass.lastfm.Artist.getInfo;
import static de.umass.lastfm.Artist.getTopTracks;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import de.umass.lastfm.Track;
import de.umass.lastfm.Track.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.music.MusicService;
import ku.piii.music.MusicServiceFactory;


/**
 *
 * @author ku14009
 */
public class TwoCollectionsModel {
    

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
    
    public void setFirstCollection(List<MusicMedia> thisList)
    {
        firstCollection = new MusicMediaCollection();
        firstCollection.setMusic(thisList);
    }
    public void setSecondCollection(List<MusicMedia> thisList)
    {
        secondCollection = new MusicMediaCollection();        
        secondCollection.setMusic(thisList);
    }
    public void swap()
    {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
    }
    
   
     
  
}
