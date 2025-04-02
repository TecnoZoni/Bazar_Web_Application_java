package com.api.bazar.controller.api;

import com.api.bazar.dto.ProductoDTO;
import com.api.bazar.mapper.ProductoMapper;
import com.api.bazar.model.Producto;
import com.api.bazar.service.IProductoService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/producto")
@PreAuthorize("permitAll()")
public class ProductoApiController {

    private final IProductoService productoService;
    private final ProductoMapper productoMapper;

    public ProductoApiController(IProductoService productoService, ProductoMapper productoMapper) {
        this.productoService = productoService;
        this.productoMapper = productoMapper;
    }

    @GetMapping
    public List<ProductoDTO> verProductos() {
        return productoService.findAll().stream()
                .map(productoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductoDTO verProducto(@PathVariable Long id) {
        return productoMapper.toDto(productoService.findById(id));
    }

    @PostMapping
    public String crearProducto(@RequestBody Producto producto) {
        productoService.saveOne(producto);
        return "Producto guardado correctamente";
    }

    @PutMapping("/{id}")
    public String editarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        productoService.updateOne(producto, id);
        return "Producto actualizado correctamente";
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.deleteOne(id);
        return "Producto eliminado con exito";
    }

}
