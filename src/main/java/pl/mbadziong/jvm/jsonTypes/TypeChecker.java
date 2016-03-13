package pl.mbadziong.jvm.jsonTypes;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by matti on 3/12/16.
 * Source: http://stackoverflow.com/questions/709961/determining-if-an-object-is-of-primitive-type
 */
public class TypeChecker {

    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();
    private static final Set<Class<?>> NUM_TYPES = getNumTypes();
    private static final Set<Class<?>> STR_TYPES = getStrTypes();
    private static final Set<Class<?>> BOOL_TYPES = getBoolTypes();

    public static boolean isWrapperType(Class<?> clazz)
    {
        return WRAPPER_TYPES.contains(clazz);
    }

    public static boolean isNumType(Class<?> clazz) {
        return NUM_TYPES.contains(clazz);
    }

    public static boolean isStringType(Class<?> clazz) {
        return STR_TYPES.contains(clazz);
    }

    public static boolean isBoolType(Class<?> clazz) {
        return BOOL_TYPES.contains(clazz);
    }

    public static boolean isArrayType(Class<?> clazz) {
        return clazz.isArray();
    }

    private static Set<Class<?>> getWrapperTypes()
    {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        ret.add(String.class);
        return ret;
    }

    private static Set<Class<?>> getNumTypes()
    {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        return ret;
    }

    private static Set<Class<?>> getStrTypes()
    {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(String.class);
        ret.add(Character.class);
        return ret;
    }

    private static Set<Class<?>> getBoolTypes() {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        return ret;
    }
}
