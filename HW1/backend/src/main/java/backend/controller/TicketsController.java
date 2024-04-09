package backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.entity.Tickets;
import backend.service.TicketsService;

@RestController
@RequestMapping("/")
public class TicketsController {
    private TicketsService ticketsService;

    public TicketsController(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }

    @GetMapping("/tickets")
    public ResponseEntity<Object> getAllTickets(
            @RequestParam(value = "origem", required = false) String origem,
            @RequestParam(value = "destino", required = false) String destino,
            @RequestParam(value = "empresa", required = false) String empresa,
            @RequestParam(value = "partida", required = false) String partida,
            @RequestParam(value = "chegada", required = false) String chegada,
            @RequestParam(value = "preco", required = false) Double preco) {
        List<Tickets> tickets = ticketsService.getAllTickets(origem, destino, empresa, partida, chegada, preco);
        
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<Object> getTicketById(@PathVariable long id) {
        Tickets ticket = ticketsService.getTicketById(id);
        
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
    }
}