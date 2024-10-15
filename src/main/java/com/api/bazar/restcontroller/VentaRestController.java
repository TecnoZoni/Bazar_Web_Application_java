package com.api.bazar.restcontroller;

import com.api.bazar.dto.VentaDTO;
import com.api.bazar.model.Producto;
import java.util.List;
import com.api.bazar.model.Venta;
import com.api.bazar.service.IVentaService;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaRestController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping("/venta/verVentas")
    public List<VentaDTO> verVentas() {
        List<Venta> ventas = ventaService.findAll();
        List<VentaDTO> ventasDTO = new ArrayList<>();

        for (Venta vent : ventas) {
            VentaDTO ventDTO = new VentaDTO();
            ventDTO.setCodigo_venta(vent.getCodigo_venta());
            ventDTO.setFecha_venta(vent.getFecha_venta());
            ventDTO.setTotal(vent.getTotal());
            ventDTO.setNombreCliente(vent.getUnCliente().getNombre() + " " + vent.getUnCliente().getApellido());

            List<String> nombresProductos = vent.getListaProductos().stream()
                    .map(Producto::getNombre)
                    .collect(Collectors.toList());
            ventDTO.setListaProductos(nombresProductos);

            ventasDTO.add(ventDTO);
        }

        return ventasDTO;
    }

    @GetMapping("/venta/verVenta/{id}")
    public VentaDTO verVenta(@PathVariable Long id) {
        Venta vent = ventaService.findById(id);
        VentaDTO ventDTO = new VentaDTO();

        ventDTO.setCodigo_venta(vent.getCodigo_venta());
        ventDTO.setFecha_venta(vent.getFecha_venta());
        ventDTO.setTotal(vent.getTotal());

        ventDTO.setNombreCliente(vent.getUnCliente().getNombre());

        List<String> nombresProductos = vent.getListaProductos().stream()
                .map(Producto::getNombre)
                .collect(Collectors.toList());
        ventDTO.setListaProductos(nombresProductos);

        return ventDTO;
    }

    @PostMapping("/venta/crearVenta")
    public String crearVenta(@RequestBody Venta venta) {
        ventaService.saveOne(venta);
        return "Venta guardado correctamente";
    }

    @PutMapping("/venta/editarVenta/{id}")
    public String editarVenta(@PathVariable Long id, @RequestBody Venta venta) {
        ventaService.updateOne(venta, id);
        return "Venta actualizado correctamente";
    }

    @DeleteMapping("/venta/eliminarVenta/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        ventaService.deleteOne(id);
        return "Venta eliminado con exito";
    }

}
