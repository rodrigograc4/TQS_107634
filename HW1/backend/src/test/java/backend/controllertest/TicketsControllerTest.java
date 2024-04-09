package backend.controllertest;

import backend.controller.TicketsController;
import backend.entity.Tickets;
import backend.service.TicketsService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(TicketsController.class)
public class TicketsControllerTest {
    

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TicketsService ticketsService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    @DisplayName("Get all tickets")
    public void testTicketsControllerGetAll() throws Exception {
        Tickets ticket1 = new Tickets("Porto", "Viseu", "BuzzTuga", "2021-10-10 10:00", "2021-10-10 12:00", 10.0);
        Tickets ticket2 = new Tickets("Coimbra", "Fatima", "Autocarros de Portugal", "2021-03-12 14:00", "2021-03-12 17:00", 15.0);
        Tickets ticket3 = new Tickets("Lisboa", "Porto", "Rede Transportes Portugal", "2021-05-20 08:00", "2021-05-20 10:00", 12.0);

        List<Tickets> allTickets = Arrays.asList(ticket1, ticket2, ticket3);

        when(ticketsService.getAllTickets(null, null, null, null, null, null)).thenReturn(allTickets);

        mvc.perform(get("/tickets").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].origem", is("Porto")))
            .andExpect(jsonPath("$[1].origem", is("Coimbra")))
            .andExpect(jsonPath("$[2].origem", is("Lisboa")));

        verify(ticketsService, times(1)).getAllTickets(null, null, null, null, null, null);
    }

    @Test
    @DisplayName("Get ticket by id")
    public void testTicketsControllerGetById() throws Exception {
        Tickets ticket = new Tickets("Porto", "Viseu", "BuzzTuga", "2021-10-10 10:00", "2021-10-10 12:00", 10.0);

        when(ticketsService.getTicketById(1)).thenReturn(ticket);

        mvc.perform(get("/tickets/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.origem", is("Porto")));

        verify(ticketsService, times(1)).getTicketById(1);
    }

    @Test
    @DisplayName("Get ticket by id not found")
    public void testTicketsControllerGetByIdNotFound() throws Exception {
        when(ticketsService.getTicketById(1)).thenReturn(null);

        mvc.perform(get("/tickets/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        verify(ticketsService, times(1)).getTicketById(1);
    }


    @Test
    @DisplayName("Get ticket by origem, partida, destino and chegada")
    public void testTicketsControllerGetByOrigemPartidaDestinoChegada() throws Exception {
        Tickets ticket1 = new Tickets("Porto", "Viseu", "BuzzTuga", "2021-10-10 10:00", "2021-10-10 12:00", 10.0);
        Tickets ticket2 = new Tickets("Porto", "Viseu", "Autocarros de Portugal", "2021-10-10 14:00", "2021-10-10 16:00", 10.0);
        Tickets ticket3 = new Tickets("Viseu", "Porto", "Rede Transportes Portugal", "2021-10-10 10:00", "2021-10-10 12:00", 10.0);
    
        List<Tickets> allTickets = Arrays.asList(ticket1, ticket2, ticket3);

        when(ticketsService.getAllTickets("Porto", "Viseu", null, "2021-10-10 10:00", "2021-10-10 12:00", null)).thenReturn(allTickets);

        mvc.perform(get("/tickets")
            .param("origem", "Porto")
            .param("destino", "Viseu")
            .param("partida", "2021-10-10 10:00")
            .param("chegada", "2021-10-10 12:00")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].origem", is("Porto")))
            .andExpect(jsonPath("$[1].origem", is("Porto")))
            .andExpect(jsonPath("$[2].origem", is("Viseu")));

        verify(ticketsService, times(1)).getAllTickets("Porto", "Viseu", null, "2021-10-10 10:00", "2021-10-10 12:00", null);
    }
}