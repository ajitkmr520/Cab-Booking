package com.cab.Cab.Service;

import com.cab.Cab.Entity.Driver;
import com.cab.Cab.Entity.User;
import com.cab.Cab.Exception.DriverNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CabBookingServiceImpl {
    private List<User> users = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();

    
    public synchronized void addUser(String name, String gender, int age) {
        users.add(new User(name, gender, age));
    }

    
    public synchronized void addDriver(String name, String gender, int age, String vehicleDetails, String licensePlate, double[] currentLocation) {
        drivers.add(new Driver(name, gender, age, vehicleDetails, licensePlate, currentLocation, true));
    }

    
    public synchronized String findRide(String userName, double[] source, double[] destination) {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : drivers) {
            if (driver.isAvailable()) {
                double distance = calculateDistance(driver.getCurrentLocation(), source);
                if (distance <= 5) {
                    availableDrivers.add(driver);
                }
            }
        }
        if (availableDrivers.isEmpty()) {
            throw new DriverNotFoundException("No available driver found within 5 units");
        } else {
            return availableDrivers.get(0).getName() + " [Available]";
        }
    }

    
    public synchronized void chooseRide(String userName, String driverName) {
        Driver chosenDriver = null;
        for (Driver driver : drivers) {
            if (driver.getName().equals(driverName)) {
                chosenDriver = driver;
                break;
            }
        }
        if (chosenDriver == null || !chosenDriver.isAvailable()) {
            throw new DriverNotFoundException("Driver not available or not found");
        }
        chosenDriver.setAvailable(false);
    }

    private double calculateDistance(double[] location1, double[] location2) {
        final int R = 6371; // Earth radius in kilometers

        double lat1 = Math.toRadians(location1[0]);
        double lon1 = Math.toRadians(location1[1]);
        double lat2 = Math.toRadians(location2[0]);
        double lon2 = Math.toRadians(location2[1]);

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.pow(Math.sin(dLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Distance in kilometers
    }
}
