package ku.piii.music;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import ku.piii.model.MusicMedia;

public class MusicMediaEquality {

    public final MusicMedia musicMedia;

    public MusicMediaEquality(final MusicMedia musicMedia) {
        this.musicMedia = musicMedia;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(musicMedia.getTitle()).
                                     append(musicMedia.getYear()).toHashCode();
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
        final MusicMediaEquality other = (MusicMediaEquality) obj;
        return new EqualsBuilder()
                .append(musicMedia.getPath(), other.musicMedia.getPath())
                .append(musicMedia.getTitle(), other.musicMedia.getTitle())
                .append(musicMedia.getId3Version(), other.musicMedia.getId3Version())
                .append(musicMedia.getLengthInSeconds(), other.musicMedia.getLengthInSeconds())
                .append(musicMedia.getYear(), other.musicMedia.getYear()).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(musicMedia);
    }
}
