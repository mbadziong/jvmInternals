package pl.mbadziong.jvm.jsonTypes;

import java.lang.reflect.Array;

/**
 * Created by matti on 3/12/16.
 * source: http://stackoverflow.com/questions/5606338/cast-primitive-type-array-into-object-array-in-java
 */
public class ArrayType implements IJsonConvertable {
    @Override
    public Object toJsonString(Object obj) {
        final String ArrayPrefix = "[";
        final String ArraySuffix = "]";
        final String Comma = ",";

        boolean firstIteration = true;

        StringBuilder sb = new StringBuilder();
        sb.append(ArrayPrefix);

        for (Object item : this.getArray(obj)) {
            if (firstIteration) {
                firstIteration = false;
            } else {
                sb.append(Comma);
            }

            sb.append(new JavaTypeToJson().buildJsonForType(item));
        }

        sb.append(ArraySuffix);

        return sb.toString();
    }

    private Object[] getArray(Object val) {
        int arrayLength = Array.getLength(val);
        Object[] outputArray = new Object[arrayLength];
        for (int i = 0; i < arrayLength; ++i) {
            outputArray[i] = Array.get(val, i);
        }

        return outputArray;
    }
}
