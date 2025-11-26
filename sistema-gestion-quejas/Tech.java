public class Tech extends Users {

    private String department;

    public Tech(String id, String name, String lastName, String username, String password, String department) {
        super(id, name, lastName, username, password, "TECH");
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

}
