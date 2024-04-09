package backend.servicetest;

import backend.entity.Reserva;
import backend.repository.ReservaRepository;
import backend.service.ReservaService;

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
public class ReservaServiceTest {
    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    @BeforeEach
    public void setUp() {
        reset(reservaRepository);
    }

    @Test
    @DisplayName("Get reserva by id")
    public void testReservaServiceGetById() {
        Reserva reserva = new Reserva(1L, "Joao", "joao@gmail.com", "1234 1234 1234 1234", "12/23", 123L, "Joao Silva");
        Reserva reserva2 = new Reserva(2L, "Maria", "maria@gmail.com", "1234 1234 1234 1234", "12/23", 123L, "Maria Silva");
        reserva.setId(1L);
        reserva2.setId(2L);

        Mockito.when(reservaRepository.findById(1L)).thenReturn(java.util.Optional.of(reserva));

        Reserva reservaFound = reservaService.getReservabyId(1L);
        
        assertThat(reservaFound).isEqualTo(reserva);
        Mockito.verify(reservaRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("Post reserva")
    public void testReservaServicePost() {
        Reserva reserva = new Reserva(1L, "Joao", "joao@gmail.com", "1234 1234 1234 1234", "12/23", 123L, "Joao Silva");

        Mockito.when(reservaRepository.save(reserva)).thenReturn(reserva);

        Reserva reservaSaved = reservaService.reserveTicket(reserva);

        assertThat(reservaSaved).isEqualTo(reserva);
        Mockito.verify(reservaRepository, Mockito.times(1)).save(reserva);
    }
}
