package com.github.lgdd.liferay.randgen.users.cmd;

import com.github.lgdd.liferay.randgen.users.api.RandGenUsersApi;
import com.liferay.portal.kernel.model.User;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;

@Component(
        immediate = true,
        property = {
                "osgi.command.scope=randgen",
                "osgi.command.function=users"
        },
        service = Object.class
)
public class RandGenUsersCmd {

    public void users() {
        RandGenUsersApi generator = _randomUsersGenerator;
        List<User> users = generator.generate();
        printUsers(users);
    }

    public void users(int size) {
        RandGenUsersApi generator = _randomUsersGenerator;
        List<User> users = generator.generate(size);
        printUsers(users);
    }

    private void printUsers(List<User> users) {
        users.stream()
                .forEach(user -> {
                    String message =
                            "Added user  " + user.getFullName() + " with screenName='" + user.getScreenName() + "' and userId=" + user.getUserId();
                    System.out.println(message);
                });
    }

    @Reference
    private RandGenUsersApi _randomUsersGenerator;

}