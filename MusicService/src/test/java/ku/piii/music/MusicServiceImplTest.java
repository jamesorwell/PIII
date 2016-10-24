package ku.piii.music;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import ku.piii.marshalling.JacksonJSONMarshallingSupport;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.mp3.MP3PathToMusicMapperImpl;
import ku.piii.nio.file.TextFileStoreImpl;

public class MusicServiceImplTest {

    @Test
    public void testAddAllMp3Media() {
        MusicService instance
                = new MusicServiceImpl(
                        new MusicRepositoryImpl(
                                new JacksonJSONMarshallingSupport(new ObjectMapper()),
                                new TextFileStoreImpl()),
                        new MP3PathToMusicMapperImpl()
                );
        MusicMediaCollection collectionA = instance.createMusicMediaCollection(Paths.get("../test-music-files/collection-A"));
        assertEquals(9, collectionA.getMusic().size());

        MusicMediaCollection collectionB = instance.createMusicMediaCollection(Paths.get("../test-music-files/collection-B"));
        MusicMediaCollection merge = collectionA.mergeCollection(collectionB);
        assertEquals(18, merge.getMusic().size());
    }

    @Test
    public void canAddFilesThenSaveThenLoad() {

        String pathToAddFrom = "../test-music-files/Collection-B";
        String jsonFileToSaveToAndLoadFrom = "../test-json-files/tmp1.json";

        MusicService instance
                = new MusicServiceImpl(
                        new MusicRepositoryImpl(
                                new JacksonJSONMarshallingSupport(
                                        new ObjectMapper()),
                                        new TextFileStoreImpl()),
                        new MP3PathToMusicMapperImpl()
                );
        MusicMediaCollection collection = 
                instance.createMusicMediaCollection(Paths.get(pathToAddFrom));
        assertThat(collection, Matchers.notNullValue());

        instance.saveMusicMediaCollection(Paths.get(jsonFileToSaveToAndLoadFrom), collection);
        MusicMediaCollection savedCollection = instance.loadMusicMediaCollection(Paths.get(jsonFileToSaveToAndLoadFrom));
        assertEquals(collection.getMusic().size(), savedCollection.getMusic().size());
        assertThat(savedCollection, Matchers.notNullValue());
        assertThat(savedCollection.getMusic(), Matchers.hasSize(collection.getMusic().size()));

        boolean comparisonResult = listsAreTheSame(collection.getMusic(),
                savedCollection.getMusic());
        assertEquals(comparisonResult, true);
    }

    @Test
    public void canLoadThenSaveThenLoad() {

        String jsonFileToLoadFrom = "../test-json-files/Collection-B.json";
        String jsonFileToSaveTo = "../test-json-files/tmp2.json";
        MusicService instance
                = new MusicServiceImpl(
                        new MusicRepositoryImpl(
                                new JacksonJSONMarshallingSupport(new ObjectMapper()),
                                new TextFileStoreImpl()),
                        new MP3PathToMusicMapperImpl()
                );
        MusicMediaCollection collection1
                = instance.loadMusicMediaCollection(Paths.get(jsonFileToLoadFrom));
        instance.saveMusicMediaCollection(Paths.get(jsonFileToSaveTo), collection1);
        MusicMediaCollection collection2
                = instance.loadMusicMediaCollection(Paths.get(jsonFileToSaveTo));

        boolean comparisonResult = listsAreTheSame(collection1.getMusic(),
                collection2.getMusic());
        assertEquals(comparisonResult, true);
    }

    private boolean listsAreTheSame(List<MusicMedia> list1,
            List<MusicMedia> list2) {
        if (list1 == null && list2 == null) {
            return true;
        }
        if (list1 == null) {
            System.out.println("List 1 is null and List 2 is not null: so test failed because lists are different");
            return false;
        }
        if (list2 == null) {
            System.out.println("List 2 is null and List 1 is not null: so test failed because lists are different");
            return false;
        }
        if (list1.size() != list2.size()) {
            System.out.println("List 1 has " + list1.size()
                    + " members and List 2 has " + list2.size()
                    + " members: test failed because lists are different.");
            return false;
        }

        Iterator<MusicMedia> iterator2 = list2.iterator();
        for (MusicMedia item1 : list1) {
            MusicMedia item2 = iterator2.next();
            if (itemsAreTheSame(item1, item2) == false) {
                System.out.println("Items in list are not the same: so test failed because lists are different");
                System.out.println("Item in first list is " + item1.getPath());
                System.out.println("Item in second list is " + item2.getPath());

                return false;
            }
        }
        return true;
    }

    private boolean itemsAreTheSame(MusicMedia item1, MusicMedia item2) {
        String title1 = item1.getTitle();
        String title2 = item2.getTitle();
        if (stringsAreTheSame(title1, title2) == false) {
            return false;
        }
        String path1 = item1.getPath();
        String path2 = item2.getPath();
        if (stringsAreTheSame(path1, path2) == false) {
            return false;
        }

        // now year, genre, year
        if (item1.getLengthInSeconds() != item2.getLengthInSeconds()) {
            return false;
        }

        if (stringsAreTheSame(item1.getYear(), item2.getYear()) == false) {
            return false;
        }

        if (item1.getId3Version() != item2.getId3Version()) {
            return false;
        }

        if (stringsAreTheSame(item1.getGenre(), item2.getGenre()) == false) {
            return false;
        }

        return true;

    }

    private boolean stringsAreTheSame(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 != null && s2 == null) {
            return false;
        }
        if (s2 != null && s1 == null) {
            return false;
        }
        if (s1.compareTo(s2) != 0) {
            return false;
        }
        return true;

    }

}
