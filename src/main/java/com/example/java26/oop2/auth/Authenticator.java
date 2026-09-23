package com.example.java26.oop2.auth;

import java.io.Serializable;

public interface Authenticator {
   boolean supports(String authType);

   boolean authenticate(String userName, String secret);

}

class PasswordAuthenticator implements Authenticator {
    @Override
    public boolean supports(String authType) {
        return authType.equalsIgnoreCase("password");
    }

    @Override
    public boolean authenticate(String userName, String secret) {
        return secret.equals("1234");
    }
}

class UserNameAndPasswordAuthenticator implements Authenticator {
    @Override
    public boolean supports(String authType) {
        return authType.equalsIgnoreCase("password");
    }

    @Override
    public boolean authenticate(String userName, String secret) {
        return userName.equals("Martin") && secret.equals("1234");
    }
}

class LoginSystem {
    static void main() {
        Authenticator auth = new PasswordAuthenticator();
        if (auth.supports("password")) {
            String userName = IO.readln("Username: ");
            String password = IO.readln("Password: ");
            if (auth.authenticate(userName, password)) {
                IO.println("Welcome " + userName);
            } else {
                IO.println("Invalid username or password");
            }
        }
    }
}
