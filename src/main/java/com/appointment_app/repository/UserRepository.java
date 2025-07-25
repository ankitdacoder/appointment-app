package com.appointment_app.repository;


import com.appointment_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Optional<User> findTopByOrderByIdDesc();
}
