package passin.dto.event;

import lombok.Data;
import lombok.Getter;
import passin.domain.event.Event;

@Data
public class EventResponseDTO {

    EventDetailDTO event;

    public EventResponseDTO(Event event, Integer numberOfAttendees){
        this.event = new EventDetailDTO(
                event.getId(),
                event.getTitle(),
                event.getDetails(),
                event.getSlug(),
                event.getMaximumAttendees(),
                numberOfAttendees
        );
    }

    public EventDetailDTO getEvent() {
        return event;
    }

    public void setEvent(
            EventDetailDTO event) {
        this.event =
                event;
    }
}
