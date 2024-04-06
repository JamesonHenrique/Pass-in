package passin.domain.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="events")
public class Event {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String details;
    @Column(nullable = false,unique = true)
    private String slug;
    @Column(nullable = false, name = "maximum_attendees")
    private Integer maximumAttendees;

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


}
