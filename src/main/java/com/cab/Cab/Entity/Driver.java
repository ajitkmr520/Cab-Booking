package com.cab.Cab.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Driver {
    private String name;
    private String gender;
    private int age;
    private String vehicleDetails;
    private String licensePlate;
    private double[] currentLocation;
    private boolean available;

    // Constructors, getters, and setters



}
