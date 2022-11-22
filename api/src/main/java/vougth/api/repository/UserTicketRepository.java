package vougth.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vougth.api.domain.UserTicket;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, Integer> {

}
