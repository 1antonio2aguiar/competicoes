package com.competicoesapi.repository;

import com.competicoesapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT get_next_kripton_users_id()", nativeQuery = true)
    Long getNextKriptonUserId();
    Optional<User> findByEmail(String email);
}
