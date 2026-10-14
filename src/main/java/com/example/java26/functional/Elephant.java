package com.example.java26.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class Elephant {

    static void main() {

        List<String> userIds = List.of("user1", "user2", "user3");

        //Find all emails for users
        var emails = userIds.stream()
                .map(userId -> findEmail(userId))
                .flatMap(Optional::stream)
                .collect(Collectors.joining(";"));
        IO.println(emails);

        userIds.stream()
                .map(Elephant::findAllEmails)
                .flatMap(List::stream)
                .forEach(IO::println);


        List<User> users = new ArrayList<>();
        users.add(new User("Martin", true));
        users.add(new User("Kalle", false));
        users.add(null);

        users.stream()
                .map(Optional::ofNullable)
                .flatMap(Optional::stream)
                .map(User::name)
                .forEach(IO::println);

        IO.println();
        Random random = new Random();

        User user = new User("Martin", true);
        String displayName = Optional.ofNullable(user.name())
                .orElseGet(()->"Guest" + random.nextInt(0,100));
        IO.println(displayName);
    }

    record User(String name, boolean enabled) {
    }

    static Optional<String> findEmail(String userId) {
        if (userId.equals("user2")) {
            return Optional.empty();
        }
        return Optional.of(userId + "@company.com");
    }

    static List<String> findAllEmails(String userId) {
        if (userId.equals("user1")) {
            return List.of(userId + "@company.com", "hotBaby@hotmail.com");
        }
        if (userId.equals("user2")) {
            return List.of();
        }
        return List.of(userId + "@company.com");
    }
}
