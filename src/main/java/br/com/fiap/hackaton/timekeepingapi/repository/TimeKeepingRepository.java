package br.com.fiap.hackaton.timekeepingapi.repository;

import br.com.fiap.hackaton.timekeepingapi.domain.TimeKeeping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeKeepingRepository extends JpaRepository<TimeKeeping, Long> {
}
