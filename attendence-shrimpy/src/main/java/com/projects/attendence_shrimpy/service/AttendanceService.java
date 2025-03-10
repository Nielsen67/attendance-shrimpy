package com.projects.attendence_shrimpy.service;

import com.projects.attendence_shrimpy.entity.Attendance;
import com.projects.attendence_shrimpy.entity.User;
import com.projects.attendence_shrimpy.model.AttendanceResponse;
import com.projects.attendence_shrimpy.model.CheckResponse;
import com.projects.attendence_shrimpy.model.WebResponse;
import com.projects.attendence_shrimpy.repository.AttendanceRepository;
import com.projects.attendence_shrimpy.utils.AttendanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    private static final int ON_TIME_CHECK_IN = 1;
    private static final int LATE_CHECK_IN = 2;

    public AttendanceResponse getToday(User user){

        Attendance attendance = attendanceRepository.findFirstByUserUsernameAndCheckInTimeBetween(user.getUsername(), Timestamp.valueOf(AttendanceUtil.getStartOfDay()), Timestamp.valueOf(AttendanceUtil.getEndOfDay()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance data is not found"));

        List<AttendanceResponse.ReportDescription> reportDescriptions = attendance.getReports().stream()
                .map(report -> AttendanceResponse.ReportDescription.builder()
                        .description(report.getDescription())
                        .build())
                .collect(Collectors.toList());


        return AttendanceResponse.builder()
                .attendanceId(attendance.getAttendanceId())
                .status(attendance.getStatus())
                .checkInTime(attendance.getCheckInTime().toLocalDateTime())
                .checkOutTime(attendance.getCheckOutTime() != null ? attendance.getCheckOutTime().toLocalDateTime() : null)
                .reports(reportDescriptions)
                .build();
    }



    public List<AttendanceResponse> getAll(User user, String sortDirection) {
        // Fetch all Attendance entities for the given user
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), "checkInTime");
        List<Attendance> attendances = attendanceRepository.findAllByUserUsername(user.getUsername(), sort);

        // Transform the list of Attendance entities into AttendanceResponse DTOs
        return attendances.stream()
                .map(attendance -> {
                    // Convert the list of Report entities into ReportDescription DTOs
                    List<AttendanceResponse.ReportDescription> reportDescriptions = attendance.getReports().stream()
                            .map(report -> AttendanceResponse.ReportDescription.builder()
                                    .description(report.getDescription())
                                    .build())
                            .collect(Collectors.toList());

                    // Build and return the AttendanceResponse DTO
                    return AttendanceResponse.builder()
                            .attendanceId(attendance.getAttendanceId())
                            .status(attendance.getStatus())
                            .checkInTime(attendance.getCheckInTime().toLocalDateTime())
                            .checkOutTime(attendance.getCheckOutTime() != null ? attendance.getCheckOutTime().toLocalDateTime() : null)
                            .reports(reportDescriptions)
                            .build();
                })
                .collect(Collectors.toList());
    }




    public CheckResponse checkIn (User user){
        List<Attendance> attendances = attendanceRepository.findByUserUsernameAndCheckInTimeBetween(user.getUsername(), Timestamp.valueOf(AttendanceUtil.getStartOfDay()), Timestamp.valueOf(AttendanceUtil.getEndOfDay()));
        if (attendances.isEmpty()) {
            Attendance attendance = new Attendance();
            attendance.setAttendanceId(UUID.randomUUID().toString());
            attendance.setUser(user);
            attendance.setCheckInTime(Timestamp.valueOf(LocalDateTime.now()));
            attendance.setStatus(LocalDateTime.now().isBefore(AttendanceUtil.getAbsenceTime()) ? ON_TIME_CHECK_IN : LATE_CHECK_IN);
            attendanceRepository.save(attendance);

            return CheckResponse.builder().status(attendance.getStatus()).message("Successfully checked in").checkInTime(attendance.getCheckInTime().toLocalDateTime()).checkOutTime(null).build();
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User has already checked-in");
        }

    }

    public CheckResponse checkOut (User user){
        Attendance attendance = attendanceRepository.findFirstByUserUsernameAndCheckInTimeBetween(user.getUsername(), Timestamp.valueOf(AttendanceUtil.getStartOfDay()), Timestamp.valueOf(AttendanceUtil.getEndOfDay()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please check-in first"));


        if (attendance.getCheckOutTime() == null){
            attendance.setCheckOutTime(Timestamp.valueOf(LocalDateTime.now()));
            attendanceRepository.save(attendance);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User has already checked-out");
        }


        return CheckResponse.builder().status(attendance.getStatus()).message("Successfully checked-out").checkInTime(attendance.getCheckInTime().toLocalDateTime()).checkOutTime(attendance.getCheckOutTime().toLocalDateTime()).build();
    }

}
