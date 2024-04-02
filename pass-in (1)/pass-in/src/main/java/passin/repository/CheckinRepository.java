package passin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import passin.domain.checkin.Checkin;


public interface CheckinRepository  extends JpaRepository<Checkin,Integer> {
}
