package cmd.Auth.service;

import cmd.Auth.Entity.AuthEntity;
import cmd.Auth.repository.AuthRepo;
import cmd.Auth.repository.AuthRepoImpl;

public class AuthService {
    
    private AuthRepo repo = new AuthRepoImpl();

    public boolean login(String name, String password) {
        AuthEntity user = repo.getUserByName(name);
        if (user.getPassword().equals(password)) return true;
        return false;
    }

    public void register(String username, String password) {
        AuthEntity newUser = new AuthEntity(0, username, password);
        repo.addUser(newUser);
    }
}
