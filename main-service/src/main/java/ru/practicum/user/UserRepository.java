package ru.practicum.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM users LIMIT ?")
    List<User> findUsersWithLimit(Integer limit);

    @Query(nativeQuery = true, value = "WITH temp AS (SELECT id, email, name, ROW_NUMBER() OVER (ORDER BY user_id) " +
            "AS rownum FROM users) SELECT * FROM temp WHERE rownum = ? LIMIT ?")
    List<User> findUsersWithLimitFrom(Integer from, Integer limit);

    Optional<User> findUserByEmail(String email);
}
