package ku.piii.nio.file;

import java.nio.file.Path;

public class MusicFileUtils {
    
    public static boolean isMP3(final Path myFile) {
        
        if (myFile == null) {
            return false;
        }
        
        return myFile.toString().toLowerCase().endsWith(".mp3");
        
    }    
    
    
            
}
