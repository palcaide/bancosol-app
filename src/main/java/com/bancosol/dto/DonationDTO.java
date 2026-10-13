package com.bancosol.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationDTO {

    private Integer id;

    @NotBlank(message = "La categoría de alimento es obligatoria")
    private String foodCategory;

    @NotNull(message = "La cantidad en kilos es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que cero")
    private Double quantityKilos;

    @NotNull(message = "La fecha de donación es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate donationDate;

    private String status; // PENDING, VERIFIED

    @NotNull(message = "Debe asociar la donación a una campaña")
    private Integer campaignId;
    private String campaignName; // Para mostrarlo en las tablas fácilmente

    @NotNull(message = "Debe indicar la tienda o punto de recogida")
    private Integer storeId;
    private String storeName;

    private Integer userId;
    private String username;
}