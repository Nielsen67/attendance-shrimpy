package com.projects.attendence_shrimpy.controller;

import com.projects.attendence_shrimpy.entity.Attendance;
import com.projects.attendence_shrimpy.entity.Report;
import com.projects.attendence_shrimpy.entity.User;
import com.projects.attendence_shrimpy.model.CreateReportRequest;
import com.projects.attendence_shrimpy.model.ReportResponse;
import com.projects.attendence_shrimpy.model.WebResponse;
import com.projects.attendence_shrimpy.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;


    @PostMapping(
            path = "/reports",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ReportResponse> create(User user, @RequestBody CreateReportRequest request) {
        ReportResponse reportResponse = reportService.create(user, request);
        return WebResponse.<ReportResponse>builder().status(200).message(null).data(reportResponse).build();
    }


    @DeleteMapping(
            path = "/reports",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user, @RequestParam(value = "id") String reportId) {
        reportService.delete(user, reportId);
        return WebResponse.<String>builder().status(200).message("Delete success").data(null).build();
    }

    @GetMapping(
            path = "/reports/today",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ReportResponse>> getToday(User user) {
        return WebResponse.<List<ReportResponse>>builder().status(null).message(null).data(reportService.getToday(user)).build();
    }
}


