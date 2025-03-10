package com.projects.attendence_shrimpy.service;

import com.projects.attendence_shrimpy.entity.Attendance;
import com.projects.attendence_shrimpy.entity.Report;
import com.projects.attendence_shrimpy.entity.User;
import com.projects.attendence_shrimpy.model.*;
import com.projects.attendence_shrimpy.repository.AttendanceRepository;
import com.projects.attendence_shrimpy.repository.ReportRepository;
import com.projects.attendence_shrimpy.utils.AttendanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Transactional
    public ReportResponse create (User user, CreateReportRequest request){

        Attendance attendance = attendanceRepository.findFirstByUserUsernameAndCheckInTimeBetween(user.getUsername(), Timestamp.valueOf(AttendanceUtil.getStartOfDay()), Timestamp.valueOf(AttendanceUtil.getEndOfDay()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance data is not found"));

        Report report = new Report();
        report.setReportId(UUID.randomUUID().toString());
        report.setDescription(request.getDescription());
        report.setAttendance(attendance);

        reportRepository.save(report);

        return ReportResponse.builder().reportId(report.getReportId()).description(report.getDescription()).build();
    }

    @Transactional
    public void delete (User user, String reportId){
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found"));
        reportRepository.delete(report);
    }

    public List<ReportResponse> getToday (User user){

        Attendance attendance = attendanceRepository.findFirstByUserUsernameAndCheckInTimeBetween(user.getUsername(), Timestamp.valueOf(AttendanceUtil.getStartOfDay()), Timestamp.valueOf(AttendanceUtil.getEndOfDay()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance data is not found"));

        List<Report> reports = reportRepository.findAllByAttendanceAttendanceId(attendance.getAttendanceId());
        return reports.stream()
                .map(report -> new ReportResponse(report.getReportId(), report.getDescription()))
                .collect(Collectors.toList());
    }

}
