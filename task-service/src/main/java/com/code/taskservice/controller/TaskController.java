package com.code.taskservice.controller;

import com.code.taskservice.dao.Task;
import com.code.taskservice.model.TaskRequest;
import com.code.taskservice.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Tasks API")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Create a new task",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Task request data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Task created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Task.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    public Task createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by ID",
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id",
                            description = "Task ID", schema = @Schema(type = "integer", format = "int64")),
            },
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Task found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Task.class))),
                    @ApiResponse(responseCode = "404", description = "Task not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    public Task getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @GetMapping
    @Operation(summary = "Get all tasks",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Tasks found",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Task.class)))),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    public List<Task> getTasks() {
        return taskService.getTasks();
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Update a task",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Task ID",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    ),
                    @Parameter(
                            name = "taskRequest",
                            description = "Task request data",
                            required = true,
                            schema = @Schema(implementation = TaskRequest.class)
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Task updated",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Task.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Task not found"
                    )
            }
    )
    public Task updateTask(@PathVariable Long id,
                           @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a task",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Task ID",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Task deleted"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Task not found"
                    )
            }
    )
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}