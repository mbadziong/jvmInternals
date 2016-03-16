package pl.mbadziong.jvm.jsonTypes;

/**
 * Created by matti on 3/12/16.
 */
public class NumType implements IJsonConvertable {
    @Override
    public Object toJsonString(Object obj) {
        return obj;
    }
}
