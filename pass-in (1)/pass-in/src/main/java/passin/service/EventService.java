package passin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import passin.domain.attendee.Attendee;
import passin.domain.event.Event;
import passin.dto.event.EventIdDTO;
import passin.dto.event.EventRequestDTO;
import passin.dto.event.EventResponseDTO;
import passin.repository.AttendeeRepository;
import passin.repository.EventRepository;

import java.text.Normalizer;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private EventRepository eventRepository;
    private AttendeeRepository attendeeRepository;
    public EventResponseDTO getEventDetails(String eventId)  {
       Event event = eventRepository.findById(eventId).orElseThrow(
               () -> new RuntimeException("Event not found with  ID:" + eventId)

       );
        List<Attendee> attendeeList = attendeeRepository.findByEventId(eventId);
        return new EventResponseDTO(event,attendeeList.size());
    }
    public EventIdDTO createEvent(
            EventRequestDTO eventDTO){
        Event newEvent = new Event();
        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());

    }

    private String createSlug(String text){
        String normalized =
                Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }





}
