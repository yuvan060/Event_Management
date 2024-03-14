package com.event_management.entity;


import java.util.List;

public class Package {
    private String name;
    private String description;
    private double price;
    private List<Service> services;

    public Package(String name, String description, double price, List<Service> selectedServices) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.services = selectedServices;
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(Service service) {
        services.remove(service);
    }

    public List<Service> getServices() {
        return services;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Package: ").append(name).append("\n")
               .append("Description: ").append(description).append("\n")
               .append("Price: $").append(price).append("\n")
               .append("Services included:\n");

        for (Service service : services) {
            builder.append("- ").append(service.getName()).append(": $").append(service.getPrice()).append("\n");
        }

        return builder.toString();
    }
}

