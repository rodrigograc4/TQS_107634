package backend.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.entity.Reserva;
import backend.repository.ReservaRepository;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }
    
    public Reserva reserveTicket(Reserva reserva) {
        Reserva reservaSaved = reservaRepository.save(reserva);
        return reservaSaved;
    }

    public Reserva getReservabyId(long id) {
        Optional<Reserva> optionalReserva = reservaRepository.findById(id);
        if (optionalReserva.isPresent()) {
            return optionalReserva.get();
        } else {
            throw new NoSuchElementException("Reservation with id " + id + " not found");
        }
    }
}