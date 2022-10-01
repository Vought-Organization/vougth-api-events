package vougth.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vougth.api.domain.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
