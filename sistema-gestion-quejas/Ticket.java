public class Ticket {

    private String ticketId;
    private String guestName;
    private String description;
    private String department;
    private Tech assignedTech;
    private String status;

    public Ticket(String ticketId, String guestName, String description, String department, Tech assignedTech) {
        this.ticketId = ticketId;
        this.guestName = guestName;
        this.description = description;
        this.department = department;
        this.assignedTech = assignedTech;
        this.status = "PENDING";
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getDescription() {
        return description;
    }

    public String getDepartment() {
        return department;
    }

    public Tech getAssignedTech() {
        return assignedTech;
    }

    public String getStatus() {
        return status;
    }

    public void setAssignedTech(Tech t) {
        assignedTech = t;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
