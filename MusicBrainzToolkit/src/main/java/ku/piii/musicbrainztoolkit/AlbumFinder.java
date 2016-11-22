/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.musicbrainztoolkit;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.controller.Artist;
import org.musicbrainz.filter.searchfilter.ArtistSearchFilterWs2;
import org.musicbrainz.model.entity.ArtistWs2;
import org.musicbrainz.model.entity.ReleaseWs2;
import org.musicbrainz.model.searchresult.ArtistResultWs2;
import org.musicbrainz.query.search.ArtistSearchWs2;

/**
 *
 * @author James
 */
public class AlbumFinder {

    String getAlbumFromArtistAndTrack(
                String artistNameToSearchFor,
                String trackNameToSearchFor) {
        
        ArtistSearchFilterWs2 filter = new ArtistSearchFilterWs2();

        filter.setArtistName(artistNameToSearchFor);
        filter.setLimit((long) 100);
        filter.setMinScore((long) 0);
        filter.setOffset((long) 0);

        ArtistSearchWs2 search = new ArtistSearchWs2(filter);
        List<ArtistResultWs2> result = search.getFullList();

        String artistName = result.get(0).getArtist().getName();
        System.out.println(artistName);

// This is the artist as returned by the search server.
        ArtistWs2 artistRetrieved = result.get(0).getArtist();

        Artist artistController = new Artist();
// This is where you tell the library that you want to have the artists
// releases included in the result.
        artistController.getIncludes().setReleases(true);

// This is the artist as returned by the MusicBrainz server, including the
// release information.
        ArtistWs2 artistWithRelease;
        try {
            artistWithRelease = artistController.lookUp(artistRetrieved);
            List<ReleaseWs2> releases = artistWithRelease.getReleaseList().getReleases();
            System.out.println(releases.size());

            for (ReleaseWs2 release : releases) {
                System.out.println(release.getTitle());
            }
        } catch (MBWS2Exception ex) {
            //Logger.getLogger(this.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
