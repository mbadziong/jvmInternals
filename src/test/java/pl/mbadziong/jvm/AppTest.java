package pl.mbadziong.jvm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import pl.mbadziong.jvm.converter.JsonConverter;
import pl.mbadziong.jvm.pojos.Address;
import pl.mbadziong.jvm.pojos.Person;

import java.io.IOException;

public class AppTest
        extends TestCase {

    private final Address address = new Address("Gdansk", "Wita Stwosza", "123A");
    private final Person person = new Person("Andrzej", "Duda", 12, address);

    private JsonConverter jsonConverter = new JsonConverter();
    private ObjectMapper mapper = new ObjectMapper();

    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testJsonIsNotNull() {

        String json = jsonConverter.convert(person);

        Assert.assertNotNull(json);
    }

    public void testMyOwnJsonAndJsonFromCustomLibraryAreEqualInLength() throws JsonProcessingException {
        String myJson = jsonConverter.convert(person);

        String customJson = mapper.writeValueAsString(person);

        Assert.assertEquals(customJson.length(), myJson.length());
    }

    public void testIsPossibleToBuildPojoFromJson() throws IOException, JSONException {
        String myJson = jsonConverter.convert(person);

        String customJson = mapper.writeValueAsString(person);

        Assert.assertNotNull(person);

        JSONAssert.assertEquals(myJson, customJson, false);
    }

    public void testMyOwnJsonAndJsonFromCustomLibraryAreEqual() throws IOException, JSONException {
        String myJson = jsonConverter.convert(person);

        String customJson = mapper.writeValueAsString(person);

        JSONAssert.assertEquals(myJson, customJson, JSONCompareMode.NON_EXTENSIBLE);
    }

    public void testTimeOfExecutionForMyConverterAndCustom() throws JsonProcessingException {
        final long TEST_RUNS = 10000;

        long startTime = System.currentTimeMillis();

        for(int i = 0; i < TEST_RUNS; i++) {
            jsonConverter.convert(person);
        }

        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("Time of execution for " + TEST_RUNS + " runs with my json converter: " + estimatedTime);

        startTime = System.currentTimeMillis();

        for(int i = 0; i < TEST_RUNS; i++) {
            mapper.writeValueAsString(person);
        }

        estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("Time of execution for " + TEST_RUNS + " runs with custom json converter: " + estimatedTime);

        Assert.assertTrue(true);
    }
}
