package passin.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import passin.domain.attendee.Attendee;
import passin.domain.event.Event;
import passin.domain.event.exception.EventFullException;
import passin.domain.event.exception.EventNotFoundException;
import passin.dto.attendee.AttendeeIdDTO;
import passin.dto.attendee.AttendeeRequestDTO;
import passin.dto.event.EventIdDTO;
import passin.dto.event.EventRequestDTO;
import passin.dto.event.EventResponseDTO;

import passin.repository.EventRepository;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private EventRepository eventRepository;
    private AttendeeService  attendeeService;

    public EventResponseDTO getEventDetail(String eventId){
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not fount with ID:" + eventId));
        List<Attendee> attendeesList = this.attendeeService.getAllAttendeesFromEvent(eventId);
        return new EventResponseDTO(event, attendeesList.size());
    }
    public EventIdDTO createEvent(EventRequestDTO eventDTO){
        Event newEvent = new Event();
        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());

    }
    public AttendeeIdDTO registerAttendeeOnEvent(String eventId, AttendeeRequestDTO attendeeRequestDTO){
        this.attendeeService.verifyAttendeeSubscription(attendeeRequestDTO.email(), eventId);

        Event event = this.getEventById(eventId);
        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);

        if(event.getMaximumAttendees() <= attendeeList.size()) throw new EventFullException("Event is full");

        Attendee newAttendee = new Attendee();
        newAttendee.setName(attendeeRequestDTO.name());
        newAttendee.setEmail(attendeeRequestDTO.email());
        newAttendee.setEvent(event);
        newAttendee.setCreatedAt(
                LocalDateTime.now());
        this.attendeeService.registerAttendee(newAttendee);

        return new AttendeeIdDTO(newAttendee.getId());

    }

    private Event getEventById(String eventId){
        return this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not fount with ID:" + eventId));
    }
    private String createSlug(String text){
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }
}




