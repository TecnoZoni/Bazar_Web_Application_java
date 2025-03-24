package com.api.bazar.controller.api;

import com.api.bazar.dto.ProductoDTO;
import com.api.bazar.model.Producto;
import com.api.bazar.service.IProductoService;
import java.util.ArrayList;
import java.util.List;
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
public class ProductoApiController {

    private final IProductoService productoService;

    public ProductoApiController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoDTO> verProductos() {
        List<Producto> productos = productoService.findAll();

        List<ProductoDTO> productosDTO = new ArrayList<>();

        for (Producto prod : productos) {
            ProductoDTO prodDTO = new ProductoDTO();
            prodDTO.setCodigo_producto(prod.getCodigo_producto());
            prodDTO.setNombre(prod.getNombre());
            prodDTO.setMarca(prod.getMarca());
            prodDTO.setCosto(prod.getCosto());
            prodDTO.setCantidad_disponible(prod.getCantidad_disponible());
            productosDTO.add(prodDTO);
        }

        return productosDTO;
    }

    @GetMapping("/{id}")
    public ProductoDTO verProducto(@PathVariable Long id) {
        Producto prod = productoService.findById(id);
        ProductoDTO prodDTO = new ProductoDTO();
        prodDTO.setCodigo_producto(prod.getCodigo_producto());
        prodDTO.setNombre(prod.getNombre());
        prodDTO.setMarca(prod.getMarca());
        prodDTO.setCosto(prod.getCosto());
        prodDTO.setCantidad_disponible(prod.getCantidad_disponible());

        return prodDTO;
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
