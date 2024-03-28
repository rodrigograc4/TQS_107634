package tqs.cars;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import tqs.cars.boundary.CarController;
import tqs.cars.model.Car;
import tqs.cars.services.CarManagerService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@WebMvcTest(CarController.class)
public class CarsControllerTest {
    

    @Autowired
    private MockMvc mvc;


    @MockBean
    private CarManagerService carService;

    @BeforeEach
    public void setUp() {
    }


    @SuppressWarnings("null")
    @Test
    public void testCreateCar() throws Exception {
        Car car = new Car("Porsche", "GT3");
        
        when(carService.save(any())).thenReturn(car);

        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.maker", is("Porsche")));

        verify(carService, times(1)).save(any());
    }


    @SuppressWarnings("null")
    @Test
    public void testgetAllCars() throws Exception {
        Car car1 = new Car("Porsche", "GT3");
        Car car2 = new Car("Dodge", "Charger");
        Car car3 = new Car ("Nissan", "GTR-35");

        List<Car> cars = Arrays.asList(car1, car2, car3);

        when(carService.getAllCars()).thenReturn(cars);

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].maker", is("Porsche")))
        .andExpect(jsonPath("$[1].maker", is("Dodge")))
        .andExpect(jsonPath("$[2].maker", is("Nissan")));


        verify(carService, times(1)).getAllCars();
       
    }

    @Test 
    public void testGetCarById() throws Exception {
        Car car = new Car("Porsche", "GT3");
        car.setCarId(1L);

        when(carService.getCarDetails(1L)).thenReturn((car));

        mvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maker", is("Porsche")));

        verify(carService, times(1)).getCarDetails(1L);
    }

    @Test
    public void testGetCarByIdNotFound() throws Exception {
        when(carService.getCarDetails(1L)).thenReturn(null);
        
        mvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    
        verify(carService, times(1)).getCarDetails(1L);
    }
    


}