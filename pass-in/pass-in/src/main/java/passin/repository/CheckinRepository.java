package passin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import passin.domain.checkin.Checkin;

import java.util.Optional;


public interface CheckinRepository  extends JpaRepository<Checkin,Integer> {
    Optional<Checkin> findByAttendeeId(String attendeeId);
}
