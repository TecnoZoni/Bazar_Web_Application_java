package com.api.bazar.mapper;

import com.api.bazar.dto.ProductoDTO;
import com.api.bazar.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    
    ProductoDTO toDto(Producto producto);
    
    @Mapping(target = "ventas", ignore = true)
    Producto toEntity(ProductoDTO productoDTO);
    
    @Mapping(target = "codigo_producto", ignore = true)
    @Mapping(target = "ventas", ignore = true)
    void updateProductoFromDto(ProductoDTO productoDTO, @MappingTarget Producto producto);
}