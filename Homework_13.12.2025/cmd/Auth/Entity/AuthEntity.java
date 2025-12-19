package cmd.Auth.Entity;

public class AuthEntity {

    private long id;
    private String username;
    private String password;

    public AuthEntity(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public String getUsername() {
        return username; 
    }
    
    public String getPassword() {
        return password; 
    }

}
