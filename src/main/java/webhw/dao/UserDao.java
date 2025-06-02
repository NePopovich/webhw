package webhw.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webhw.model.User;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    Optional findByNameAndLastName(String name, String lastName);

    @Modifying
    @Transactional
    @Query("update User u set u.name = :name, u.lastName = :lastName, u.email = :email where u.id = :id")
    void updateUserById(@Param("user") User user, @Param("id") Long id);
}
