package com.event_management.entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer implements Event_Management_Customer {
    private String name;
    private String email;
    private String password;
    private List<Event> bookedEvents;
    private List<Feedback> feedbacks;

    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.bookedEvents = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
    }

    public Event bookEvent(Scanner scanner,List<Package> packages) {
        System.out.println("\nAvailable Packages:");
        for (int i = 0; i < packages.size(); i++) {
            System.out.println((i + 1) + ". " + packages.get(i).getName());
        }

        System.out.print("Enter the package number to book: ");
        int packageNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (packageNumber <= 0 || packageNumber > packages.size()) {
            System.out.println("Invalid package number.");
            return null;
        }
        System.out.println("Enter the location: ");
        String location = scanner.nextLine();
        
        System.out.println("Enter the date of the event : ");
        String date  = scanner.nextLine();
        Package selectedPackage = packages.get(packageNumber - 1);
        Event newEvent = new Event(selectedPackage.getName(),location,date, selectedPackage.getPrice(),selectedPackage.getServices());
        bookedEvents.add(newEvent);
        System.out.println("Event booked successfully: " + newEvent.getName());
        return newEvent;
    }

    public void viewPackages(List<Package> packages) {
        System.out.println("\nAvailable Packages:");
        for (Package pack : packages) {
            System.out.println(pack.toString());
        }
    }

//    public void giveFeedback(Scanner scanner,List<Vendor> vendors) {
//        System.out.println("\nAvailable Vendors:");
//        for (int i = 0; i < vendors.size(); i++) {
//            System.out.println((i + 1) + ". " + vendors.get(i).getName());
//        }
//
//        System.out.print("Enter the vendor number to give feedback: ");
//        int vendorNumber = scanner.nextInt();
//        scanner.nextLine();  // Consume newline
//
//        if (vendorNumber <= 0 || vendorNumber > vendors.size()) {
//            System.out.println("Invalid vendor number.");
//            return;
//        }
//
//        Vendor selectedVendor = vendors.get(vendorNumber - 1);
//        System.out.print("Enter your feedback: ");
//        String feedbackText = scanner.nextLine();
//
//        Feedback feedback = new Feedback(this.name, selectedVendor, feedbackText);
//        feedbacks.add(feedback);
//        selectedVendor.addFeedback(feedback);
//        System.out.println("Feedback submitted successfully.");
//    }

    public void giveFeedbackForCompletedService(Scanner scanner) {
        System.out.println("\nCompleted Services:");
        List<Service> completedServices = getCompletedServices();
        if (completedServices.isEmpty()) {
            System.out.println("No completed services found.");
            return;
        }

        for (int i = 0; i < completedServices.size(); i++) {
            System.out.println((i + 1) + ". " + completedServices.get(i).getName());
        }

        System.out.print("Enter the service number to give feedback: ");
        int serviceNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (serviceNumber <= 0 || serviceNumber > completedServices.size()) {
            System.out.println("Invalid service number.");
            return;
        }

        Service selectedService = completedServices.get(serviceNumber - 1);
        Vendor vendor = selectedService.getVendor();

        System.out.print("Enter your feedback for " + vendor.getName() + ": ");
        String feedbackText = scanner.nextLine();

        Feedback feedback = new Feedback(this.name, vendor, feedbackText);
        feedbacks.add(feedback);
        vendor.addFeedback(feedback);
        System.out.println("Feedback submitted successfully for " + selectedService.getName());
    }

    private List<Service> getCompletedServices() {
        List<Service> completedServices = new ArrayList<>();
        for (Event event : bookedEvents) {
            if (event.getStatus().equals("Completed")) {
                completedServices.addAll(event.getServices());
            }
        }
        return completedServices;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void viewBookedEvents() {
        System.out.println("\nBooked Events:");
        if (bookedEvents.isEmpty()) {
            System.out.println("No events booked.");
        } else {
            for (Event event : bookedEvents) {
                System.out.println(event.toString());
            }
        }
    }
}
