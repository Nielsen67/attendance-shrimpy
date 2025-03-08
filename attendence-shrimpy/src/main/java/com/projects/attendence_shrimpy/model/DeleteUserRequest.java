package com.projects.attendence_shrimpy.model;

import com.projects.attendence_shrimpy.entity.User;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteUserRequest {

    @Size(max = 100)
    private String username;

}
