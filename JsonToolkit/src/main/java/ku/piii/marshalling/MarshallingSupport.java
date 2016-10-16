package ku.piii.marshalling;

public interface MarshallingSupport {
    public String marshal(Object object);
    public <T> T unmarshal(String s, Class<T> t);
}
