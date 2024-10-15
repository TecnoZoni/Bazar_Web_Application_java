package com.api.bazar.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {

    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    private String nombreCliente;
    private List<String> listaProductos;
}
