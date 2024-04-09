package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import backend.entity.Tickets;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {

    @Query("SELECT t FROM Tickets t WHERE t.origem = ?1 AND t.partida = ?2 AND t.destino = ?3 AND t.chegada = ?4")
    public Tickets findByOrigemPartidaDestinoChegada(String origem, String partida, String destino, String chegada);
    
}