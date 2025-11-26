public class Admin extends Users {

    public Admin(String id, String name, String lastName, String username, String password){
        super(id, name, lastName, username, password, "ADMIN");
    }
}
