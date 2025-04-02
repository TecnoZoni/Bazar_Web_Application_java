package com.api.bazar.controller.api;

import com.api.bazar.dto.VentaDTO;
import com.api.bazar.mapper.VentaMapper;
import java.util.List;
import com.api.bazar.model.Venta;
import com.api.bazar.service.IVentaService;
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
@RequestMapping("/api/venta")
@PreAuthorize("permitAll()")
public class VentaApiController {

    private final IVentaService ventaService;
    private final VentaMapper ventaMapper;

    public VentaApiController(IVentaService ventaService, VentaMapper ventaMapper) {
        this.ventaService = ventaService;
        this.ventaMapper = ventaMapper;
    }

    @GetMapping
    public List<VentaDTO> verVentas() {
        return ventaService.findAll().stream()
                .map(ventaMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public VentaDTO verVenta(@PathVariable Long id) {
        return ventaMapper.toDto(ventaService.findById(id));
    }

    @PostMapping
    public String crearVenta(@RequestBody Venta venta) {
        ventaService.saveOne(venta);
        return "Venta guardado correctamente";
    }

    @PutMapping("/{id}")
    public String editarVenta(@PathVariable Long id, @RequestBody Venta venta) {
        ventaService.updateOne(venta, id);
        return "Venta actualizado correctamente";
    }

    @DeleteMapping("/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        ventaService.deleteOne(id);
        return "Venta eliminado con exito";
    }

}
