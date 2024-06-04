package com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories;

import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
