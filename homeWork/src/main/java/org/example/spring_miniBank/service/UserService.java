package org.example.spring_miniBank.service;

import org.example.spring_miniBank.model.Account;
import org.example.spring_miniBank.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private int idCounter;
    private final AccountService accountService;
    private final Map<Integer, User> usersMap;
    private final Set<String> takeLogins;


    @Autowired
    public UserService(AccountService accountService) {
        this.idCounter = 0;
        this.accountService = accountService;
        this.usersMap = new HashMap<>();
        this.takeLogins = new HashSet<>();
    }


    public User userCreate(String userLogin) {
        String login = validateUserLogin(userLogin);

        if (takeLogins.contains(login)) {
            throw new IllegalArgumentException("user with login " + login + " already exists");
        }

        idCounter++;
        User user = new User(idCounter, login, new ArrayList<>());

        Account defaultAccount = accountService.createAccount(user);
        user.getAccountList().add(defaultAccount);

        usersMap.put(idCounter, user);
        takeLogins.add(login);

        return user;
    }


    public User findUserById(Integer userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("user id must be > 0");
        }

        User user = usersMap.get(userId);

        if (user == null) {
            throw new IllegalArgumentException("user not found");
        }

        return user;
    }


    public List<User> showAllUsers() {
        return usersMap.values().stream().toList();
    }


    private String validateUserLogin(String userLogin) {
        if (userLogin == null || userLogin.isEmpty()) {
            throw new IllegalArgumentException("userLogin is null or empty");
        }
        return userLogin.trim();
    }

}
