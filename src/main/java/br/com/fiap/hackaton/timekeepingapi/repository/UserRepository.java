package br.com.fiap.hackaton.timekeepingapi.repository;

import br.com.fiap.hackaton.timekeepingapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
