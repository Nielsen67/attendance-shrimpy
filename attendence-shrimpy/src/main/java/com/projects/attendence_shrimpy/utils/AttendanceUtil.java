package com.projects.attendence_shrimpy.utils;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
public final class AttendanceUtil {

    public static LocalDateTime getStartOfDay() {
        return LocalDate.now().atStartOfDay();
    }

    public static LocalDateTime getEndOfDay() {
        return LocalDate.now().atTime(23, 59, 59);
    }

    public static LocalDateTime getAbsenceTime() {

        return LocalDate.now().atTime(9, 00, 00);
    }
}
