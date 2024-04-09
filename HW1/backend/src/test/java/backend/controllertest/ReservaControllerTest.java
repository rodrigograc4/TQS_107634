package backend.controllertest;

import backend.controller.ReservaController;
import backend.entity.Reserva;
import backend.service.ReservaService;
import backend.service.TicketsService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TicketsService ticketsService;

    @MockBean
    private ReservaService reservaService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    @DisplayName("Post reserva")
    public void testReservaControllerPost() throws Exception {
        Reserva reserva = new Reserva(1L, "Joao", "joao@gmail.com", "1234 1234 1234 1234", "12/23", 123L, "Joao Silva");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String reservaJson = objectMapper.writeValueAsString(reserva);

        when(reservaService.reserveTicket(Mockito.any(Reserva.class))).thenReturn(reserva);

        mvc.perform(post("/newreserve").contentType(MediaType.APPLICATION_JSON).content(reservaJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.idticket", is(1)))
            .andExpect(jsonPath("$.nome", is("Joao")))
            .andExpect(jsonPath("$.email", is("joao@gmail.com")))
            .andExpect(jsonPath("$.numeroCartao", is("1234 1234 1234 1234")))
            .andExpect(jsonPath("$.validade", is("12/23")))
            .andExpect(jsonPath("$.cvv", is(123)))
            .andExpect(jsonPath("$.nomeCartao", is("Joao Silva")));

        verify(reservaService, times(1)).reserveTicket(Mockito.any(Reserva.class));
    }

    @Test
    @DisplayName("Get reserva by id")
    public void testReservaControllerGetById() throws Exception {
        Reserva reserva = new Reserva(1L, "Joao", "joao@gmail.com", "1234 1234 1234 1234", "12/23", 123L, "Joao Silva");

        when(reservaService.getReservabyId(1L)).thenReturn((reserva));

        mvc.perform(get("/reserve/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idticket", is(1)))
            .andExpect(jsonPath("$.nome", is("Joao")))
            .andExpect(jsonPath("$.email", is("joao@gmail.com")))
            .andExpect(jsonPath("$.numeroCartao", is("1234 1234 1234 1234")))
            .andExpect(jsonPath("$.validade", is("12/23")))
            .andExpect(jsonPath("$.cvv", is(123)))
            .andExpect(jsonPath("$.nomeCartao", is("Joao Silva")));

        verify(reservaService, times(1)).getReservabyId(1L);
    }

    @Test
    @DisplayName("Get reserva by id not found")
    public void testReservaControllerGetByIdNotFound() throws Exception {
        when(reservaService.getReservabyId(1L)).thenReturn(null);

        mvc.perform(get("/reserve/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        verify(reservaService, times(1)).getReservabyId(1L);
    }
}
