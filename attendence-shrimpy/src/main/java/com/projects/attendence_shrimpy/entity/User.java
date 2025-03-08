package com.projects.attendence_shrimpy.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name =  "users", schema = "public")
public class User {

    @Id
    private String username;

    private String password;

    private String name;

    private String phone;

    private String token;

    @Column (name = "token_expired_at")
    private Long tokenExpiredAt;

    @OneToMany(mappedBy = "user")
    private List<Attendance> attendances;
}
