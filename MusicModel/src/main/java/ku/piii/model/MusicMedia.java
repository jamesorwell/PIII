package ku.piii.model;

public class MusicMedia {

    private String absolutePath;
    private String title, artist, year;

    public MusicMedia() {
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Deprecated
    public String getFileNameAndPath() {
        return getAbsolutePath();
    }

    @Deprecated
    public void setFileNameAndPath(final String fileNameAndPath) {
        setAbsolutePath(fileNameAndPath);
    }

}
