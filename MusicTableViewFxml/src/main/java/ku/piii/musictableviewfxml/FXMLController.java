package ku.piii.musictableviewfxml;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Track;
import de.umass.lastfm.Track.*;
import static de.umass.lastfm.Artist.getTopTracks;
import static de.umass.lastfm.Artist.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.swing.JFileChooser;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.model.MusicMediaColumnInfo;
import ku.piii.music.MusicService;
import ku.piii.music.MusicServiceFactory;
import ku.piii.marshalling.JacksonJSONMarshallingSupport;

public class FXMLController implements Initializable {

    public String key = "db0feeb2f3625eb7400e31b982474319";
    JacksonJSONMarshallingSupport js;
    String pathScannedOnLoad = "../test-music-files/";
    private final static MusicService MUSIC_SERVICE = MusicServiceFactory.getMusicServiceInstance();

    private ObservableList<MusicMedia> dataForTableView;
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
    private Button button;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        final MusicMediaCollection collection = MUSIC_SERVICE
                .createMusicMediaCollection(Paths.get(pathScannedOnLoad));
        dataForTableView = FXCollections.observableArrayList(collection.getMusic());

        dataForTableView.addListener(makeChangeListener(collection));
        dataForTableView.add(tableView.getSelectionModel().getSelectedItem());
        List<MusicMediaColumnInfo> myColumnInfoList = TableViewFactory.makeColumnInfoList();

        tableView.setItems(dataForTableView);
        TableViewFactory.makeTable(tableView, myColumnInfoList);
        TableViewFactory.makeTable(tableView2, myColumnInfoList);
        tableView.setEditable(true);
        button.setVisible(false);
    }

    @FXML
    private void handleLookUpBtnAction(ActionEvent event) {

        if (tableView.getSelectionModel().getSelectedItems().isEmpty()) {
            selectCheck.setText("you need to select an item from this list");
        } else {
            selectCheck.setText("");
            dataForTableView = FXCollections.observableArrayList(tableView.getSelectionModel().getSelectedItem());
            List<MusicMediaColumnInfo> myColumnInfoList = TableViewFactory.makeColumnInfoList();
            List<MusicMedia> myList = tableView2.getItems(); //this is append items to the table instead of wiping it each time
            int i = 0;

            for (MusicMedia m : myList) {
                dataForTableView.add(myList.get(i));
                i++;
            }
            tableView2.setItems(dataForTableView);
        }
    }
    
   

    @FXML
    private void handleAboutAction(final ActionEvent event) {
        System.out.println("hello about working");
    }

    @FXML
    private void handleKeyInput(final InputEvent event) {
        if (event instanceof KeyEvent) {
            final KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A) {
                System.out.println("sfsds");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private static ListChangeListener<MusicMedia> makeChangeListener(final MusicMediaCollection collection) {
        return new ListChangeListener<MusicMedia>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends MusicMedia> change) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        for (MusicMedia addedChild : change.getAddedSubList()) {
                            collection.addMusicMedia(addedChild);
                        }
                    }
                    if (change.wasRemoved()) {
                        for (MusicMedia removedChild : change.getRemoved()) {
                            collection.removeMusicMedia(removedChild);
                        }
                    }
                }
            }
        };
    }

    @FXML
    private void topTracks(ActionEvent event) throws IOException {

        Track t;
        int i = 0;
        MusicMedia mm = new MusicMedia();

        ObservableList<String> toptracks = FXCollections.observableArrayList();

        MusicMedia artist = tableView.getSelectionModel().getSelectedItem();
        // textarea.wrapTextProperty();
        Collection<Track> info = getTopTracks(artist.getArtist(), key);

        Iterator iterator = info.iterator();
        while (iterator.hasNext() && i != 10) {
            textarea.setVisible(true);
            toptrackslist.setVisible(true);
            toptrackslabel.setVisible(true);
            t = (Track) iterator.next();
            System.out.println(t);
            
            mm.setTitle(t.getName());
            toptracks.add(mm.getTitle());
            toptrackslabel.setText("top tracks for " + artist.getArtist());
            i++;
        }
        selectedArtist = artist.getArtist();
        textarea.setText(artistInfo(artist.getArtist()));
        toptrackslist.setItems(toptracks);

    }
    @FXML
     private void VisitURL(){        
       //  ObservableList<Track> track = toptrackslist.getSelectionModel().getSelectedItems();
       String tra = (String) toptrackslist.getSelectionModel().getSelectedItem();
       System.out.println(tra);
       System.out.println(selectedArtist);
       Track t = Track.getInfo(selectedArtist, tra, key);
       System.out.println("URL HERE ==== " + t.getUrl());
       
    }

    public String artistInfo(String artistName) {

        Artist a = getInfo(artistName, key);
        
        System.out.println(a.getUrl());
        return a.getWikiSummary();
    }

    @FXML
    public void browse() throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Add Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            addMoreFiles(chooser.getSelectedFile().getPath());
        } else {
            System.out.println("something went wrong with choosing a folder!");
        }
    }

    @FXML
    private void addMoreFiles(String path) {

        final MusicMediaCollection collection = MUSIC_SERVICE
                .createMusicMediaCollection(Paths.get(path));
        dataForTableView = FXCollections.observableArrayList(collection.getMusic());
        List<MusicMedia> myList = tableView.getItems(); //this is append items to the table instead of wiping it each time
        int i = 0;
        for (MusicMedia m : myList) {
            dataForTableView.add(myList.get(i));
            i++;
        }
        dataForTableView.addListener(makeChangeListener(collection));
        tableView.setItems(dataForTableView);
        tableView.setEditable(true);

    }

}
