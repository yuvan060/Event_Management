package com.event_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.event_management.entity.Admin;
import com.event_management.entity.Customer;
import com.event_management.entity.Event;
import com.event_management.entity.Vendor;
import com.event_management.entity.Package;
import com.event_management.entity.Service;

public class EventManagementSystem {
	
	private List<Customer> customers;
    private List<Vendor> vendors;
    private List<Admin> admins;
    private List<Event> events;
    private List<Service> services;
    private List<Package> packages;
    private Scanner scanner;
    
    public EventManagementSystem() {
        this.customers = new ArrayList<>();
        this.vendors = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.events = new ArrayList<>();
        this.packages = new ArrayList<>();
        this.services = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
    public void registerCustomer() {
        System.out.println("----- Register Customer -----");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Customer newCustomer = new Customer(name, email, password);
        customers.add(newCustomer);
        System.out.println("Customer registered successfully.");
    }
    
    public void registerVendor() {
        System.out.println("----- Register Vendor -----");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Vendor newVendor = new Vendor(name, email, password);
        vendors.add(newVendor);
        System.out.println("Vendor registered successfully.");
    }
    
    public void registerAdmin() {
        System.out.println("----- Register Admin -----");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Admin newAdmin = new Admin(name, email, password);
        admins.add(newAdmin);
        System.out.println("Admin registered successfully.");
    }
    
    public void login() {
        System.out.println("----- Login -----");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (loginCustomer(email, password)) {
            return;
        }
        if (loginVendor(email, password)) {
            return;
        }
        if (loginAdmin(email, password)) {
            return;
        }
        System.out.println("Login failed. Invalid credentials.");
    }
    
    private boolean loginCustomer(String email, String password) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                System.out.println("Customer login successful.");
                customerMenu(customer);
                return true;
            }
        }
        return false;
    }
    
    private boolean loginVendor(String email, String password) {
        for (Vendor vendor : vendors) {
            if (vendor.getEmail().equals(email) && vendor.getPassword().equals(password)) {
                System.out.println("Vendor login successful.");
                vendorMenu(vendor);
                return true;
            }
        }
        return false;
    }

    private boolean loginAdmin(String email, String password) {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                System.out.println("Admin login successful.");
                adminMenu(admin);
                return true;
            }
        }
        return false;
    }
    
    private void customerMenu(Customer customer) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----- Customer Menu -----");
            System.out.println("1. Book an Event");
            System.out.println("2. View Available Packages");
            System.out.println("3. View Booked Events");
            System.out.println("4. Give Feedback");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    Event bookedEvent = customer.bookEvent(scanner,packages);
                    if(bookedEvent != null) {
                    	events.add(bookedEvent);
                    }
                    break;
                case 2:
                    customer.viewPackages(packages);
                    break;
                case 3:
                    customer.viewBookedEvents();
                    break;
                case 4:
                    customer.giveFeedbackForCompletedService(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void vendorMenu(Vendor vendor) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----- Vendor Menu -----");
            System.out.println("1. Add a Service");
            System.out.println("2. View Services");
            System.out.println("3. Update Service Status");
            System.out.println("4. View Booked Events");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    Service createdService = vendor.addService(scanner);
                    services.add(createdService);
                    break;
                case 2:
                    vendor.viewServices();
                    break;
                case 3:
                    vendor.updateService(scanner);
                    break;
                case 4:
                    vendor.viewBookedEvents(events);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void adminMenu(Admin admin) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----- Admin Menu -----");
            System.out.println("1. Create a Package");
            System.out.println("2. View Available Services");
            System.out.println("3. View packages");
            System.out.println("4. View Events");
            System.out.println("5. Update Event status");
            System.out.println("6. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    Package createdPackage = admin.createPackage(scanner,services);
                    if(createdPackage!= null) {
                    	packages.add(createdPackage);
                    }
                    break;
                case 2:
                    admin.viewAvailableServices(services);;
                    break;
                case 4:
                    admin.viewEvents(events);
                    break;
                case 3:
                    admin.viewPackages();;
                    break;
                case 5:
                   admin.updateEventStatus(scanner, events);
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
        
    private void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----- Welcome to Event Management System -----");
            System.out.println("1. Register Customer");
            System.out.println("2. Register Vendor");
            System.out.println("3. Register Admin");
            System.out.println("4. Login");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    registerVendor();
                    break;
                case 3:
                    registerAdmin();
                    break;
                case 4:
                    login();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
        System.out.println("Exiting Event Management System.");
    }
    
    public static void main(String args[]) {
    	EventManagementSystem ems = new EventManagementSystem();
    	ems.run();
    }
}
