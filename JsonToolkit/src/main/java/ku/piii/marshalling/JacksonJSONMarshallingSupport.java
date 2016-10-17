package ku.piii.marshalling;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JacksonJSONMarshallingSupport implements MarshallingSupport {
    
    private final ObjectMapper mapper;
    
    public JacksonJSONMarshallingSupport(ObjectMapper mapper) {
       this.mapper = mapper;
       this.mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
    }

    @Override
    public String marshal(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> T unmarshal(String s, Class<T> t) {
        try {
            T newObj = mapper.readValue(s, t);
            return newObj;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
