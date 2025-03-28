package com.dio.medical_appointments.controller;

import com.dio.medical_appointments.domain.model.Consultation;
import com.dio.medical_appointments.service.ConsultationService;
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
@RequestMapping("/consultation")
public class ConsultationController {
    private final ConsultationService service;

    @Operation(summary = "Get all consultations", description = "Retrieve a list of all consultations in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of consultations"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<List<Consultation>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Get consultation by ID", description = "Retrieve a specific consultation by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the consultation details"),
            @ApiResponse(responseCode = "404", description = "Consultation not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getById(@Parameter(description = "ID of the consultation to retrieve") @PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Create a new consultation", description = "Create a new consultation in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consultation successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<Consultation> create(@RequestBody Consultation request) {
        return ResponseEntity.ok(service.save(request));
    }

    @Operation(summary = "Update consultation details", description = "Update the information of an existing consultation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consultation successfully updated"),
            @ApiResponse(responseCode = "404", description = "Consultation not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Consultation> update(@Parameter(description = "ID of the consultation to update") @PathVariable Long id, @RequestBody Consultation request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(summary = "Delete consultation", description = "Delete a consultation from the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Consultation successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Consultation not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "ID of the consultation to delete")@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
