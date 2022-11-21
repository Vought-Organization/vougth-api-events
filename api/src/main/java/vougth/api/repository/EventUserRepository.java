package vougth.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vougth.api.domain.EventUser;

@Repository
public interface EventUserRepository  extends JpaRepository<EventUser, Integer> {
}
