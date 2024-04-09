package backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.entity.Tickets;
import backend.repository.TicketsRepository;

@Service
public class TicketsService {

    private final TicketsRepository ticketsRepository;

    public TicketsService(TicketsRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }

    public List<Tickets> getAllTickets(String origem, String destino, String empresa, String partida, String chegada, Double preco){
        List<Tickets> tickets = ticketsRepository.findAll();
        return tickets;
    }

    public Tickets getTicketById(long id){
        Optional<Tickets> optionalTicket = ticketsRepository.findById(id);
        if (optionalTicket.isPresent()) {
            return optionalTicket.get();
        } else {
            throw new NoSuchElementException("Ticket with id " + id + " not found");
        }
    }

    public List<Tickets> getTicketByOrigemPartidaDestinoChegada(String origem, String partida, String destino, String chegada) {
        return ticketsRepository.findAll().stream()
                .filter(ticket -> origem == null || ticket.getOrigem().equals(origem))
                .filter(ticket -> partida == null || ticket.getPartida().equals(partida))
                .filter(ticket -> destino == null || ticket.getDestino().equals(destino))
                .filter(ticket -> chegada == null || ticket.getChegada().equals(chegada))
                .toList();                
    }
}
