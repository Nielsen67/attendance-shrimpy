package com.projects.attendence_shrimpy.repository;

import com.projects.attendence_shrimpy.entity.Attendance;
import com.projects.attendence_shrimpy.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, String> {
    List<Report> findAllByAttendanceAttendanceId(String attendanceId);
}
