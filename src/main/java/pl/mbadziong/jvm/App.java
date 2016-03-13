package pl.mbadziong.jvm;

import pl.mbadziong.jvm.converter.JsonConverter;
import pl.mbadziong.jvm.pojos.Address;
import pl.mbadziong.jvm.pojos.Person;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

        Address address = new Address("Gdansk", "Wita Stwosza", "123A");
        Person person = new Person("andrzej", "dupa", 12, address);
        Class personClazz = person.getClass();

        JsonConverter jsonConverter = new JsonConverter();

        String json = jsonConverter.convert(person);
        System.out.println(json);
    }
}
