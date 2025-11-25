import java.util.ArrayList;
import java.util.List;

public class Ticketsystem {

    private List<Ticket> tickets;
    private Login login;

    public Ticketsystem(Login login) {
        this.login = login;
        tickets = new ArrayList<>();
    }

    public Ticket createTicket(Guest g, String description, String department) {

        Tech assignedTech = searchTechByDepartment(department);

        String id = "T" + (tickets.size() + 1);

        Ticket t = new Ticket(id, g.getName(), description, department, assignedTech);

        tickets.add(t);
        return t;
    }

    public Tech searchTechByDepartment(String dept) {
        for (Tech t : login.getAllTechs()) {
            if (t.getDepartment().equalsIgnoreCase(dept)) {
                return t;
            }
        }
        return null;
    }

    public void viewAllTickets() {
        int i = 0;
        for (Ticket t : tickets) {
            System.out.println(t.getTicketId() + " - " + t.getDescription() + " - " + t.getStatus());
            i += 1;
        }

        if (i == 0) {
            System.out.println("There's no tickets");
        }
    }

    public void viewTicketsByTech(Tech tech) {
        int i = 0;
        for (Ticket t : tickets) {
            if (t.getAssignedTech().equals(tech)) {
                System.out.println(t.getTicketId() + " - " + t.getDescription() + " - " + t.getStatus());
                i += 1;
            }

            if (i == 0) {
                System.out.println("There's no tickets");
            }
        }
    }

    public void viewTicketsByGuest(Guest g) {
        int i = 0;
        for (Ticket t : tickets) {
            if (t.getGuestName().equals(g.getName())) {
                System.out.println(t.getTicketId() + " - " + t.getDescription() + " - " + t.getStatus());
                i += 1;
            }

            if (i == 0) {
                System.out.println("There's no tickets");
            }
        }
    }

    public void changeStatus(String id, String status) {
        for (Ticket t : tickets) {
            if (t.getTicketId().equals(id)) {
                t.setStatus(status);
                System.out.println("Status updated");
                return;
            }
        }
        System.out.println("Ticket not found");
    }
}
