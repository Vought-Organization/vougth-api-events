package vougth.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vougth.api.domain.User;
import vougth.api.response.UserResponse;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select new " +
            "vougth.api.response.UserResponse" +
            "(u.idUser, u.userName, u.password) from User u")
    List<UserResponse> getUserResponse();

}
