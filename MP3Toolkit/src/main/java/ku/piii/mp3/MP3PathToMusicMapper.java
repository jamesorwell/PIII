package ku.piii.mp3;

import java.nio.file.Path;

import ku.piii.model.MusicMedia;

public interface MP3PathToMusicMapper {

    public MusicMedia mapPath(final Path mp3File);
}
