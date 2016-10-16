/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.nio.file;

import static org.junit.Assert.assertTrue;
import static ku.piii.nio.file.MusicFileUtils.isMP3;
import static org.junit.Assert.assertFalse;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class MusicFileUtilsTest {

    public MusicFileUtilsTest() {
    }

    @Test
    public void canIdentifyNullPathAndReturnsFalse() {
        final Path myFile = null;
        assertFalse(isMP3(myFile));
    }

    @Test
    public void canIdentifyLowercaseMP3FileAsMP3File() {
        final Path myFile = Paths.get("test-data/simple-file.mp3");
        assertTrue(isMP3(myFile));
    }

    @Test
    public void canIdentifyUppercaseMP3FileAsMP3File() {
        final Path myFile = Paths.get("test-data/simple-file.MP3");
        assertTrue(isMP3(myFile));
    }

    @Test
    public void canIdentifyMP4FileIsNotMP3File() {
        final Path myFile = Paths.get("test-data/simple-file.mp4");
        assertFalse(isMP3(myFile));
    }

    @Test
    public void canIdentifyVariedCaseMP3FileExtentionAsMP3File() {
        final Path myFile = Paths.get("test-data/simple-file.mP3");
        assertTrue(isMP3(myFile));
    }

    @Test
    public void canIdentifyFileWithMp3InTitleIsNotMP3File() {
        final Path myFile = Paths.get("test-data/simple-file.mp3.txt");
        assertFalse(isMP3(myFile));
    }
}
