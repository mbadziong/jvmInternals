package pl.mbadziong.jvm.jsonTypes;


/**
 * Created by matti on 3/12/16.
 */
public class JavaTypeToJson {

    public Object buildJsonForType(Object obj) {
        Class clazz = obj.getClass();
        IJsonConvertable strategy = null;

        if (TypeChecker.isWrapperType(obj.getClass())) {
            if(TypeChecker.isStringType(clazz)) {
                strategy = new StringType();
            } else if(TypeChecker.isBoolType(clazz)) {
                strategy = new BoolType();
            } else if(TypeChecker.isNumType(clazz)) {
                strategy = new NumType();
            }
        } else {
            if(TypeChecker.isArrayType(clazz)) {
                strategy = new ArrayType();
            } else {
                strategy = new ObjectType();
            }
        }

        return strategy.toJsonString(obj);
    }
}
