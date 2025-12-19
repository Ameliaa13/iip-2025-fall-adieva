package cmd.Auth.repository;

import java.util.ArrayList;

import cmd.Auth.Entity.AuthEntity;

public class AuthRepoImpl implements AuthRepo {

    private ArrayList<AuthEntity> users = new ArrayList<>();
    private int nextId = 0;

    public AuthRepoImpl() {
        users.add(new AuthEntity(nextId++, "Amelia", "2222"));
    }

    @Override
    public void addUser(AuthEntity user) {
        user.setId(nextId++);
        users.add(user);
    }

    @Override
    public AuthEntity getUserByName(String name) {
        for (AuthEntity u : users) {
            if (u.getUsername().equals(name)) return u;
        }

        return null;
    }


    @Override
    public ArrayList<AuthEntity> getAll() {
        return new ArrayList<>(users);
    }

    @Override
    public void removeUserById(long id) {
        users.remove(getUserById(id));
    }

    @Override
    public AuthEntity getUserById(long id) {
        for (AuthEntity u : users) {
            if (u.getId() == id) return u;
        }

        return null;
    }
    
}
