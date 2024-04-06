package rocketseat.com.passin.domain.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    public String getId() {
        return id;
    }

    public void setId(
            String id) {
        this.id =
                id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(
            String title) {
        this.title =
                title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(
            String details) {
        this.details =
                details;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(
            String slug) {
        this.slug =
                slug;
    }

    public Integer getMaximumAttendees() {
        return maximumAttendees;
    }

    public void setMaximumAttendees(
            Integer maximumAttendees) {
        this.maximumAttendees =
                maximumAttendees;
    }

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false, name="maximum_attendees")
    private Integer maximumAttendees;
}
