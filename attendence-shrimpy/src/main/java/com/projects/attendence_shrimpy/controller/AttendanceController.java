package com.projects.attendence_shrimpy.controller;

import com.projects.attendence_shrimpy.entity.Attendance;
import com.projects.attendence_shrimpy.entity.User;
import com.projects.attendence_shrimpy.model.AttendanceResponse;
import com.projects.attendence_shrimpy.model.CheckResponse;
import com.projects.attendence_shrimpy.model.WebResponse;
import com.projects.attendence_shrimpy.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping(
            path = "/attendances/today",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public AttendanceResponse get (User user){
        return  attendanceService.getToday(user);
    }

    @GetMapping(
            path = "/attendances",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AttendanceResponse>> getAll (User user, @RequestParam(defaultValue = "desc") String sortDirection){
        List<AttendanceResponse> attendanceResponse = attendanceService.getAll(user, sortDirection);
        return WebResponse.<List<AttendanceResponse>>builder().status(null).message(null).data(attendanceResponse).build();
    }

    @PostMapping(
            path = "/attendances/check-in",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CheckResponse checkIn (User user){
        return attendanceService.checkIn(user);
    }

    @PostMapping(
            path = "/attendances/check-out",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CheckResponse checkOut (User user){
        return attendanceService.checkOut(user);
    }
}
