package pl.mbadziong.jvm.converter;

import fj.data.Array;
import pl.mbadziong.jvm.jsonTypes.JavaTypeToJson;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static fj.data.Array.array;

/**
 * Created by matti on 3/12/16.
 */
public class JsonConverter {

    private final String GetterPrefix = "get";
    private final String BooleanGetterPrefix = "is";

    private Array getGetters(Class<?> clazz) {
        Array allMethods = array(clazz.getMethods());
        Array getters = allMethods
                .filter(method -> isGetter(method))
                .filter(method -> notGetClass(method));

        return getters;
    }

    private Map<String, Object> getPropertiesAndItsValues(Object pojo, Array properties) {
        Map propertiesAndValues = new HashMap();

        for (Object property : properties) {
            Object value = null;
            try {
                value = ((Field) property).get(pojo);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            propertiesAndValues.put(((Field) property).getName(), value);
        }

        return propertiesAndValues;
    }

    private Map<String, Object> getGettersAndItsValues(Object pojo, Array getters) {
        Map gettersAndValues = new HashMap();

        for (Object getter : getters) {
            Object value = null;

            try {
                value = ((Method) getter).invoke(pojo);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            gettersAndValues.put(((Method) getter).getName(), value);
        }

        return gettersAndValues;
    }

    private Array getProperties(Class<?> clazz) {
        Array allProperties = array(clazz.getFields());
        Array publicProperties = allProperties
                .filter(property -> isPublicProperty(property));

        return publicProperties;
    }

    public String convert(Object pojo) {
        Array getters = this.getGetters(pojo.getClass());
        Array publicProperties = this.getProperties(pojo.getClass());

        Map<String, Object> propertiesMap = this.getPropertiesAndItsValues(pojo, publicProperties);
        Map<String, Object> gettersMap = this.getGettersAndItsValues(pojo, getters);

        gettersMap = this.retrievePropertyNamesFromGetters(gettersMap);

        Map merged = new HashMap();
        merged.putAll(propertiesMap);
        merged.putAll(gettersMap);

        return this.buildJson(merged);
    }

    private String buildJson(Map<String, Object> objectParams) {
        final String JsonPrefix = "{";
        final String JsonSuffix = "}";
        final String Colon = ":";
        final String Comma = ",";
        final String Quote = "\"";

        boolean firstIteration = true;

        StringBuffer sb = new StringBuffer();
        sb.append(JsonPrefix);

        for (Map.Entry<String, Object> entry : objectParams.entrySet()) {
            if (firstIteration) {
                firstIteration = false;
            } else {
                sb.append(Comma);
            }

            sb.append(Quote);
            sb.append(entry.getKey());
            sb.append(Quote);
            sb.append(Colon);
            sb.append(new JavaTypeToJson().buildJsonForType(entry.getValue()));
        }

        sb.append(JsonSuffix);

        return sb.toString();
    }

    private boolean isGetter(Object method) {
        Method currentMethod = ((Method) method);
        int modifiers = currentMethod.getModifiers();

        if (Modifier.isStatic(modifiers) || Modifier.isPrivate(modifiers)) {
            return false;
        }

        String methodName = currentMethod.getName().toLowerCase();

        return methodName.startsWith(GetterPrefix) ||
                methodName.startsWith(BooleanGetterPrefix);
    }

    private boolean notGetClass(Object method) {
        final String GetClassMethod = "getClass";

        Method currentMethod = ((Method) method);
        String methodName = currentMethod.getName();

        return !methodName.equals(GetClassMethod);
    }

    private boolean isPublicProperty(Object obj) {
        Field field = (Field) obj;
        int modifiers = field.getModifiers();

        return Modifier.isPublic(modifiers);
    }

    private Map<String, Object> retrievePropertyNamesFromGetters(Map<String, Object> getters) {
        Map<String, Object> changedGetters = new HashMap<>();

        for (Map.Entry<String, Object> entry : getters.entrySet()) {
            String name = entry.getKey();
            String result;

            if(entry.getKey().startsWith(GetterPrefix)) {
                 result = name.replaceFirst(Pattern.quote(GetterPrefix), "");
            } else {
                result = name.replaceFirst(Pattern.quote(BooleanGetterPrefix), "");
            }

            Object currentEntryValue = entry.getValue();
            changedGetters.put(this.firstCharToLowerCase(result), currentEntryValue);
        }

        return changedGetters;
    }

    private String firstCharToLowerCase(String propertyName) {
        return propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1,propertyName.length());
    }
}
