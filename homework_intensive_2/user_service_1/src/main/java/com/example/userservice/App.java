package com.example.userservice;

import com.example.userservice.exception.DaoException;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import com.example.userservice.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final UserService userService = new UserService();

    public static void main(String[] args) {
        logger.info("User service starting...");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> createUser(scanner);
                    case "2" -> listUsers();
                    case "3" -> getUser(scanner);
                    case "4" -> updateUser(scanner);
                    case "5" -> deleteUser(scanner);
                    case "0" -> {
                        running = false;
                        logger.info("Shutting down...");
                    }
                    default -> System.out.println("Unknown option. Try again.");
                }
            } catch (DaoException e) {
                System.err.println("Operation failed: " + e.getMessage());
                logger.error("Operation failed", e);
            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
                logger.error("Unexpected error", e);
            }
        }

        scanner.close();
        HibernateUtil.shutdown();
        System.out.println("Bye!");
    }

    private static void printMenu() {
        System.out.println("\n--- User Service ---");
        System.out.println("1) Create user");
        System.out.println("2) List users");
        System.out.println("3) Get user by id");
        System.out.println("4) Update user");
        System.out.println("5) Delete user");
        System.out.println("0) Exit");
        System.out.print("Choose: ");
    }

    private static void createUser(Scanner scanner) throws DaoException {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Age (optional): ");
        String ageStr = scanner.nextLine().trim();
        Integer age = ageStr.isEmpty() ? null : Integer.parseInt(ageStr);
        User created = userService.createUser(name, email, age);
        System.out.println("Created: " + created);
    }

    private static void listUsers() throws DaoException {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private static void getUser(Scanner scanner) throws DaoException {
        System.out.print("Enter id: ");
        Long id = Long.parseLong(scanner.nextLine().trim());
        Optional<User> user = userService.getUserById(id);
        user.ifPresentOrElse(System.out::println, () -> System.out.println("User not found."));
    }

    private static void updateUser(Scanner scanner) throws DaoException {
        System.out.print("Enter id to update: ");
        Long id = Long.parseLong(scanner.nextLine().trim());
        System.out.print("New name (leave blank to keep): ");
        String name = scanner.nextLine().trim();
        System.out.print("New email (leave blank to keep): ");
        String email = scanner.nextLine().trim();
        System.out.print("New age (leave blank to keep): ");
        String ageStr = scanner.nextLine().trim();

        String newName = name.isEmpty() ? null : name;
        String newEmail = email.isEmpty() ? null : email;
        Integer newAge = ageStr.isEmpty() ? null : Integer.parseInt(ageStr);

        User updated = userService.updateUser(id, newName, newEmail, newAge);
        System.out.println("Updated: " + updated);
    }

    private static void deleteUser(Scanner scanner) throws DaoException {
        System.out.print("Enter id to delete: ");
        Long id = Long.parseLong(scanner.nextLine().trim());
        boolean ok = userService.deleteUser(id);
        if (ok) System.out.println("Deleted.");
        else System.out.println("User not found.");
    }
}