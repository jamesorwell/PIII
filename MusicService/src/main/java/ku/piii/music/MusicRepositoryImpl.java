package ku.piii.music;

import java.nio.file.Path;

import ku.piii.marshalling.MarshallingSupport;
import ku.piii.model.MusicMediaCollection;
import ku.piii.nio.file.TextFileStore;

public class MusicRepositoryImpl implements MusicRepository{
	
	private MarshallingSupport marshallingSupport;
	private TextFileStore textFileStore;
	
	public MusicRepositoryImpl(final MarshallingSupport ms, 
                final TextFileStore fileStore){
		this.marshallingSupport = ms;
		this.textFileStore = fileStore;
	}

	@Override
	public MusicMediaCollection loadCollection(final Path file) {
		final String loadText = textFileStore.loadText(file);
		return marshallingSupport.unmarshal(loadText, 
                                                    MusicMediaCollection.class);
	}

	@Override
	public void saveCollection(Path path, 
                                   MusicMediaCollection musicMediaCollection) {
		final String jsonMusicCollection = 
                             marshallingSupport.marshal(musicMediaCollection);
		textFileStore.saveText(jsonMusicCollection, path);
	}

}
