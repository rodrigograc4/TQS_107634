package backend.repositorytest;

import backend.repository.TicketsRepository;
import backend.entity.Tickets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


@DataJpaTest
public class TicketsRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TicketsRepository ticketsRepository;

    @Test
@DisplayName("Find all tickets")
public void testFindAllTickets() {
    Tickets ticket1 = new Tickets();
    ticket1.setOrigem("Coimbra");
    ticket1.setDestino("Lisboa");
    ticket1.setEmpresa("Rede Expressos");
    ticket1.setPartida("2021-06-01 08:00:00");
    ticket1.setChegada("2021-06-01 10:00:00");
    ticket1.setPreco(20.0);
    entityManager.persist(ticket1);
    Tickets ticket2 = new Tickets();
    ticket2.setOrigem("Porto");
    ticket2.setDestino("Lisboa");
    ticket2.setEmpresa("Rede Expressos");
    ticket2.setPartida("2021-06-02 09:00:00");
    ticket2.setChegada("2021-06-02 11:00:00");
    ticket2.setPreco(25.0);
    entityManager.persist(ticket2);
    entityManager.flush();

    List<Tickets> allTickets = ticketsRepository.findAll();

    assertNotNull(allTickets);
    assertEquals(2, allTickets.size());
}


    @Test
    @DisplayName("Find ticket by id")
    public void testTicketRepositorybyId() {
        Tickets ticket = new Tickets();
        ticket.setOrigem("Coimbra");
        ticket.setDestino("Lisboa");
        ticket.setEmpresa("Rede Expressos");
        ticket.setPartida("2021-06-01 08:00:00");
        ticket.setChegada("2021-06-01 10:00:00");
        ticket.setPreco(20.0);
        entityManager.persist(ticket);
        entityManager.flush();

        Tickets found = ticketsRepository.findById(ticket.getId()).get();

        assertEquals(ticket.getId(), found.getId());
        assertEquals(ticket.getOrigem(), found.getOrigem());
        assertEquals(ticket.getDestino(), found.getDestino());
        assertEquals(ticket.getEmpresa(), found.getEmpresa());
        assertEquals(ticket.getPartida(), found.getPartida());
        assertEquals(ticket.getChegada(), found.getChegada());
        assertEquals(ticket.getPreco(), found.getPreco());
    }


    @Test
    @DisplayName("Find ticket by origem, partida, destino and chegada")
    public void testTicketRepositorybyOrigemandDestino() {
        Tickets ticket = new Tickets();
        ticket.setOrigem("Coimbra");
        ticket.setDestino("Lisboa");
        ticket.setEmpresa("Rede Expressos");
        ticket.setPartida("2021-06-01 08:00:00");
        ticket.setChegada("2021-06-01 10:00:00");
        ticket.setPreco(20.0);
        entityManager.persist(ticket);
        entityManager.flush();

        Tickets found = ticketsRepository.findByOrigemPartidaDestinoChegada(ticket.getOrigem(), ticket.getPartida(), ticket.getDestino(), ticket.getChegada());

        assertEquals(ticket.getId(), found.getId());
        assertEquals(ticket.getOrigem(), found.getOrigem());
        assertEquals(ticket.getDestino(), found.getDestino());
        assertEquals(ticket.getEmpresa(), found.getEmpresa());
        assertEquals(ticket.getPartida(), found.getPartida());
        assertEquals(ticket.getChegada(), found.getChegada());
        assertEquals(ticket.getPreco(), found.getPreco());
    }

}
