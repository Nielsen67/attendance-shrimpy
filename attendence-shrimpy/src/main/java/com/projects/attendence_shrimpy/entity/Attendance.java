package com.projects.attendence_shrimpy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attendance", schema = "public")
public class Attendance {

    @Id
    private String attendanceId;

    private Integer status;

    private Timestamp checkInTime;

    private Timestamp checkOutTime;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @OneToMany(mappedBy = "attendance")
    private List <Report> reports;


}
