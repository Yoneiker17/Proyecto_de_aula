public class Guest extends Users {

    private int roomNumber;
    //CONSTRUCTOR
    public Guest(String id, String name, String lastName, String username, String password, int roomNumber){
        super(id, name, lastName, username, password, "GUEST");
        this.roomNumber = roomNumber;
    }
    //METODO
    public int getRoomNumber() {
        return roomNumber;
    }
}
