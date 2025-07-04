package Car.Renatl.repository;

import Car.Renatl.Entities.Car;
import Car.Renatl.Entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findByAvailableTrue();
}
