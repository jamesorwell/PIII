/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.nio.file;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.hamcrest.Matchers;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author user
 */
public class TextFileStoreImplComponentTest {

    @Test
    public void canSaveAndRetrieveTextFromToPath() {
        final String text = "This is some text";
        final Path path = Paths.get("text.txt");
        final TextFileStore fileStore = new TextFileStoreImpl();
        fileStore.saveText(text, path);
        final String loadedText = fileStore.loadText(path);
        assertThat(loadedText, Matchers.equalTo(text));
    }

}
