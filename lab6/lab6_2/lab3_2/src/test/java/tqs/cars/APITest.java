package tqs.cars;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;


import static org.assertj.core.api.Assertions.assertThat;

import tqs.cars.model.Car;
import tqs.cars.data.CarRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@TestPropertySource(locations = "application-integrationtest.properties")
@AutoConfigureTestDatabase
public class APITest {

    @LocalServerPort
    int testPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void reset() {
        repository.deleteAll();
    }


    @Test
    public void testAddCar(){
            
            Car car = new Car("Porsche", "GT3");
            ResponseEntity<Car> response = restTemplate.postForEntity("/api/cars", car, Car.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            assertThat(response.getBody().getMaker()).isEqualTo(car.getMaker());
    }

    @Test
    public void testGetAllCars() {

        Car car1 = new Car("Porsche", "GT3");
        Car car2 = new Car("Dodge", "Charger");
        Car car3 = new Car ("Nissan", "GTR-35");
        repository.save(car1);
        repository.save(car2);
        repository.save(car3);


        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly(car1.getMaker(), car2.getMaker(), car3.getMaker());
    }

    @Test
    public void testGetCarByID(){
        Car car = new Car("Porsche", "GT3");
        repository.save(car);

        ResponseEntity<Car> response = restTemplate.getForEntity("/api/cars/" + car.getCarId(), Car.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getMaker()).isEqualTo(car.getMaker());

    }


    @Test
    public void testGetCarByIDnull(){
        ResponseEntity<Car> response = restTemplate.exchange("/cars/420", HttpMethod.GET, null, Car.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
    
}