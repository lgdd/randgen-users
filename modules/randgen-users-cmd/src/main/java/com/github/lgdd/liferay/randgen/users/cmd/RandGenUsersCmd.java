package com.github.lgdd.liferay.randgen.users.cmd;

import com.github.lgdd.liferay.randgen.users.api.RandGenUsersApi;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;

/**
 * Gogo Shell command helping generates random users.
 * Usage: {@code randgen:users <optional size>}.
 *
 * @see RandGenUsersApi
 */
@Component(
        immediate = true,
        property = {
                "osgi.command.scope=randgen",
                "osgi.command.function=users"
        },
        service = Object.class
)
public class RandGenUsersCmd {

    /**
     * Generates random users and prints results.
     */
    public void users() {
        RandGenUsersApi generator = _randomUsersGenerator;
        List<User> users = generator.generate();
        printUsers(users);
    }

    /**
     * Generates a specified number of random users and prints results.
     *
     * @param size number of users to generate.
     */
    public void users(int size) {
        RandGenUsersApi generator = _randomUsersGenerator;
        List<User> users = generator.generate(size);
        printUsers(users);
    }

    /**
     * Prints screenName and userId for each user of a list.
     *
     * @param users a list of users.
     */
    private void printUsers(List<User> users) {
        users.forEach(user -> {
            String message =
                    "Added user  " + user.getFullName()
                            + " with screenName='" + user.getScreenName()
                            + "' and userId=" + user.getUserId();
            System.out.println(message);
        });
    }

    @Reference
    private RandGenUsersApi _randomUsersGenerator;

}