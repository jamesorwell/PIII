/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.nio.file;

import java.nio.file.Path;

/**
 *
 * @author user
 */
public interface TextFileStore {
    
    public void saveText(final String text, final Path location);
    
    public String loadText(final Path location);
    
}
