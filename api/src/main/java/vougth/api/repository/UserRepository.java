package vougth.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vougth.api.domain.User;
import vougth.api.response.UserResponseDto;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select new vougth.api.response.UserResponseDto(u.idUser, u.userName, u.password, u.email) from User u")
    List<UserResponseDto> getUserResponse();

    boolean existsByCpf(String cpf);

    User getUserByCpf(String cpf);
}
