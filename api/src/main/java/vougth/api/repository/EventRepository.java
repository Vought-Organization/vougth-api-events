package vougth.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vougth.api.domain.Event;


@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}
