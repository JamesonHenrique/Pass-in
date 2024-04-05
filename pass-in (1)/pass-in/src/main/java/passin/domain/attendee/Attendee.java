package passin.domain.attendee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import passin.domain.event.Event;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attendees")
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }

    public void setId(
            String id) {
        this.id =
                id;
    }

    public String getName() {
        return name;
    }

    public void setName(
            String name) {
        this.name =
                name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(
            String email) {
        this.email =
                email;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(
            Event event) {
        this.event =
                event;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt) {
        this.createdAt =
                createdAt;
    }
}
