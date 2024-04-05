package passin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import passin.domain.attendee.Attendee;
import passin.domain.attendee.exception.AttendeeAlreadyExistException;
import passin.domain.attendee.exception.AttendeeNotFoundException;
import passin.domain.checkin.Checkin;
import passin.dto.attendee.AttendeeBadgeDTO;
import passin.dto.attendee.AttendeeBadgeResponseDTO;
import passin.dto.attendee.AttendeeDetails;
import passin.dto.attendee.AttendeeListResponseDTO;
import passin.repository.AttendeeRepository;
import passin.repository.CheckinRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AttendeeService {
    private AttendeeRepository
            attendeeRepository;
private CheckinRepository
        checkinRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId){
        return attendeeRepository.findByEventId(eventId);
    }
    public AttendeeListResponseDTO getEventsAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<Checkin>
                    checkIn = this.checkinRepository.findByAttendeeId(attendee.getId());
            LocalDateTime
                    checkedInAt = checkIn.<LocalDateTime>map(Checkin::getCreatedAt).orElse(null);
            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeeListResponseDTO(attendeeDetailsList);
    }
    public void verifyAttendeeSubscription(String email, String eventId){
        Optional<Attendee> isAttendeeRegistered = this.attendeeRepository.findByEventIdAndEmail(eventId, email);
        if(isAttendeeRegistered.isPresent()) throw new AttendeeAlreadyExistException("Attendee already registered");

    }

    public Attendee registerAttendee(Attendee newAttendee){
        this.attendeeRepository.save(newAttendee);
        return newAttendee;
    }



    private Attendee getAttendee(String attendeeId){
        return this.attendeeRepository.findById(attendeeId).orElseThrow(() -> new AttendeeNotFoundException("Attendee not found with ID: " + attendeeId));
    }


    public AttendeeBadgeResponseDTO getAttendeeBadge(String attendeeId, UriComponentsBuilder uriComponentsBuilder){
        Attendee attendee = this.getAttendee(attendeeId);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/check-in").buildAndExpand(attendeeId).toUri().toString();

        AttendeeBadgeDTO
                attendeeBadgeDTO = new AttendeeBadgeDTO(attendee.getName(), attendee.getEmail(), uri, attendee.getEvent().getId());
        return new AttendeeBadgeResponseDTO(attendeeBadgeDTO);
    }
}
