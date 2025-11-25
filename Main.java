import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Login login = new Login();
        Ticketsystem system = new Ticketsystem(login);

        // ---- Example users ----
        login.addUser(new Admin("A1", "Carlos", "Lopez", "admin", "1234"));

        Tech t1 = new Tech("T1", "Miguel", "Torres", "tech1", "1111", "Maintenance");
        Tech t2 = new Tech("T2", "Luisa", "Gomez", "tech2", "2222", "Housekeeping");
        Tech t3 = new Tech("T3", "Ana", "Martinez", "tech3", "3333", "Systems");

        login.addUser(t1);
        login.addUser(t2);
        login.addUser(t3);

        login.addUser(new Guest("G1", "Yoneiker", "Pineda", "guest", "abcd", 204));

        while (true) {

            System.out.println("\n=== COMPLAINT MANAGEMENT SYSTEM ===");
            System.out.print("Username: ");
            String user = sc.nextLine();
            System.out.print("Password:");
            String pass = sc.nextLine();
            System.out.print("------------------------------");

            Users u = login.authenticate(user, pass);

            if (u == null) {
                System.out.println("Wrong username or password.");
                continue;
            }

            System.out.println("\nWelcome " + u.getName() + " (" + u.getRole() + ")");

            // ----------- ADMIN -----------
            if (u.getRole().equals("ADMIN")) {
                String option;
                do {
                    System.out.println("\n--- ADMIN MENU ---");
                    System.out.println("1. View all tickets");
                    System.out.println("2. View technicians");
                    System.out.println("3. Logout");
                    System.out.print("Choose an option: ");
                    option = sc.nextLine();

                    switch (option) {
                        case "1":
                            system.viewAllTickets();
                            break;

                        case "2":
                            for (Tech t : login.getAllTechs()) {
                                System.out.println(t.getName() + " - " + t.getDepartment());
                            }
                            break;

                        case "3":
                            System.out.println("Logging out...");
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }

                } while (option != "3");
            }

            // ----------- TECHNICIAN -----------
            else if (u.getRole().equals("TECH")) {

                Tech tech = (Tech) u;
                int option;

                do {
                    System.out.println("\n--- TECHNICIAN MENU ---");
                    System.out.println("1. View assigned tickets");
                    System.out.println("2. Change ticket status");
                    System.out.println("3. Logout");
                    System.out.print("Option: ");
                    option = Integer.parseInt(sc.nextLine());

                    switch (option) {
                        case 1:
                            system.viewTicketsByTech(tech);
                            break;

                        case 2:
                            System.out.print("Ticket ID: ");
                            String id = sc.nextLine();
                            System.out.print("New status (IN_PROGRESS / RESOLVED): ");
                            String status = sc.nextLine();
                            system.changeStatus(id, status);
                            break;

                        case 3:
                            System.out.println("Logging out...");
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }

                } while (option != 3);
            }

            // ----------- GUEST -----------
            else if (u.getRole().equals("GUEST")) {

                Guest g = (Guest) u;
                int option;

                do {
                    System.out.println("\n--- GUEST MENU ---");
                    System.out.println("1. Create ticket");
                    System.out.println("2. View my tickets");
                    System.out.println("3. Logout");
                    System.out.print("Option: ");
                    option = Integer.parseInt(sc.nextLine());

                    switch (option) {

                        case 1:
                            System.out.print("Describe your issue: ");
                            String desc = sc.nextLine();

                            System.out.print("Department (Maintenance / Housekeeping / Systems): ");
                            String dep = sc.nextLine();

                            Ticket t = system.createTicket(g, desc, dep);

                            System.out.println("\n✔ Ticket created successfully");
                            System.out.println("ID: " + t.getTicketId());
                            System.out.println("Assigned tech: " + t.getAssignedTech().getName());
                            break;

                        case 2:
                            system.viewTicketsByGuest(g);
                            break;

                        case 3:
                            System.out.println("Logging out...");
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }

                } while (option != 3);
            }
            sc.close();
        }

    }
}
// Test change -  Tue Nov 25 17:24:28 HPS 2025
