package vougth.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vougth.api.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
