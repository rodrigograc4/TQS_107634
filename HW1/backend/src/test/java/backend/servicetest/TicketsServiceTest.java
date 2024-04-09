package backend.servicetest;

import backend.entity.Tickets;
import backend.repository.TicketsRepository;
import backend.service.TicketsService;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.reset;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TicketsServiceTest {
    @Mock
    private TicketsRepository ticketsRepository;

    @InjectMocks
    private TicketsService ticketsService;

    @BeforeEach
    public void setUp() {
        reset(ticketsRepository);
    }

    @Test
    @DisplayName("Get all tickets")
    public void testTicketsServiceGetAll() {
        Tickets ticket1 = new Tickets("Porto", "Viseu", "BuzzTuga", "2021-10-10 10:00", "2021-10-10 12:00", 10.0);
        Tickets ticket2 = new Tickets("Coimbra", "Fatima", "Autocarros de Portugal", "2021-03-12 14:00", "2021-03-12 17:00", 15.0);
        Tickets ticket3 = new Tickets("Lisboa", "Porto", "Rede Transportes Portugal", "2021-05-20 08:00", "2021-05-20 10:00", 12.0);


        Mockito.when(ticketsRepository.findAll()).thenReturn(Arrays.asList(ticket1, ticket2, ticket3));

        List<Tickets> tickets = ticketsService.getAllTickets(null, null, null, null, null, null);

        assertThat(tickets).hasSize(3);
        Mockito.verify(ticketsRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Get ticket by id")
    public void testTicketsServiceGetById() {
        Tickets ticket = new Tickets("Porto", "Viseu", "BuzzTuga", "2021-10-10 10:00", "2021-10-10 12:00", 10.0);
        ticket.setId(1L);

        Mockito.when(ticketsRepository.findById(1L)).thenReturn(java.util.Optional.of(ticket));

        Tickets ticketFound = ticketsService.getTicketById(1L);

        assertThat(ticketFound).isEqualTo(ticket);
        Mockito.verify(ticketsRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("Get ticket by origem, partida, destino, chegada")
    public void testTicketsServiceGetByOrigemPartidaDestinoChegada() {
        Tickets ticket1 = new Tickets("Porto", "Viseu", "BuzzTuga", "2021-10-10 10:00", "2021-10-10 12:00", 10.0);
        Tickets ticket2 = new Tickets("Porto", "Viseu", "Autocarros de Portugal", "2021-10-10 14:00", "2021-10-10 16:00", 10.0);
        Tickets ticket3 = new Tickets("Viseu", "Porto", "Rede Transportes Portugal", "2021-10-10 10:00", "2021-10-10 12:00", 10.0);


        Mockito.when(ticketsRepository.findAll()).thenReturn(Arrays.asList(ticket1, ticket2, ticket3));

        List<Tickets> tickets = ticketsService.getTicketByOrigemPartidaDestinoChegada(ticket1.getOrigem(), ticket1.getPartida(), ticket1.getDestino(), ticket1.getChegada());
        assertThat(tickets).hasSize(1);
        Mockito.verify(ticketsRepository, Mockito.times(1)).findAll();
    }

}

