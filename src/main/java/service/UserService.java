package service;

import entities.Permission;
import entities.Person;
import entities.Role;
import entities.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class UserService implements UserServiceInterface {

    private List<User> users;

    public UserService(List<User> users) {
        this.users = users;
    }

    public List<User> findUsersWithAddressesCountMoreThan(final int numberOfAddresses) {
        return this.users
                .stream()
                .filter(user -> user.getPersonDetails().getAddresses().size() > numberOfAddresses)
                .collect(Collectors.toList());
    }

    public Person findOldestPerson() {
        return this.users
                .stream()
                .max(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        if (o1.getPersonDetails().getAge() > o2.getPersonDetails().getAge()) {
                            return 1;
                        } else if (o1.getPersonDetails().getAge() < o2.getPersonDetails().getAge()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                })
                .get()
                .getPersonDetails();
    }

    public User findUserWithLongestUsername() {
        return this.users
                .stream()
                .max(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        if (o1.getName().length() > o2.getName().length()) {
                            return 1;
                        } else if (o1.getName().length() < o2.getName().length()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                })
                .get();
    }

    public String getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18() {
        return this.users
                .stream()
                .map(User::getPersonDetails)
                .filter(person -> person.getAge() >= 18)
                .map(person -> person.getName() + " " + person.getSurname())
                .reduce("", (a, b) -> a + "," + b);
    }

    public List<String> getSortedPermissionsOfUsersWithNameStartingWith(String prefix) {
        return this.users
                .stream()
                .filter(user -> user.getName().startsWith(prefix))
                .map(User::getPersonDetails)
                .map(Person::getRole)
                .map(Role::getPermissions)
                .flatMap(list -> list.stream())
                .map(permission -> permission.getName())
                .sorted()
                .collect(Collectors.toList());

    }

    public double getUsersAverageAge() {
        return this.users
                .stream()
                .map(user -> user.getPersonDetails())
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();
    }

    public void printCapitalizedPermissionNamesOfUsersWithSurnameStartingWith(String suffix) {
        this.users
            .stream()
            .map(User::getPersonDetails)
            .filter(person -> person.getSurname().startsWith(suffix))
            .map(Person::getRole)
            .map(Role::getPermissions)
            .flatMap(list -> list.stream())
            .map(Permission::getName)
            .map(String::toUpperCase)
            .forEach(System.out::println);
    }

    public Map<Role, List<User>> groupUsersByRole() {
        return this.users
                .stream()
                .collect(Collectors.groupingBy(user -> user.getPersonDetails().getRole()));
    }

    public Map<Boolean, List<User>> partitionUserByUnderAndOver18() {
        return this.users
                .stream()
                .collect(Collectors.partitioningBy(user -> user.getPersonDetails().getAge() > 18));
    }
}
