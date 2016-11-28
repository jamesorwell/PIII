/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.twocollectionsmodel;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.music.MusicService;
import ku.piii.music.MusicServiceFactory;

/**
 *
 * @author ku14009
 */
public class TwoCollectionsModel {

    MusicMediaCollection firstCollection;
    MusicMediaCollection secondCollection;
    MusicService myMusicService
            = MusicServiceFactory.getMusicServiceInstance();

    public TwoCollectionsModel() {
        firstCollection = null;
        secondCollection = null;
    }

    public boolean haveFirstCollection() {
        if (firstCollection == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean haveSecondCollection() {
        if (secondCollection == null) {
            return false;
        } else {
            return true;
        }
    }

    public void clearFirstCollection() {
        firstCollection = null;
    }

    public void loadFirstCollection(String collectionFilename) {
        firstCollection = myMusicService.loadMusicMediaCollection(
                            Paths.get(collectionFilename));
    }

    public void createFirstCollection(String root) {

        firstCollection = myMusicService.createMusicMediaCollection(
                Paths.get(root));
    }

    public void saveFirstCollection(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MusicMediaCollection getFirstCollection() {
        return firstCollection;
    }

    public void clearSecondCollection() {
        secondCollection = null;
    }

    public void loadSecondCollection(String collectionFilename) {
        secondCollection = myMusicService.loadMusicMediaCollection(
                Paths.get(collectionFilename));
    }

    public void createSecondCollection(String root) {
        secondCollection = myMusicService.createMusicMediaCollection(
                Paths.get(root));
    }

    public void saveSecondCollection(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MusicMediaCollection getSecondCollection() {
        return secondCollection;
    }
    
    public void setFirstCollection(List<MusicMedia> thisList)
    {
        firstCollection = new MusicMediaCollection();
        firstCollection.setMusic(thisList);
    }
    public void setSecondCollection(List<MusicMedia> thisList)
    {
        secondCollection = new MusicMediaCollection();        
        secondCollection.setMusic(thisList);
    }
    public void swap()
    {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        
    }
}
