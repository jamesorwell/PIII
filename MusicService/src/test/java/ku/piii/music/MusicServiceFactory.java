package ku.piii.music;

import com.fasterxml.jackson.databind.ObjectMapper;

import ku.piii.marshalling.JacksonJSONMarshallingSupport;
import ku.piii.mp3.MP3PathToMusicMapperImpl;
import ku.piii.nio.file.TextFileStoreImpl;

public class MusicServiceFactory {

    public static MusicService getMusicServiceInstance() {
        final MusicService musicService = new MusicServiceImpl(
                new MusicRepositoryImpl
                        (new JacksonJSONMarshallingSupport(
                                new ObjectMapper()), 
                                new TextFileStoreImpl()),
                new MP3PathToMusicMapperImpl());
        return musicService;
    }
}
