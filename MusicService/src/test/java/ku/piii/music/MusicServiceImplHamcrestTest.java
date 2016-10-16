package ku.piii.music;

import static java.nio.file.Paths.get;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import ku.piii.marshalling.JacksonJSONMarshallingSupport;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.mp3.MP3PathToMusicMapperImpl;
import ku.piii.nio.file.TextFileStoreImpl;

public class MusicServiceImplHamcrestTest {

	@Test
	public void canCreateMusicCollectionFromPath() {
		final MusicService musicService = createMusicServiceInstance();
		final MusicMediaCollection collectionA = musicService
				.createMusicMediaCollection(Paths.get("../test-music-files/collection-A"));

		assertEquals(9, collectionA.getMusic().size());

		final MusicMediaCollection collectionB = musicService
				.createMusicMediaCollection(Paths.get("../test-music-files/collection-B"));

		final MusicMediaCollection merge = collectionA.mergeCollection(collectionB);
		assertEquals(18, merge.getMusic().size());
	}

	@Test
	public void canAddFilesThenSaveThenLoad() {

		final String pathToAddFrom = "../test-music-files/Collection-B";
		final String jsonFileToSaveToAndLoadFrom = "../test-json-files/Collection-B.json";

		final MusicService musicService = createMusicServiceInstance();
		final MusicMediaCollection collection = musicService.createMusicMediaCollection(Paths.get(pathToAddFrom));

		assertThat(collection, Matchers.notNullValue());

		musicService.saveMusicMediaCollection(get(jsonFileToSaveToAndLoadFrom), collection);
		final MusicMediaCollection savedCollection = musicService
				.loadMusicMediaCollection(get(jsonFileToSaveToAndLoadFrom));

		assertEquals(collection.getMusic().size(), savedCollection.getMusic().size());
		assertThat(savedCollection, Matchers.notNullValue());
		assertThat(savedCollection.getMusic(), Matchers.hasSize(collection.getMusic().size()));

		final List<MusicMediaEquality> expectedMusic = collection.getMusic().stream().map(MusicMediaEquality::new)
				.collect(Collectors.toList());

		final List<MusicMediaEquality> actualMusic = savedCollection.getMusic().stream().map(MusicMediaEquality::new)
				.collect(Collectors.toList());

		assertThat(actualMusic, containsInAnyOrder(expectedMusic.toArray()));
	}

	@Test
	public void canLoadThenSaveThenLoad() {
		final String jsonFileToLoadFrom = "../test-json-files/Collection-A.json";
		final String jsonFileToSaveTo = "../test-json-files/tmp2.json";

		final MusicService musicService = createMusicServiceInstance();

		final MusicMediaCollection collectionA = musicService.loadMusicMediaCollection(get(jsonFileToLoadFrom));
		musicService.saveMusicMediaCollection(get(jsonFileToSaveTo), collectionA);

		final MusicMediaCollection copyOfCollectionA = musicService.loadMusicMediaCollection(get(jsonFileToSaveTo));

		final List<MusicMediaEquality> expectedMusic = collectionA.getMusic().stream().map(MusicMediaEquality::new)
				.collect(Collectors.toList());

		final List<MusicMediaEquality> actualMusic = copyOfCollectionA.getMusic().stream().map(MusicMediaEquality::new)
				.collect(Collectors.toList());

		assertThat(actualMusic, containsInAnyOrder(expectedMusic.toArray()));
	}

	public static class MusicMediaEquality {
		public final MusicMedia musicMedia;

		public MusicMediaEquality(final MusicMedia musicMedia) {
			this.musicMedia = musicMedia;
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder().append(musicMedia.getAbsolutePath()).append(musicMedia.getArtist())
					.append(musicMedia.getTitle()).append(musicMedia.getYear()).toHashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj.getClass() != getClass()) {
				return false;
			}
			MusicMediaEquality other = (MusicMediaEquality) obj;
			return new EqualsBuilder()
					.append(musicMedia.getAbsolutePath(), other.musicMedia.getAbsolutePath())
					.append(musicMedia.getArtist(), other.musicMedia.getArtist())
					.append(musicMedia.getTitle(), other.musicMedia.getTitle())
					.append(musicMedia.getYear(), other.musicMedia.getYear()).isEquals();
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(musicMedia);
		}
	}

	private MusicService createMusicServiceInstance() {
		final MusicService instance = new MusicServiceImpl(
				new MusicRepositoryImpl(new JacksonJSONMarshallingSupport(new ObjectMapper()), new TextFileStoreImpl()),
				new MP3PathToMusicMapperImpl());
		return instance;
	}

}
