package tqs.cars;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.cars.model.Car;
import tqs.cars.data.CarRepository;


@DataJpaTest
public class CarsRepositoryTest {
    

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CarRepository carRepository;


    @Test
    public void testFindCarById(){
        Car car = new Car("Porsche","GT3");

        testEntityManager.persistAndFlush(car);

        Car foundCar = carRepository.findByCarId(car.getCarId());

        assertThat(foundCar).isNotNull();
        assertThat(foundCar.getCarId()).isEqualTo(car.getCarId());

        
    }

    @Test
    public void testFindCarByIdNull(){
        
        Car foundCar = carRepository.findByCarId(54L);

        assertThat(foundCar).isNull();
    }


    @Test
    public void testFindAllCars(){
        Car car1 = new Car("Porsche", "GT3");
        Car car2 = new Car("Dodge", "Charger");
        Car car3 = new Car ("Nissan", "GTR-35");


        testEntityManager.persistAndFlush(car1);
        testEntityManager.persistAndFlush(car2);
        testEntityManager.persistAndFlush(car3);

        List<Car> cars = carRepository.findAll();


        assertThat(cars).isNotNull();
        assertThat(cars).hasSize(3).extracting(Car::getMaker).containsOnly(car1.getMaker(), car2.getMaker(), car3.getMaker());
    }
}