package com.event_management.entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vendor {
    private String name;
    private String email;
    private String password;
    private List<Feedback> feedbacks;
    private List<Service> services;

    public Vendor(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.feedbacks = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public Service addService(Scanner scanner) {
        System.out.print("Enter service name: ");
        String serviceName = scanner.nextLine();
        System.out.print("Enter service description: ");
        String serviceDescription = scanner.nextLine();
        System.out.println("Enter Service Price : ");
        double price = scanner.nextDouble();
        Service newService = new Service(serviceName, serviceDescription,price,this);
        services.add(newService);
        System.out.println("Service added successfully: " + newService.getName());
        return newService;
    }

    public void viewServices() {
        System.out.println("\nServices offered:");
        if (services.isEmpty()) {
            System.out.println("No services added yet.");
        } else {
            for (int i = 0; i < services.size(); i++) {
                System.out.println((i + 1) + ". " + services.get(i).toString());
            }
        }
    }

    public void updateService(Scanner scanner) {
        System.out.println("\nCurrent Services:");
        viewServices();

        System.out.print("Enter the service number to update: ");
        int serviceNumber = scanner.nextInt();
        scanner.nextLine();  

        if (serviceNumber <= 0 || serviceNumber > services.size()) {
            System.out.println("Invalid service number.");
            return;
        }

        Service selectedService = services.get(serviceNumber - 1);
        System.out.print("Enter updated service name (Enter to keep current): ");
        String updatedName = scanner.nextLine();
        System.out.print("Enter updated service description (Enter to keep current): ");
        String updatedDescription = scanner.nextLine();

        if (!updatedName.isEmpty()) {
            selectedService.setName(updatedName);
        }
        if (!updatedDescription.isEmpty()) {
            selectedService.setDescription(updatedDescription);
        }

        System.out.println("Service updated successfully: " + selectedService.getName());
    }

    public void viewFeedbacks() {
        System.out.println("\nReceived Feedbacks:");
        if (feedbacks.isEmpty()) {
            System.out.println("No feedbacks received yet.");
        } else {
            for (Feedback feedback : feedbacks) {
                System.out.println(feedback.toString());
            }
        }
    }

    public void updateEventServiceStatus(Scanner scanner,Event event) {
        System.out.println("\nUpdate Service Status for Event: " + event.getName());
        List<Service> eventServices = event.getServices();
        if (eventServices.isEmpty()) {
            System.out.println("No services added for this event yet.");
            return;
        }

        System.out.println("Select the service to update status:");
        for (int i = 0; i < eventServices.size(); i++) {
            System.out.println((i + 1) + ". " + eventServices.get(i).getName());
        }

        System.out.print("Enter the service number to update status: ");
        int serviceNumber = scanner.nextInt();
        scanner.nextLine();  

        if (serviceNumber <= 0 || serviceNumber > eventServices.size()) {
            System.out.println("Invalid service number.");
            return;
        }

        Service selectedService = eventServices.get(serviceNumber - 1);
        System.out.print("Enter the new status for " + selectedService.getName() + ": ");
        String newStatus = scanner.nextLine();

        selectedService.setStatus(newStatus);
        System.out.println("Status updated successfully for " + selectedService.getName() +
                " in event " + event.getName());
    }

    public void viewBookedEvents(List<Event> events) {
        System.out.println("\nBooked Events for Your Services:");
        boolean foundEvents = false;
        for (Event event : events) {
            List<Service> eventServices = event.getServices();
            for (Service service : eventServices) {
                if (services.contains(service)) {
                    System.out.println("Service: " + service.getName() +
                            ", Status: " + event.getStatus());
                    foundEvents = true;
                    break;
                }
            }
        }

        if (!foundEvents) {
            System.out.println("No events booked");
        }
        
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }

    public String getName() {
        return name;
    }
}
