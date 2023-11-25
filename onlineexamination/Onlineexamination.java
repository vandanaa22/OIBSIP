import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private String name;
    private String email;
    private Map<String, String> selectedAnswers;

    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.selectedAnswers = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public void setProfile(String newName, String newEmail) {
        name = newName;
        email = newEmail;
    }

    public void selectAnswer(String question, String answer) {
        selectedAnswers.put(question, answer);
    }

    public String getSelectedAnswers(String question) {
        return selectedAnswers.get(question);
    }
}

class OnlineExamSystem {
    private Map<String, User> users;
    public User loggedInUser;

    public OnlineExamSystem() {
        users = new HashMap<>();
    }

    public void register(String username, String password, String name, String email) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password, name, email));
            System.out.println("Registration successful!");
        } else {
            System.out.println("Username already exists.");
        }
    }

    public boolean login(String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Welcome, " + username + "!");
                return true;
            }
        }
        System.out.println("Login failed. Invalid credentials.");
        return false;
    }

    public void updatePassword(String newPassword) {
        if (loggedInUser != null) {
            loggedInUser.setPassword(newPassword);
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("You are not logged in.");
        }
    }

    public void updateProfile(String newName, String newEmail) {
        if (loggedInUser != null) {
            loggedInUser.setProfile(newName, newEmail);
            System.out.println("Profile updated successfully!");
        } else {
            System.out.println("You are not logged in.");
        }
    }

    public void logout() {
        loggedInUser = null;
        System.out.println("Logged out successfully.");
    }
}

public class Onlineexamination {
    public static void main(String[] args) {
        OnlineExamSystem examSystem = new OnlineExamSystem();
        Scanner scanner = new Scanner(System.in);
        boolean shouldRun = true;
        boolean wasLoggedIn = false;

        System.out.println("Welcome to the Online Examination System!");

        while (shouldRun) {
            System.out.println();
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    examSystem.register(username, password, name, email);
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    examSystem.login(loginUsername, loginPassword);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }

            if (examSystem.loggedInUser != null) {
                wasLoggedIn = true;
                while (true) {
                    System.out.println();
                    System.out.println("1. Update Password");
                    System.out.println("2. Update Profile");
                    System.out.println("3. Start Exam");
                    System.out.println("4. Logout");
                    System.out.print("Choose an option: ");
                    int choice2 = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    

                    switch (choice2) {
                        case 1:
                            if (examSystem.loggedInUser != null) {
                                System.out.print("Enter new password: ");
                                String newPassword = scanner.nextLine();
                                examSystem.updatePassword(newPassword);
                            } else {
                                System.out.println("You are not logged in.");
                            }
                            break;

                        case 2:
                            if (examSystem.loggedInUser != null) {
                                System.out.print("Enter new name: ");
                                String newName = scanner.nextLine();
                                System.out.print("Enter new email: ");
                                String newEmail = scanner.nextLine();
                                examSystem.updateProfile(newName, newEmail);
                            } else {
                                System.out.println("You are not logged in.");
                            }
                            break;

                        case 3:
                            System.out.println("Subject: Java Programming");
                            System.out.println();
                            // Add your exam questions here and score the answers
                            break;

                        case 4:
                            examSystem.logout();
                            wasLoggedIn = false;
                            break;

                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                            break;
                    }
                    if (!wasLoggedIn) {
                        break;
                    }
                }
            } else if (wasLoggedIn) {
                System.out.println("Logged out successfully.");
                wasLoggedIn = false;
            }
        }
    }
}
