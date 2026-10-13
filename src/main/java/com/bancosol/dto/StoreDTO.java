package com.bancosol.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {

    private Integer id;

    @NotBlank(message = "El nombre de la tienda es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String name;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede superar los 200 caracteres")
    private String address;

    @NotBlank(message = "La ciudad o distrito es obligatorio")
    @Size(max = 100, message = "La ciudad no puede superar los 100 caracteres")
    private String city;

    private boolean active = true;
}