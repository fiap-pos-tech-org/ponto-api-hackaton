package br.com.fiap.hackaton.clockregistryapi.repository;

import br.com.fiap.hackaton.clockregistryapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
