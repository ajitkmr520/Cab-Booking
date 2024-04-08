package com.cab.Cab.Controller;

import com.cab.Cab.Entity.Driver;
import com.cab.Cab.Entity.User;
import com.cab.Cab.Exception.DriverNotFoundException;
//import com.cab.Cab.Service.CabBookingService;
import com.cab.Cab.Service.CabBookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CabBookingController {

    @Autowired
    private CabBookingServiceImpl cabBookingService;

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        cabBookingService.addUser(user.getName(), user.getGender(), user.getAge());
        return ResponseEntity.ok("User added successfully");
    }

    @PostMapping("/drivers")
    public ResponseEntity<String> addDriver(@RequestBody Driver driver) {
        cabBookingService.addDriver(driver.getName(), driver.getGender(), driver.getAge(),
                driver.getVehicleDetails(), driver.getLicensePlate(), driver.getCurrentLocation());
        return ResponseEntity.ok("Driver added successfully");
    }

    @GetMapping("/findRide")
    public ResponseEntity<String> findRide(@RequestParam String userName,
                                           @RequestParam double[] source,
                                           @RequestParam double[] destination) {
        try {
            String result = cabBookingService.findRide(userName, source, destination);
            return ResponseEntity.ok(result);
        } catch (DriverNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/chooseRide")
    public ResponseEntity<String> chooseRide(@RequestParam String userName,
                                             @RequestParam String driverName) {
        try {
            cabBookingService.chooseRide(userName, driverName);
            return ResponseEntity.ok("Ride chosen successfully");
        } catch (DriverNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
