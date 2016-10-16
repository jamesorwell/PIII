/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.nio.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author user
 */
public class TextFileStoreImpl implements TextFileStore {

    @Override
    public void saveText(String text, Path location) {
        try {
            Files.write(location, text.getBytes());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String loadText(Path location) {
        try {
            return new String(Files.readAllBytes(location));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
