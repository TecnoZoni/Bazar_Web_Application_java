package com.api.bazar.mapper;

import com.api.bazar.dto.VentaDTO;
import com.api.bazar.model.Producto;
import com.api.bazar.model.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    @Mapping(source = "unCliente.nombre", target = "nombreCliente")  // Mapea el nombre del cliente
    @Mapping(source = "listaProductos", target = "listaProductos", qualifiedByName = "productosToNombres")
    VentaDTO toDto(Venta venta);

    @Mapping(target = "unCliente", ignore = true)  // Ignorado porque necesitarías un ClienteService para resolver
    @Mapping(target = "listaProductos", ignore = true)  // Similar aquí
    Venta toEntity(VentaDTO ventaDTO);

    @Named("productosToNombres")
    default List<String> productosToNombres(List<Producto> productos) {
        return productos.stream()
                .map(Producto::getNombre)  // Asume que Producto tiene un campo "nombre"
                .collect(Collectors.toList());
    }
}