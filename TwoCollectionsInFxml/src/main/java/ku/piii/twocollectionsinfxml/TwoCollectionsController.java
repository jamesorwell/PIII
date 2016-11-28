package ku.piii.twocollectionsinfxml;

import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.model.MusicMediaColumnInfo;
import ku.piii.music.MusicService;
import ku.piii.music.MusicServiceFactory;
import ku.piii.twocollectionsmodel.TwoCollectionsModel;

public class TwoCollectionsController implements Initializable {

    String defaultPathForFirstCollection = "../test-music-files/collection-A";
    String defaultPathForSecondCollection = "../test-music-files/collection-B";
    private final static MusicService MUSIC_SERVICE = MusicServiceFactory.getMusicServiceInstance();

    
    private TwoCollectionsModel model = new TwoCollectionsModel();
//    private ObservableList<MusicMedia> dataForTableView;

    @FXML
    private Label addressBook;

    @FXML
    private TableView<MusicMedia> tableView1;
    @FXML
    private TableView<MusicMedia> tableView2;

    @FXML
    public void handleButton1Action(ActionEvent event) {
        final MusicMediaCollection collection = MUSIC_SERVICE
                .createMusicMediaCollection(Paths.get(defaultPathForFirstCollection));
        ObservableList<MusicMedia>  dataForTableViewAndModel = FXCollections.observableArrayList(collection.getMusic());

        dataForTableViewAndModel.addListener(makeChangeListener(collection));

        List<MusicMediaColumnInfo> myColumnInfoList = TwoCollectionsTableViewFactory.makeColumnInfoList();

        tableView1.setItems(dataForTableViewAndModel);
        TwoCollectionsTableViewFactory.makeTable(tableView1, myColumnInfoList);
        tableView1.setEditable(true);
        
        model.setFirstCollection(dataForTableViewAndModel);
        
        
        // TODO
    }
    public void handleButton2Action(ActionEvent event) {
        final MusicMediaCollection collection = MUSIC_SERVICE
                .createMusicMediaCollection(Paths.get(defaultPathForSecondCollection));
        ObservableList<MusicMedia>  dataForTableViewAndModel = FXCollections.observableArrayList(collection.getMusic());

        dataForTableViewAndModel.addListener(makeChangeListener(collection));

        List<MusicMediaColumnInfo> myColumnInfoList = TwoCollectionsTableViewFactory.makeColumnInfoList();

        tableView2.setItems(dataForTableViewAndModel);
        TwoCollectionsTableViewFactory.makeTable(tableView2, myColumnInfoList);
        tableView2.setEditable(true);
        
        model.setSecondCollection(dataForTableViewAndModel);
        
        
        // TODO
    }

    @FXML
    private void handleAboutAction(final ActionEvent event) {
        model.swap();

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

}
