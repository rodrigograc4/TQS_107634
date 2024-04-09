package backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.entity.Reserva;
import backend.service.ReservaService;

@RestController
@RequestMapping("/")
public class ReservaController {
    private ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/reserve/{id}")
    public ResponseEntity<Object> reserveTicket(@PathVariable long id) {

        Reserva reserve = reservaService.getReservabyId(id);
        
        if (reserve == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reserve, HttpStatus.OK);
        }
    }


    @PostMapping("/newreserve")
    public ResponseEntity<Reserva> newReserve(@RequestBody Reserva reserva) {
        Reserva reservaSaved = reservaService.reserveTicket(reserva);
        
        if (reservaSaved == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reservaSaved, HttpStatus.CREATED);
        }
    }
}

        