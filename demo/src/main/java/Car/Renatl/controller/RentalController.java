package Car.Renatl.controller;


import Car.Renatl.Entities.Rental;
import Car.Renatl.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @PostMapping("/rent")
    public Rental rentCar(@RequestParam Long carId, @RequestParam String customer) {
        return rentalService.rentCar(carId, customer);
    }

    @PostMapping("/return/{rentalId}")
    public Rental returnCar(@PathVariable Long rentalId) {
        return rentalService.returnCar(rentalId);
    }


    @GetMapping("/getData")
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }



}
