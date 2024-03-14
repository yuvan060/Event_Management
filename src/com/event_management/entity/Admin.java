package com.event_management.entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
    private List<Package> packages;
    private String name;
    private String email;
    private String password;


    public Admin(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.packages = new ArrayList<>();
	}

	public Package createPackage(Scanner scanner,List<Service> availableServices) {
        System.out.println("\nCreate Package:");
        viewAvailableServices(availableServices);

        if (availableServices.isEmpty()) {
            System.out.println("No services available to create a package.");
            return null;
        }

        System.out.print("Enter package name: ");
        String packageName = scanner.nextLine();
        System.out.print("Enter package description: ");
        String packageDescription = scanner.nextLine();
        System.out.print("Enter package price: ");
        double packagePrice = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        List<Service> selectedServices = new ArrayList<>();
        boolean addingServices = true;
        while (addingServices) {
            System.out.print("Enter the number of the service to add to the package (0 to finish): ");
            int serviceNumber = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (serviceNumber == 0) {
                addingServices = false;
            } else if (serviceNumber < 0 || serviceNumber > availableServices.size()) {
                System.out.println("Invalid service number.");
            } else {
                Service selectedService = availableServices.get(serviceNumber - 1);
                selectedServices.add(selectedService);
                System.out.println("Added service: " + selectedService.getName() + " to the package.");
            }
        }

        Package newPackage = new Package(packageName, packageDescription, packagePrice, selectedServices);
        packages.add(newPackage);
        System.out.println("Package created successfully: " + newPackage.getName());
        return newPackage;
    }
	
	public void updateEventStatus(Scanner scanner,List<Event> bookedEvents) {
		System.out.println("\nBooked Events:");
        if (bookedEvents.isEmpty()) {
            System.out.println("No events booked.");
        } else {
            for (Event event : bookedEvents) {
                System.out.println(event.toString());
            }
        }
        System.out.println("Enter the event to update : ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter status to be updated : ");
        String status = scanner.nextLine();
        bookedEvents.get(id-1).setStatus(status);
	}

    public void viewAvailableServices(List<Service> availableServices) {
        System.out.println("\nAvailable Services:");
        if (availableServices.isEmpty()) {
            System.out.println("No services available.");
        } else {
            for (int i = 0; i < availableServices.size(); i++) {
                System.out.println((i + 1) + ". " + availableServices.get(i).toString());
            }
        }
    }

    public void viewPackages() {
        System.out.println("\nAvailable Packages:");
        if (packages.isEmpty()) {
            System.out.println("No packages created yet.");
        } else {
            for (int i = 0; i < packages.size(); i++) {
                System.out.println((i + 1) + ". " + packages.get(i).toString());
            }
        }
    }

    public List<Package> getPackages() {
        return packages;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void viewEvents(List<Event> events) {
		for(Event i : events) {
			System.out.println(i);
		}
	}

	
}
