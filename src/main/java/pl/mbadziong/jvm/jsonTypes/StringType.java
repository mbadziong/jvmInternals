package pl.mbadziong.jvm.jsonTypes;

/**
 * Created by matti on 3/12/16.
 */
public class StringType implements IJsonable {
    @Override
    public String toJsonString(Object obj) {
        final String Quote = "\"";

        String value = obj.toString();

        StringBuffer sb = new StringBuffer();
        sb.append(Quote);
        sb.append(value);
        sb.append(Quote);

        return sb.toString();
    }
}
