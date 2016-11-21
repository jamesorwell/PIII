/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.twocollectionsinconsole;

import java.io.File;
import java.nio.file.Paths;
import ku.piii.model.MusicMediaCollection;
import ku.piii.music.MusicService;
import ku.piii.music.MusicServiceFactory;

/**
 *
 * @author ku14009
 */
class ExampleModel {

    MusicMediaCollection firstCollection;
    MusicMediaCollection secondCollection;
    MusicService myMusicService
            = MusicServiceFactory.getMusicServiceInstance();

    public ExampleModel() {
        firstCollection = null;
        secondCollection = null;
    }

    boolean haveFirstCollection() {
        if (firstCollection == null) {
            return false;
        } else {
            return true;
        }
    }

    boolean haveSecondCollection() {
        if (secondCollection == null) {
            return false;
        } else {
            return true;
        }
    }

    void clearFirstCollection() {
        firstCollection = null;
    }

    void loadFirstCollection(String collectionFilename) {
        firstCollection = myMusicService.loadMusicMediaCollection(
                            Paths.get(collectionFilename));
    }

    void createFirstCollection(String root) {

        firstCollection = myMusicService.createMusicMediaCollection(
                Paths.get(root));
    }

    void saveFirstCollection(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    MusicMediaCollection getFirstCollection() {
        return firstCollection;
    }

    void clearSecondCollection() {
        secondCollection = null;
    }

    void loadSecondCollection(String collectionFilename) {
        secondCollection = myMusicService.loadMusicMediaCollection(
                Paths.get(collectionFilename));
    }

    void createSecondCollection(String root) {
        secondCollection = myMusicService.createMusicMediaCollection(
                Paths.get(root));
    }

    void saveSecondCollection(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    MusicMediaCollection getSecondCollection() {
        return secondCollection;
    }
}
