package backend.integrationtest;

import backend.entity.Reserva;
import backend.repository.ReservaRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "../../application.properties")
class ReservaIntegrationTest {
    
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReservaRepository reservaRepository;

    @BeforeEach
    public void setUp() {
        reservaRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        reservaRepository.deleteAll();
    }

    @Test
    @DisplayName("Get reserva by id")
    void testGetReservaById() {
        Reserva reserva1 = new Reserva(1L, "Joao", "joao@gmail.com", "1234 1234 1234 1234", "12/23", 123L, "Joao Silva");
        Reserva reserva2 = new Reserva(2L, "Maria", "maria@gmail.com", "1234 1234 1234 1234", "12/23", 123L, "Maria Silva");

        reserva1 = reservaRepository.save(reserva1);
        reserva2 = reservaRepository.save(reserva2);

        restTemplate.getForEntity("/reserve/"+ reserva1.getId(), Reserva.class);

        Reserva reserva = restTemplate.getForObject("/reserve/{id}", Reserva.class, reserva1.getId());

        assertThat(reserva).isNotNull();
        assertThat(reserva).extracting(Reserva::getIdticket).isEqualTo(1L);
        assertThat(reserva).extracting(Reserva::getNome).isEqualTo("Joao");
        assertThat(reserva).extracting(Reserva::getEmail).isEqualTo("joao@gmail.com");
        assertThat(reserva).extracting(Reserva::getNumeroCartao).isEqualTo("1234 1234 1234 1234");
        assertThat(reserva).extracting(Reserva::getValidade).isEqualTo("12/23");
        assertThat(reserva).extracting(Reserva::getCvv).isEqualTo(123L);
        assertThat(reserva).extracting(Reserva::getNomeCartao).isEqualTo("Joao Silva");
    }

    @Test
    @DisplayName("Post new reserva")
    void testPostNewReserva() {
        Reserva reserva1 = new Reserva(1L, "Joao", "joao@gmail.com", "1234 1234 1234 1234", "12/23", 123L, "Joao Silva");

        Reserva reserva = restTemplate.postForObject("/newreserve", reserva1, Reserva.class);

        assertThat(reserva).isNotNull();
        assertThat(reserva).extracting(Reserva::getIdticket).isEqualTo(1L);
        assertThat(reserva).extracting(Reserva::getNome).isEqualTo("Joao");
        assertThat(reserva).extracting(Reserva::getEmail).isEqualTo("joao@gmail.com");
        assertThat(reserva).extracting(Reserva::getNumeroCartao).isEqualTo("1234 1234 1234 1234");
        assertThat(reserva).extracting(Reserva::getValidade).isEqualTo("12/23");
        assertThat(reserva).extracting(Reserva::getCvv).isEqualTo(123L);
        assertThat(reserva).extracting(Reserva::getNomeCartao).isEqualTo("Joao Silva");
        
    }
}