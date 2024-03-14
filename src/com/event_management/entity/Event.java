package com.event_management.entity;


import java.util.List;

public class Event {
    private String name;
    private String location;
    private String date;
    private double budget;
    private List<Service> services;
    private String status;  
 

	public Event(String name ,String location, String date, double price,List<Service> services) {
		this.name = name;
		this.budget = price;
		this.location = location;
        this.date = date;
        this.status = "Pending"; 
        this.services = services;
	}

	public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public double getBudget() {
        return budget;
    }

    public List<Service> getServices() {
        return services;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(Service service) {
        services.remove(service);
    }

    @Override
    public String toString() {
        return "Event Name: " + name +
                "\nLocation: " + location +
                "\nDate: " + date +
                "\nBudget: $" + budget +
                "\nStatus: " + status +
                "\nServices Booked: " + services.toString();
    }
}

