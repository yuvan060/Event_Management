package com.event_management.entity;

public class Service {
    private String name;
    private String description;
    private double price;
    private String status;
    private Vendor vendor;

    public Service(String name, String description,double price,Vendor vendor) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.vendor = vendor;
        this.status = "Available";  
    }
    
    public Vendor getVendor() {
    	return this.vendor;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Service: " + name +
                "\nDescription: " + description +
                "\nPrice: $" + price +
                "\nStatus: " + status;
    }

	public void setName(String updatedName) {
		this.name = updatedName;
	}

	public void setDescription(String updatedDescription) {
		this.description = updatedDescription;
	}
}
