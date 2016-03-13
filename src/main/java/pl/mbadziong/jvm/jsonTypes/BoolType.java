package pl.mbadziong.jvm.jsonTypes;

/**
 * Created by matti on 3/12/16.
 */
public class BoolType implements IJsonable {
    @Override
    public String toJsonString(Object obj) {
        boolean value = ((Boolean) obj);

        return value ? "True" : "False";
    }
}
