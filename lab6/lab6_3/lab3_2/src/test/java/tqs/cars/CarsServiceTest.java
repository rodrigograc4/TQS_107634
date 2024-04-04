package tqs.cars;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.cars.model.Car;
import tqs.cars.data.CarRepository;
import tqs.cars.services.CarManagerService;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
public class CarsServiceTest {
    
    @Mock (lenient= true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;


    @BeforeEach
    public void setUp(){

        Car car1 = new Car("Porsche", "GT3");
        Car car2 = new Car("Dodge", "Charger");
        Car car3 = new Car ("Nissan", "GTR-35");

        car1.setCarId(1L);
        car2.setCarId(2L);
        car3.setCarId(3L);
        List<Car> allCars = List.of(car1, car2, car3);

        when(carRepository.findAll()).thenReturn(allCars);
        when(carRepository.findByCarId(1L)).thenReturn(car1);
        when(carRepository.findByCarId(2L)).thenReturn(car2);
        when(carRepository.findByCarId(3L)).thenReturn(car3);
        when(carRepository.findByCarId(54L)).thenReturn(null);
        
    }


    @Test
    public void testCarDetailsValid(){

        Car carToBeFound = carService.getCarDetails(1L);

        assertThat(carToBeFound.getMaker()).isEqualTo("Porsche");

    }
    
    @Test
    public void testGetCarDetailsWrong() {
        Car carToBeFound = carService.getCarDetails(54L);

        assertThat(carToBeFound).isNull();
        
    }


    @Test
    public void testGetAllCars(){
            
            List<Car> cars = carService.getAllCars();
    
            assertThat(cars).hasSize(3).extracting(Car::getMaker).contains("Porsche", "Dodge", "Nissan");

            verify(carRepository).findAll();
    }

}