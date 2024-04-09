package backend.entitiestest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import backend.entity.Reserva;

public class ReservaTest {
    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        reserva = new Reserva(1L, "Joao", "joao@gmail.com", "1234 1234 1234 1234", "12/23", 123L, "Joao Silva");
    }

    @AfterEach
    public void tearDown() {
        reserva = null;
    }

    @Test
    public void testGetTicketId() {
        assertEquals(1L, reserva.getIdticket());
    }

    @Test
    public void testSetTicketId() {
        reserva.setIdticket(2L);
        assertEquals(2L, reserva.getIdticket());
    }

    @Test
    public void testGetNome() {
        assertEquals("Joao", reserva.getNome());
    }

    @Test
    public void testSetNome() {
        reserva.setNome("Maria");
        assertEquals("Maria", reserva.getNome());
    }

    @Test
    public void testGetEmail() {
        assertEquals("joao@gmail.com", reserva.getEmail());
    }

    @Test
    public void testSetEmail() {
        reserva.setEmail("maria@gmail.com");
        assertEquals("maria@gmail.com", reserva.getEmail());
    }

    @Test
    public void testGetCartao() {
        assertEquals("1234 1234 1234 1234", reserva.getNumeroCartao());
    }

    @Test
    public void testSetCartao() {
        reserva.setNumeroCartao("4321 4321 4321 4321");
        assertEquals("4321 4321 4321 4321", reserva.getNumeroCartao());
    }

    @Test
    public void testGetValidade() {
        assertEquals("12/23", reserva.getValidade());
    }

    @Test
    public void testSetValidade() {
        reserva.setValidade("01/25");
        assertEquals("01/25", reserva.getValidade());
    }

    @Test
    public void testGetCvv() {
        assertEquals(123L, reserva.getCvv());
    }

    @Test
    public void testSetCvv() {
        reserva.setCvv(321L);
        assertEquals(321L, reserva.getCvv());
    }

    @Test
    public void testGetNomeCartao() {
        assertEquals("Joao Silva", reserva.getNomeCartao());
    }

    @Test
    public void testSetNomeCartao() {
        reserva.setNomeCartao("Maria Silva");
        assertEquals("Maria Silva", reserva.getNomeCartao());
    }
}
