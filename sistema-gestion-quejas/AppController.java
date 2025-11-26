import java.util.Scanner;

public class AppController {
    Scanner sc = new Scanner(System.in);
    boolean valid = false;

    public void prueba() {
        Login login = new Login();
        Ticketsystem system = new Ticketsystem(login);

        // ---- Example users ----
        login.addUser(new Admin("A1", "Jose", "Profe", "admin", "1234"));

        Tech t1 = new Tech("T1", "Jheisson", "Passos", "tech1", "1111", "Maintenance");
        Tech t2 = new Tech("T2", "Yoneiker", "Pineda", "tech2", "2222", "Housekeeping");
        Tech t3 = new Tech("T3", "Jhonaycker", "Castillo", "tech3", "3333", "Systems");

        login.addUser(t1);
        login.addUser(t2);
        login.addUser(t3);
        login.addUser(new Guest("G1", "Samuel", "Verbel", "guest", "abcd", 204));

        while (true) {

            System.out.println("\n=== COMPLAINT MANAGEMENT SYSTEM ===");
            System.out.print("Username: ");
            String user = sc.nextLine();
            System.out.print("Password:");
            String pass = sc.nextLine();

            Users u = login.authenticate(user, pass);

            if (u == null) {
                System.out.println("\nWrong username or password.");
                continue;
            }

            System.out.println("\nWelcome " + u.getName() + " (" + u.getRole() + ")");

            // ----------- ADMIN -----------
            if (u.getRole().equals("ADMIN")) {
                int option = -1;
                do {
                    System.out.println("\n--- ADMIN MENU ---");
                    System.out.println("1. View all tickets");
                    System.out.println("2. View technicians");
                    System.out.println("3. Logout");
                    System.out.print("Choose an option: ");

                    try {
                        option = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("\n Please enter a valid option.");
                        continue;
                    }

                    switch (option) {
                        case 1:
                            System.out.println("\n=== Tickets list ===");
                            system.viewAllTickets();
                            break;

                        case 2:
                            System.out.println("\n=== Technicians list ===");
                            for (Tech t : login.getAllTechs()) {
                                System.out.println(t.getName() + " - " + t.getDepartment());
                            }
                            break;

                        case 3:
                            System.out.println("Logging out...");
                            break;

                        default:
                            System.out.println("\nInvalid option.");
                    }

                } while (option != 3);
            }

            // ----------- TECHNICIAN -----------
            else if (u.getRole().equals("TECH")) {

                Tech tech = (Tech) u;
                int option = -1;

                do {
                    System.out.println("\n--- TECHNICIAN MENU ---");
                    System.out.println("1. View assigned tickets");
                    System.out.println("2. Change ticket status");
                    System.out.println("3. Logout");
                    System.out.print("Option: ");

                    try {
                        option = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("\nPlease enter a valid option.");
                        continue;
                    }

                    switch (option) {
                        case 1:
                            System.out.println("\n=== Tickets list ===");
                            system.viewTicketsByTech(tech);
                            break;

                        case 2:
                            System.out.println("\n=== Change tickets status ===");
                            System.out.print("Ticket ID: ");
                            String id = sc.nextLine();
                            String status;
                            while (true) {
                                System.out.print("New status (IN PROGRESS / RESOLVED): ");
                                status = sc.nextLine();

                                if (status.equalsIgnoreCase("IN PROGRESS") || (status.equalsIgnoreCase("RESOLVED"))) {
                                    break;
                                }
                                System.out.println("Invalid status. Please try again.\n");

                            }

                            system.changeStatus(id, status);
                            break;
                        case 3:
                            System.out.println("Logging out...");
                            break;

                        default:
                            System.out.println("\nInvalid option.");
                    }

                } while (option != 3);
            }

            // ----------- GUEST -----------
            else if (u.getRole().equals("GUEST")) {

                Guest g = (Guest) u;
                int option = -1;

                do {
                    System.out.println("\n--- GUEST MENU ---");
                    System.out.println("1. Create ticket");
                    System.out.println("2. View my tickets");
                    System.out.println("3. Logout");
                    System.out.print("Option: ");

                    try {
                        option = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("\nPlease enter a valid option.");
                        continue;
                    }

                    switch (option) {

                        case 1:
                            System.out.println("\n=== Create ticket ====");
                            System.out.print("Describe your issue: ");
                            String desc = sc.nextLine();

                            String dep;
                            while (true) {
                                System.out.print("Department (Maintenance / Housekeeping / Systems): ");
                                dep = sc.nextLine();

                                if (dep.equalsIgnoreCase("Maintenance") ||
                                        dep.equalsIgnoreCase("Housekeeping") ||
                                        dep.equalsIgnoreCase("Systems")) {
                                    break;
                                }

                                System.out.println("Invalid department. Please try again.\n");
                            }

                            Ticket t = system.createTicket(g, desc, dep);

                            System.out.println("\nTicket created successfully");
                            System.out.println("ID: " + t.getTicketId());
                            System.out.println("Assigned tech: " + t.getAssignedTech().getName());
                            break;

                        case 2:
                            System.out.println("\n=== Tickets list ===");
                            system.viewTicketsByGuest(g);
                            break;

                        case 3:
                            System.out.println("Logging out...");
                            break;

                        default:
                            System.out.println("\nInvalid option.");
                    }

                } while (option != 3);
            }
        }

    }
}