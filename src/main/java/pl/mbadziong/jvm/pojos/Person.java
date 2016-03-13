package pl.mbadziong.jvm.pojos;

import pl.mbadziong.jvm.pojos.Address;

/**
 * Created by matti on 3/12/16.
 */
public class Person {
    private String name;
    public String surname;
    public int[] numbers;
    private int age;
    private Address address;

    public Person() {
    }

    public Person(String name, String surname, int age, Address address, int[] numbers) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.numbers = new int[]{1,2,3};
        this.address = address;
    }

    public Person(String name, String surname, int age, Address address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.numbers = new int[]{1,2,3};
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void doSth() {

    }
}
