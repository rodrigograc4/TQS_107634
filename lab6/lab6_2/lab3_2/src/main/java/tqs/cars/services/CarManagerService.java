package tqs.cars.services;

import org.springframework.stereotype.Service;
import tqs.cars.data.CarRepository;
import tqs.cars.model.Car;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {

    final
    CarRepository carRepository;

    public CarManagerService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public Car save(Car oneCar) {
        return carRepository.save(oneCar);
    }

    public List<Car> getAllCars() {

        return carRepository.findAll();
    }

    public  Car getCarDetails(Long carId) {
        return (carRepository.findByCarId(carId)) ;
    }
}
