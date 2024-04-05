package passin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import passin.domain.attendee.Attendee;
import passin.domain.checkin.Checkin;
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


}
