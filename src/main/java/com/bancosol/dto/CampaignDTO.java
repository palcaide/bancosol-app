package com.bancosol.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDTO {

    private Integer id;

    @NotBlank(message = "El nombre de la campaña es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String name;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String description;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "La fecha de fin es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "El objetivo en kilos es obligatorio")
    @Positive(message = "El objetivo debe ser mayor que cero")
    private Double targetKilos;

    @NotBlank(message = "El estado es obligatorio")
    private String status; // PLANNING, ACTIVE, FINISHED
}