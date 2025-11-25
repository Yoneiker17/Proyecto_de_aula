public class Tech extends Users {

    private String department;
    //CONSTRUCTOR
    public Tech(String id, String name, String lastName, String username, String password, String department){
        super(id, name, lastName, username, password, "TECH");
        this.department = department;
    }
    //METODO
    public String getDepartment() {
        return department;
    }
}
