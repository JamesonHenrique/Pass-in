package passin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import passin.domain.attendee.Attendee;


public interface AttendeeRepository extends JpaRepository<Attendee,String> {
}
