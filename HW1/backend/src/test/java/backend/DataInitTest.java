package backend;

import backend.repository.TicketsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
class DataInitTest {
    
    @Autowired
    private TicketsRepository ticketsRepository;
    
    @BeforeEach
    public void setup() {
        ticketsRepository.deleteAll();
    }

    @Test
    void testDataInitialization() {

        new DataInitializer(ticketsRepository).run();

        assertEquals(64, ticketsRepository.count());
    }
}
