/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.musicbrainztoolkit;

import java.util.ArrayList;
import java.util.List;
import org.musicbrainz.controller.Recording;
import org.musicbrainz.model.entity.RecordingWs2;
import org.musicbrainz.model.entity.ReleaseWs2;
import org.musicbrainz.model.searchresult.RecordingResultWs2;

/**
 *
 * @author James
 */
public class ArtistFinder {

    public List<String> GetArtistsFromTitle(String titleToSearchWith) {
        Recording query = new Recording();
        query.getSearchFilter().setLimit((long) 30);
        query.search(titleToSearchWith);
        List<String> outputList = new ArrayList<String>();
        List<RecordingResultWs2> list_of_recording_results = query.getFirstSearchResultPage();
//        int i = 0;
        for (RecordingResultWs2 recording_result : list_of_recording_results) {
            RecordingWs2 recording = recording_result.getRecording();
            outputList.add(recording.getArtistCreditString());

            /*            System.out.println(i + ": " + recording.getArtistCreditString() + ":");
            i++;
            List<ReleaseWs2> list_of_releases = recording.getReleases();
            int j = 0;
            for (ReleaseWs2 release : list_of_releases) {
                System.out.println("\t: " + release.getDateStr());*/
        }
        return outputList;
    }
}

