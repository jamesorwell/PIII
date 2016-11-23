package ku.piii.music;

import java.nio.file.Path;
import ku.piii.model.MusicMediaCollection;

public interface MusicService {
    public MusicMediaCollection createMusicMediaCollection(Path root);
    public void saveMusicMediaCollection(Path fileToSave, MusicMediaCollection collection);
    public MusicMediaCollection loadMusicMediaCollection(Path fileToLoad);

    
    
}
