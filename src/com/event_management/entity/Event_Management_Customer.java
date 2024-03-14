package com.event_management.entity;

import java.util.List;
import java.util.Scanner;

public interface Event_Management_Customer {
	public Event bookEvent(Scanner scanner,List<Package> packages);
	public void viewPackages(List<Package> packages);
	public void giveFeedbackForCompletedService(Scanner scanner);
	public void viewBookedEvents();
}
