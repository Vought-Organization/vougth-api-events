package vougth.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vougth.api.domain.EventTicket;

public interface EventTicketRepository extends JpaRepository<EventTicket, Integer> {

}
