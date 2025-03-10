package com.projects.attendence_shrimpy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reports", schema = "public")
public class Report {

    @Id
    private String reportId;

    private String description;
    @ManyToOne
    @JoinColumn(name = "attendanceId", referencedColumnName = "attendanceId")
    private Attendance attendance;
}
