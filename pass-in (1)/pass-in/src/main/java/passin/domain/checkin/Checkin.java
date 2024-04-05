package passin.domain.checkin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import passin.domain.attendee.Attendee;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "check_ins")
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToOne
    private Attendee attendee;

    public Integer getId() {
        return id;
    }

    public void setId(
            Integer id) {
        this.id =
                id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt) {
        this.createdAt =
                createdAt;
    }

    public Attendee getAttendee() {
        return attendee;
    }

    public void setAttendee(
            Attendee attendee) {
        this.attendee =
                attendee;
    }
}
