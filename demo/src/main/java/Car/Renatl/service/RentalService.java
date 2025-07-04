package Car.Renatl.service;

import Car.Renatl.Entities.Car;
import Car.Renatl.Entities.Rental;
import Car.Renatl.repository.CarRepository;
import Car.Renatl.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentalRepository rentalRepository;

    public Rental rentCar(Long carId, String customerName) {
        Car car = carRepository.findById(carId).orElseThrow();
        if (!car.isAvailable()) {
            throw new RuntimeException("Car is not available.");
        }

        car.setAvailable(false);
        carRepository.save(car);

        Rental rental;
        rental = new Rental();
        rental.setCar(car);
        rental.setCustomerName(customerName);
        rental.setRentalDate(LocalDate.now());

        return rentalRepository.save(rental);
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental returnCar(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        rental.setReturnDate(LocalDate.now());  // set return date to today
        rental.getCar().setAvailable(true);     // mark car as available again

        return rentalRepository.save(rental);
    }
}
