package ku.piii.music;

import com.fasterxml.jackson.databind.ObjectMapper;

import ku.piii.marshalling.JacksonJSONMarshallingSupport;
import ku.piii.mp3.MP3PathToMusicMapperImpl;
import ku.piii.nio.file.TextFileStoreImpl;

public class MusicServiceFactory {

    private static MusicService instance = null;
    private static Object mutex = new Object();

    private MusicServiceFactory() {
    }

    public static MusicService getMusicServiceInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new MusicServiceImpl(
                            new MusicRepositoryImpl(new JacksonJSONMarshallingSupport(
                                    new ObjectMapper()),
                                    new TextFileStoreImpl()),
                            new MP3PathToMusicMapperImpl()
                    );
                }
            }

        }
        return instance;
    }
}
