package ku.piii.music;

import static java.util.Optional.ofNullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.mp3.MP3PathToMusicMapper;
import ku.piii.nio.file.SimpleMp3FileVisitor;

public class MusicServiceImpl implements MusicService {

    private final MusicRepository musicRepository;
    private final MP3PathToMusicMapper mapper;

    @Override
    public MusicMediaCollection loadMusicMediaCollection(final Path fileToLoad) {
        return musicRepository.loadCollection(fileToLoad);
    }

    @Override
    public void saveMusicMediaCollection(final Path fileToSave, final MusicMediaCollection collection) {
        musicRepository.saveCollection(fileToSave, collection);
    }

    public MusicServiceImpl(final MusicRepository musicRepository, MP3PathToMusicMapper myMapper) {
        this.musicRepository = musicRepository;
        this.mapper = myMapper;
    }

    @Override
    public MusicMediaCollection createMusicMediaCollection(final Path root) {
        SimpleMp3FileVisitor myVisitor = new SimpleMp3FileVisitor();

        try {
            Files.walkFileTree(root, myVisitor);
        } catch (IOException ex) {
            Logger.getLogger(MusicServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        final MusicMediaCollection collection = new MusicMediaCollection();
        for (Path p : myVisitor.getListOfMP3Files()) {
            collection.addMusicMedia(mapper.mapPath(p));
        }

        return collection;
    }


}
