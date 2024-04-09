package backend.integrationtest;

import backend.entity.Tickets;
import backend.repository.TicketsRepository;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "../../application.properties")
class TicketsIntegrationTest {
    
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TicketsRepository ticketsRepository;

    @BeforeEach
    public void setUp() {
        ticketsRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        ticketsRepository.deleteAll();
    }

    @Test
    @DisplayName("Get all tickets")
    void testGetAllTickets() {
        Tickets ticket1 = new Tickets("Porto", "Viseu", "BuzzTuga", "2021-10-10 10:00", "2021-10-10 12:00", 10.0);
        Tickets ticket2 = new Tickets("Porto", "Viseu", "Autocarros de Portugal", "2021-10-10 14:00", "2021-10-10 16:00", 10.0);
        ticketsRepository.save(ticket1);
        ticketsRepository.save(ticket2);

        ResponseEntity<Tickets[]> responseEntity = restTemplate.getForEntity("/tickets", Tickets[].class);
        Tickets[] tickets = responseEntity.getBody();
        
        assertThat(tickets)
            .isNotNull()
            .hasSize(2);
        assertThat(tickets).extracting(Tickets::getOrigem).containsOnly("Porto", "Porto");
        assertThat(tickets).extracting(Tickets::getDestino).containsOnly("Viseu", "Viseu");
        assertThat(tickets).extracting(Tickets::getEmpresa).containsOnly("BuzzTuga", "Autocarros de Portugal");
        assertThat(tickets).extracting(Tickets::getPartida).containsOnly("2021-10-10 10:00", "2021-10-10 14:00");
        assertThat(tickets).extracting(Tickets::getChegada).containsOnly("2021-10-10 12:00", "2021-10-10 16:00");
        assertThat(tickets).extracting(Tickets::getPreco).containsOnly(10.0, 10.0);        
    }

    @Test
    @DisplayName("Get ticket by id")
    void testGetTicketById() {
        Tickets ticket1 = new Tickets("Porto", "Viseu", "BuzzTuga", "2021-10-10 10:00", "2021-10-10 12:00", 10.0);
        Tickets ticket2 = new Tickets("Porto", "Viseu", "Autocarros de Portugal", "2021-10-10 14:00", "2021-10-10 16:00", 10.0);
        Tickets ticket3 = new Tickets("Viseu", "Porto", "Rede Transportes Portugal", "2021-10-10 10:00", "2021-10-10 12:00", 10.0);

        ticket1 = ticketsRepository.save(ticket1);
        ticket2 = ticketsRepository.save(ticket2);
        ticket3 = ticketsRepository.save(ticket3);

        restTemplate.getForEntity("/tickets/"+ ticket1.getId(), Tickets.class);

        Tickets ticket = restTemplate.getForObject("/tickets/{id}", Tickets.class, ticket1.getId());

        assertThat(ticket).isNotNull();
        assertThat(ticket).extracting(Tickets::getOrigem).isEqualTo("Porto");
        assertThat(ticket).extracting(Tickets::getDestino).isEqualTo("Viseu");
        assertThat(ticket).extracting(Tickets::getEmpresa).isEqualTo("BuzzTuga");
        assertThat(ticket).extracting(Tickets::getPartida).isEqualTo("2021-10-10 10:00");
        assertThat(ticket).extracting(Tickets::getChegada).isEqualTo("2021-10-10 12:00");
        assertThat(ticket).extracting(Tickets::getPreco).isEqualTo(10.0);
    }
}
