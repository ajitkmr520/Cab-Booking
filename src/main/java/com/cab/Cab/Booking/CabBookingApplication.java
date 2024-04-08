package com.cab.Cab.Booking;

import com.cab.Cab.Service.CabBookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CabBookingApplication implements CommandLineRunner {

	@Autowired
	private CabBookingServiceImpl cabBookingService;

	public static void main(String[] args) {
		SpringApplication.run(CabBookingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cabBookingService.addUser("Abhishek", "M", 23);
		cabBookingService.addUser("Rahul", "M", 29);
		cabBookingService.addUser("Nandini", "F", 22);

		cabBookingService.addDriver("Driver1", "M", 22, "Swift", "KA-01-12345", new double[]{10, 1});
		cabBookingService.addDriver("Driver2", "M", 29, "Swift", "KA-01-12345", new double[]{11, 10});
		cabBookingService.addDriver("Driver3", "M", 24, "Swift", "KA-01-12345", new double[]{5, 3});

		System.out.println(cabBookingService.findRide("Abhishek", new double[]{0, 0}, new double[]{20, 1}));
		System.out.println(cabBookingService.findRide("Rahul", new double[]{10, 0}, new double[]{15, 3}));
		System.out.println(cabBookingService.findRide("Nandini", new double[]{15, 6}, new double[]{20, 4}));
	}
}

