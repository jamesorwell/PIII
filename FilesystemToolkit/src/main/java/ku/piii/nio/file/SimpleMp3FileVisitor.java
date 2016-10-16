/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.nio.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class SimpleMp3FileVisitor extends SimpleFileVisitor<Path> {
    
    private final List<Path> listOfMP3Files;
    
    public SimpleMp3FileVisitor() {
        listOfMP3Files = new ArrayList();
    }
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (MusicFileUtils.isMP3(file)) {
            listOfMP3Files.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
    
    public List<Path> getListOfMP3Files() {
        return listOfMP3Files;
    }
    
}
