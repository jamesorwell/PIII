package ku.piii.music;

import java.nio.file.Path;

import ku.piii.model.MusicMediaCollection;

public interface MusicRepository{
    public MusicMediaCollection loadCollection(final Path file);    
    public void saveCollection(final Path fileToSave, final MusicMediaCollection musicMediaCollection);
}
