package com.projects.attendence_shrimpy.repository;


import com.projects.attendence_shrimpy.entity.Attendance;
import com.projects.attendence_shrimpy.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, String> {
    Optional<Attendance> findFirstByUserUsernameAndCheckInTimeBetween(String username, Timestamp start, Timestamp end);

    List<Attendance> findByUserUsernameAndCheckInTimeBetween(String username, Timestamp start, Timestamp end);

    List<Attendance> findByUserUsernameAndCheckOutTimeBetween(String username, Timestamp start, Timestamp end);

    List<Attendance> findAllByUserUsername(String username, Sort sort);

}
