package com.api.bazar.model;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidad_disponible;

    // Relación Muchos-a-Muchos con Venta (bidireccional)
    @ManyToMany(mappedBy = "listaProductos", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Venta> ventas = new ArrayList<>();

    // Métodos para desvincular ventas
    public void removeVenta(Venta venta) {
        this.ventas.remove(venta);
        venta.getListaProductos().remove(this);
    }
}
