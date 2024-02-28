package com.code.taskservice.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
    private String name;

    private String description;

    private boolean isCompleted;
}
