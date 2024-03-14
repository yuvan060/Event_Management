package com.event_management.entity;

public class Feedback {
    private String customerName;
    private String vendorName;
    private String serviceName;
    private String comment;
    private int rating; // Rating out of 5

    public Feedback(String customerName, String vendorName, String serviceName, String comment, int rating) {
        this.customerName = customerName;
        this.vendorName = vendorName;
        this.serviceName = serviceName;
        this.comment = comment;
        this.rating = rating;
    }

    public Feedback(String customerName, Vendor selectedVendor, String feedbackText) {
		this.customerName = customerName;
		this.vendorName = selectedVendor.getName();
		this.comment = feedbackText;
	}

	public String getCustomerName() {
        return customerName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Feedback from: " + customerName +
                "\nVendor: " + vendorName +
                "\nService: " + serviceName +
                "\nRating: " + rating +
                "\nComment: " + comment;
    }
}

