package ku.piii.model;

public class MusicMedia {

    public enum Id3Version {
        V1, V2
    }
    private String path;
    private Integer lengthInSeconds;
    
    private Id3Version id3Version;
    // retrieves from the ID tag:
    private String title;
    private String year;
    private String genre;

    public MusicMedia() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String thisPath) {
        this.path = thisPath;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getLengthInSeconds() {
        return lengthInSeconds;
    }

    public void setLengthInSeconds(Integer lengthInSeconds) {
        this.lengthInSeconds = lengthInSeconds;
    }

    public Id3Version getId3Version() {
        return id3Version;
    }

    public void setId3Version(Id3Version id3Version) {
        this.id3Version = id3Version;
    }

}
