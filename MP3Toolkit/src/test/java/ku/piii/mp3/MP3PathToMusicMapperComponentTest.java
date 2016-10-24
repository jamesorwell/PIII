package ku.piii.mp3;

import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import ku.piii.model.MusicMedia;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.notNullValue;

public class MP3PathToMusicMapperComponentTest {

    @Test(expected = IllegalArgumentException.class)
    public void nullInputTest() {
        final MP3PathToMusicMapper mp3PathToMusicMapper = new MP3PathToMusicMapperImpl();
        mp3PathToMusicMapper.mapPath(null);
    }

    @Test
    public void canMapRelativeMP3PathToMusic() {
        String filename = "DARKPOP BAND ANGELIQUE - PERFECT WORLD (AMBIENT) - clip.mp3";
        final Path mp3Path = createPathToMP3("../test-music-files/collection-A/" + filename);
        final MP3PathToMusicMapper mp3PathToMusicMapper = new MP3PathToMusicMapperImpl();
        final MusicMedia musicMedia = mp3PathToMusicMapper.mapPath(mp3Path);
        MatcherAssert.assertThat(musicMedia, notNullValue());
        MatcherAssert.assertThat(musicMedia.getPath(), equalTo(mp3Path.toAbsolutePath().toString()));
        MatcherAssert.assertThat(musicMedia.getTitle(), equalTo("PERFECT WORLD (AMBIENT)"));
        MatcherAssert.assertThat(musicMedia.getYear(), equalTo("1999"));
        MatcherAssert.assertThat(musicMedia.getGenre(), equalTo("Alt. Rock"));

    }

    private Path createPathToMP3(final String relativePathToMP3) {
        final Path currentAbsolutePath = Paths.get("").toAbsolutePath();
        final Path targetUnnormalisedPath = Paths.get(currentAbsolutePath.toString(), relativePathToMP3.toString());
        return targetUnnormalisedPath.normalize();
    }
}
