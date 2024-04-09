package backend.repositorytest;

import backend.repository.ReservaRepository;
import backend.entity.Reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class ReservaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReservaRepository reservaRepository;

    @Test
    @DisplayName("Post reserva")
    public void testReservaRepositoryPost() {
        Reserva reserva = new Reserva();
        reserva.setIdticket(1L); 
        reserva.setNome("João");
        reserva.setEmail("joao@gmail.com");
        reserva.setNumeroCartao("1234 1234 1234 1234");
        reserva.setValidade("12/23");
        reserva.setCvv(123L);
        reserva.setNomeCartao("João Silva");

        Reserva savedReserva = reservaRepository.save(reserva);
        assertNotNull(savedReserva.getId());
    }

    @Test
    @DisplayName("Find reserva by id")
    public void testReservaRepositorybyId() {
        Reserva reserva = new Reserva();
        reserva.setIdticket(1L); 
        reserva.setNome("João");
        reserva.setEmail("joao@gmail.com");
        reserva.setNumeroCartao("1234 1234 1234 1234");
        reserva.setValidade("12/23");
        reserva.setCvv(123L);
        reserva.setNomeCartao("João Silva");
        entityManager.persist(reserva);
        entityManager.flush();

        Reserva found = reservaRepository.findById(reserva.getId()).get();
        
        assertEquals(reserva.getId(), found.getId());
        assertEquals(reserva.getNome(), found.getNome());
        assertEquals(reserva.getEmail(), found.getEmail());
        assertEquals(reserva.getNumeroCartao(), found.getNumeroCartao());
        assertEquals(reserva.getValidade(), found.getValidade());
        assertEquals(reserva.getCvv(), found.getCvv());
        assertEquals(reserva.getNomeCartao(), found.getNomeCartao());

    }    
}
