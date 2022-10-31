package vougth.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vougth.api.domain.User;
import vougth.api.dto.CredencialDto;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select new " +
            "vought.api.dto.CredencialDto" +
            "(m.username, m.password) from User")
        // @Query("select m from Motorista m")
    List<CredencialDto> getCredenciais();
}
