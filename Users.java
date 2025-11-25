public class Users {
    private String id;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String role;

    //CONTRUCTOR
    public Users(String id, String name, String lastName, String username, String password, String role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //GETTER & SETTER
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    //METODO
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
