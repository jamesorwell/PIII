package ku.piii.marshalling;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;

public class JacksonJSONMarshallerComponentTest {

    @Test
    public void canMarshal() {
        final TestItem testItem = new TestItem();
        testItem.setItemName("testName");
        final MarshallingSupport marshaller = new JacksonJSONMarshallingSupport(new ObjectMapper());
        final String testItemAsJson = marshaller.marshal(testItem);
        MatcherAssert.assertThat(testItemAsJson, Matchers.equalTo("{\"itemName\":\"testName\"}"));
    }

    @Test
    public void canUnMarshal() {
        final String testItemAsJson = "{\"itemName\":\"testName\"}";
        final MarshallingSupport marshaller = new JacksonJSONMarshallingSupport(new ObjectMapper());
        final TestItem testItem = marshaller.unmarshal(testItemAsJson, TestItem.class);
        MatcherAssert.assertThat(testItem.getItemName(), Matchers.equalTo("testName"));
    }
}
