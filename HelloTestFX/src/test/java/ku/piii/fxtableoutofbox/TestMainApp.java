package ku.piii.fxtableoutofbox;

import java.io.IOException;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

@SuppressWarnings("restriction")
public class TestMainApp  extends GuiTest {
		
	@Override
	protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            return parent;
        } catch (IOException ex) {
            // TODO ...
        }
        return parent;
	}
	
    @Test
    public void clickButton() {        
    	final Button button = (Button)find("#button");
    	click(button);
    }	

}
