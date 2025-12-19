package cmd.Auth.repository;

import java.util.ArrayList;

import cmd.Auth.Entity.AuthEntity;

public interface AuthRepo {

    void addUser(AuthEntity user);
    AuthEntity getUserByName(String name);
    AuthEntity getUserById(long id);
    ArrayList<AuthEntity> getAll();
    void removeUserById(long id);


}
