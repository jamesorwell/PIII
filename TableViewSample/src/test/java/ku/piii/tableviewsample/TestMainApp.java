/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.tableviewsample;
//import org.testfx.framework.junit.ApplicationTest;

//import static org.testfx.api.FxAssert.verifyThat;
//import static org.testfx.service.query.impl.NodeQueryUtils.hasText;
/**
 *
 * @author James
 
public class TestMainApp  extends ApplicationTest {
    @Override public void start(Stage stage) {
        Parent sceneRoot = new ClickApplication.ClickPane();
        Scene scene = new Scene(sceneRoot, 100, 100);
        stage.setScene(scene);
        stage.show();
    }

    @Test public void should_contain_button() {
        // expect:
        verifyThat(".button", hasText("click me!"));
    }

    @Test public void should_click_on_button() {
        // when:
        clickOn(".button");

        // then:
        verifyThat(".button", hasText("clicked!"));
    }
}
*/