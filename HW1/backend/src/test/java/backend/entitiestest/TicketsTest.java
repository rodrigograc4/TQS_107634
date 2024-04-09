package backend.entitiestest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import backend.entity.Tickets;

public class TicketsTest {
    private Tickets ticket;

    @BeforeEach
    public void setUp() {
        ticket = new Tickets("Porto", "Viseu", "BuzzTuga", "2021-10-10 10:00", "2021-10-10 12:00", 10.0);
    }

    @AfterEach
    public void tearDown() {
        ticket = null;
    }

    @Test
    public void testGetOrigem() {
        assertEquals("Porto", ticket.getOrigem());
    }

    @Test
    public void testSetOrigem() {
        ticket.setOrigem("Lisboa");
        assertEquals("Lisboa", ticket.getOrigem());
    }

    @Test
    public void testGetDestino() {
        assertEquals("Viseu", ticket.getDestino());
    }

    @Test
    public void testSetDestino() {
        ticket.setDestino("Coimbra");
        assertEquals("Coimbra", ticket.getDestino());
    }

    @Test
    public void testGetEmpresa() {
        assertEquals("BuzzTuga", ticket.getEmpresa());
    }

    @Test
    public void testSetEmpresa() {
        ticket.setEmpresa("Rede Expressos");
        assertEquals("Rede Expressos", ticket.getEmpresa());
    }

    @Test
    public void testGetPartida() {
        assertEquals("2021-10-10 10:00", ticket.getPartida());
    }

    @Test
    public void testSetPartida() {
        ticket.setPartida("2021-10-10 11:00");
        assertEquals("2021-10-10 11:00", ticket.getPartida());
    }

    @Test
    public void testGetChegada() {
        assertEquals("2021-10-10 12:00", ticket.getChegada());
    }

    @Test
    public void testSetChegada() {
        ticket.setChegada("2021-10-10 13:00");
        assertEquals("2021-10-10 13:00", ticket.getChegada());
    }

    @Test
    public void testGetPreco() {
        assertEquals(10.0, ticket.getPreco());
    }

    @Test
    public void testSetPreco() {
        ticket.setPreco(15.0);
        assertEquals(15.0, ticket.getPreco());
    }
}
