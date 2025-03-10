package com.projects.attendence_shrimpy.model;

import com.projects.attendence_shrimpy.entity.Attendance;
import com.projects.attendence_shrimpy.entity.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AttendanceResponse {
    private String attendanceId;

    private Integer status;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private List<ReportDescription> reports;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReportDescription  {
        private String description;
    }


}
