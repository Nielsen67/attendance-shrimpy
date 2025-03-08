package com.projects.attendence_shrimpy.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckResponse {

    private Integer status;

    private String message;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

}
