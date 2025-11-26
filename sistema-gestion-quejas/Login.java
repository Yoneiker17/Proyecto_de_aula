import java.util.ArrayList;
import java.util.List;

public class Login {

    private List<Users> users;

    public Login() {
        users = new ArrayList<>();
    }

    public void addUser(Users user) {
        users.add(user);
    }

    public Users authenticate(String username, String password) {
        for (Users user : users) {
            if (user.login(username, password)) {
                return user;
            }
        }
        return null;
    }

    public List<Tech> getAllTechs() {
        List<Tech> list = new ArrayList<>();
        for (Users u : users) {
            if (u instanceof Tech)
                list.add((Tech) u);
        }
        return list;
    }
}
