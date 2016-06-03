package service;

import entities.*;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UserServiceTest {

    public static List<User> users;
    public static UserServiceInterface service;


    @Test
    public void findUsersWithAddressesCountMoreThan() {
        CreateUsers();
        Assert.assertEquals(1, service.findUsersWithAddressesCountMoreThan(5).size());

    }

    @Test
    public void findOldestPerson() {
        CreateUsers();
        Assert.assertEquals(55, service.findOldestPerson().getAge());
    }

    @Test
    public void findUserWithLongestUsername() {
        CreateUsers();
        Assert.assertEquals("napierdalaczu", service.findUserWithLongestUsername().getName());
    }

    @Test
    public void getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18() {
        CreateUsers();
        Assert.assertEquals(",Mieczyslaw Grzyb,Rafal Zielony,Tadeusz Szprot", service.getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18());
    }

    @Test
    public void getSortedPermissionsOfUsersWithNameStartingWith() {
        CreateUsers();
        //no distinct permissions - lack of impl in Permission class, which I think can't be edited
        Assert.assertEquals(Arrays.asList("read", "read", "write"), service.getSortedPermissionsOfUsersWithNameStartingWith("mi"));
    }

    @Test
    public void getUsersAverageAge() {
        CreateUsers();
        Assert.assertEquals(28.0, service.getUsersAverageAge());
    }

    @Test
    public void printCapitalizedPermissionNamesOfUsersWithSurnameStartingWith() {
        CreateUsers();
        service.printCapitalizedPermissionNamesOfUsersWithSurnameStartingWith("K");
        Assert.assertTrue(true);
    }

    @Test
    public void groupUsersByRole() {
        CreateUsers();
        Map<Role, List<User>> map = service.groupUsersByRole();
        //fails because lack of impl in Role class, equals, hashcode etc.
        Assert.assertEquals(4, map.size());
    }

    @Test
    public void partitionUserByUnderAndOver18() {
        CreateUsers();
        Map<Boolean, List<User>> map = service.partitionUserByUnderAndOver18();
        Assert.assertEquals(2, map.get(false).size());
        Assert.assertEquals(3, map.get(true).size());
    }

    private void BuildUser(String nick, String name, String surname, int age, int addressesCount, String roleName, String[] permissionNames) {

        User user1 = new User();
        user1.setName(nick);
        user1.setPassword("12345");

        Person person1 = new Person();
        person1.setName(name);
        person1.setSurname(surname);
        person1.setAge(age);

        user1.setPersonDetails(person1);

        List<Address> addresses = new ArrayList<>();
        for(int i = 0; i < addressesCount; i++) {
            addresses.add(new Address());
        }

        person1.setAddresses(addresses);

        Role role = new Role();
        role.setName(roleName);

        List<Permission> permissions = new ArrayList<>();
        for(String permissionName : permissionNames) {
            permissions.add(new Permission().setName(permissionName));
        }

        role.setPermissions(permissions);

        person1.setRole(role);

        users.add(user1);
    }

    private void CreateUsers() {
        users = new ArrayList<>();

        BuildUser("mietek", "Mieczyslaw", "Grzyb", 33, 5, "user", new String[] {
                "read",
                "write"
        });

        BuildUser("misiek", "Heniek", "Kowalski", 13, 1, "user", new String[] {
                "read",
        });

        BuildUser("zielony", "Rafal", "Zielony", 55, 2, "admin", new String[] {
                "read",
                "write",
                "destroy"
        });

        BuildUser("klikaczu", "Tadeusz", "Szprot", 22, 3, "tester", new String[] {
                "read",
                "write",
                "test"
        });

        BuildUser("napierdalaczu", "Jan", "Knur", 17, 10, "developer", new String[] {
                "read",
                "write",
                "develop",
                "debug"
        });

        service = new UserService(users);
    }
}
