package com.dio.medical_appointments.controller;

import com.dio.medical_appointments.domain.model.Doctor;
import com.dio.medical_appointments.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService service;

    @Operation(summary = "Get all doctors", description = "Retrieve a list of all doctors in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of doctors"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<List<Doctor>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Get doctor by ID", description = "Retrieve a specific doctor by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the doctor details"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(@Parameter(description = "ID of the doctor to retrieve") @PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Create a new doctor", description = "Add a new doctor to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody Doctor request) {
        return ResponseEntity.ok(service.save(request));
    }

    @Operation(summary = "Update a doctor's details", description = "Update the information of an existing doctor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor successfully updated"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable Long id, @RequestBody Doctor request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(summary = "Delete a doctor", description = "Remove a doctor from the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Doctor successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
