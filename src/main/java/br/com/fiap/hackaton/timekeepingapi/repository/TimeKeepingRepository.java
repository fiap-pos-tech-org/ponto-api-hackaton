package br.com.fiap.hackaton.timekeepingapi.repository;

import br.com.fiap.hackaton.timekeepingapi.domain.TimeKeeping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeKeepingRepository extends JpaRepository<TimeKeeping, Long> {

    @Query(value = "SELECT * FROM time_keeping WHERE user_id = :userId AND time LIKE :timePattern% ORDER BY time", nativeQuery = true)
    List<TimeKeeping> findByUserIdAndTimeLikeOrderByTime(Long userId, String timePattern);

}
