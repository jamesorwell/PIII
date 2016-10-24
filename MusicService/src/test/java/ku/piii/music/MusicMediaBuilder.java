package ku.piii.music;

import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMedia.Id3Version;

public class MusicMediaBuilder {

    private String title;
    private String year;
    private Integer lengthInSeconds;
    private MusicMedia.Id3Version id3Version;
    private String genre;

    public MusicMediaBuilder withTitle(final String title) {
        this.title = title;
        return this;
    }

    public MusicMediaBuilder withYear(String year) {
        this.year = year;
        return this;
    }

    public MusicMediaBuilder withLengthInSeconds(Integer lengthInSeconds) {
        this.lengthInSeconds = lengthInSeconds;
        return this;
    }

    public MusicMediaBuilder withId3Version(final Id3Version id3Version) {
        this.id3Version = id3Version;
        return this;
    }

    public MusicMediaBuilder withGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public MusicMedia build() {
        final MusicMedia musicMedia = new MusicMedia();
        musicMedia.setTitle(title);
        musicMedia.setYear(year);
        musicMedia.setLengthInSeconds(lengthInSeconds);
        musicMedia.setId3Version(id3Version);
        musicMedia.setGenre(genre);
        return musicMedia;
    }

}
