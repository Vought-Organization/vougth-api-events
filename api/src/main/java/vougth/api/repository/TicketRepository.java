package vougth.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vougth.api.domain.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT t FROM Ticket t WHERE t.event.idEvent = :idEvent")
    List<Ticket> findByCodigoEvento(@Param("idEvent") Integer idEvent);
}
