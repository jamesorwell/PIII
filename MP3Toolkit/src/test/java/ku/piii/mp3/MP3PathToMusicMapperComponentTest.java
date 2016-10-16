package ku.piii.mp3;

import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import ku.piii.model.MusicMedia;
import static org.hamcrest.Matchers.notNullValue;

public class MP3PathToMusicMapperComponentTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenNullPathIsPassedToMP3MusicMapperItThrowsIllegalArgumentExcpetion() {
        final MP3PathToMusicMapper mp3PathToMusicMapper = new MP3PathToMusicMapperImpl();
        mp3PathToMusicMapper.mapPath(null);
    }

    @Test
    public void canMapRelativeMP3PathToMusic() {
        final Path mp3Path = createPathToMP3("../test-music-files/collection-A/Anshlavs - Second Trip - clip.mp3");
        final MP3PathToMusicMapper mp3PathToMusicMapper = new MP3PathToMusicMapperImpl();
        final MusicMedia musicMedia = mp3PathToMusicMapper.mapPath(mp3Path);
        MatcherAssert.assertThat(musicMedia, notNullValue());
        MatcherAssert.assertThat(musicMedia.getAbsolutePath(), equalTo(mp3Path.toAbsolutePath().toString()));
        MatcherAssert.assertThat(musicMedia.getTitle(), equalTo("Second Trip"));
    }

    private Path createPathToMP3(final String relativePathToMP3) {
        final Path currentAbsolutePath = Paths.get("").toAbsolutePath();
        final Path targetUnnormalisedPath = Paths.get(currentAbsolutePath.toString(), relativePathToMP3.toString());
        return targetUnnormalisedPath.normalize();
    }
}
