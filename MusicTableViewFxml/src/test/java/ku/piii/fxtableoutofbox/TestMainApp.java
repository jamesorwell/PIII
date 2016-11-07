package ku.piii.fxtableoutofbox;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;

import org.loadui.testfx.GuiTest;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.music.MusicService;
import ku.piii.music.MusicServiceFactory;

import org.loadui.testfx.controls.TableViews;
import org.loadui.testfx.exceptions.NoNodesFoundException;

@SuppressWarnings("restriction")
public class TestMainApp extends GuiTest {

    final String pathScannedOnLoad = "../test-music-files/collection-A";
    private final static MusicService MUSIC_SERVICE = MusicServiceFactory.getMusicServiceInstance();

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            return parent;
        } catch (IOException ex) {
            // TODO ...
        }
        return parent;
    }

    @Test
    public void isTableViewListCorrect() throws InterruptedException {

        final Button button = (Button) find("#button");
        click(button);

        Thread.sleep(1000);

        final MusicMediaCollection expected = MUSIC_SERVICE
                .createMusicMediaCollection(Paths.get(pathScannedOnLoad));
        final TableView t = getTableView("#tableView");

        final ObservableList<MusicMedia> items = t.getItems();

        final MusicMediaCollection actual = new MusicMediaCollection();
        items.forEach(m -> actual.addMusicMedia(m));

        final List<MusicMediaEquality> expectedMusic = expected.getMusic().stream().map(MusicMediaEquality::new)
                .collect(Collectors.toList());

        final List<MusicMediaEquality> actualMusic = actual.getMusic().stream().map(MusicMediaEquality::new)
                .collect(Collectors.toList());

        assertThat(actualMusic, containsInAnyOrder(expectedMusic.toArray()));

    }

    protected static Object cellValue(String tableSelector, int row, int column) {
        return getTableView(tableSelector).getColumns().get(column).getCellData(row);
    }

    protected static TableCell<?, ?> cell(String tableSelector, int row, int column) {
        List<Node> current = row(tableSelector, row).getChildrenUnmodifiable();
        while (current.size() == 1 && !(current.get(0) instanceof TableCell)) {
            current = ((Parent) current.get(0)).getChildrenUnmodifiable();
        }

        Node node = current.get(column);
        if (node instanceof TableCell) {
            return (TableCell<?, ?>) node;
        } else {
            throw new RuntimeException("Expected TableRowSkin with only TableCells as children");
        }
    }

    protected static TableRow<?> row(String tableSelector, int row) {

        TableView<?> tableView = getTableView(tableSelector);

        List<Node> current = tableView.getChildrenUnmodifiable();
        while (current.size() == 1) {
            current = ((Parent) current.get(0)).getChildrenUnmodifiable();
        }

        current = ((Parent) current.get(1)).getChildrenUnmodifiable();
        while (!(current.get(0) instanceof TableRow)) {
            current = ((Parent) current.get(0)).getChildrenUnmodifiable();
        }

        Node node = current.get(row);
        if (node instanceof TableRow) {
            return (TableRow<?>) node;
        } else {
            throw new RuntimeException("Expected Group with only TableRows as children");
        }
    }

    private static <T> TableView<T> getTableView(String tableSelector) {
        Node node = find(tableSelector);
        if (!(node instanceof TableView)) {
            throw new NoNodesFoundException(tableSelector + " selected " + node + " which is not a TableView!");
        }
        return (TableView<T>) node;
    }

    public static class MusicMediaEquality {

        public final MusicMedia musicMedia;

        public MusicMediaEquality(final MusicMedia musicMedia) {
            this.musicMedia = musicMedia;
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder()
                    .append(musicMedia.getPath())
                    .append(musicMedia.getTitle())
                    .append(musicMedia.getYear()).toHashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            final MusicMediaEquality other = (MusicMediaEquality) obj;
            return new EqualsBuilder()
                    .append(musicMedia.getPath(), other.musicMedia.getPath())
                    .append(musicMedia.getTitle(), other.musicMedia.getTitle())
                    .append(musicMedia.getYear(), other.musicMedia.getYear()).isEquals();
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(musicMedia);
        }
    }

}
