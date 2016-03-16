package pl.mbadziong.jvm.jsonTypes;

import pl.mbadziong.jvm.converter.JsonConverter;

/**
 * Created by matti on 3/12/16.
 */
public class ObjectType implements IJsonConvertable {
    @Override
    public Object toJsonString(Object obj) {
        return new JsonConverter().convert(obj);
    }
}
