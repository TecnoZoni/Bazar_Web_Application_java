package com.api.bazar.controller;

import com.api.bazar.model.Cliente;
import com.api.bazar.model.Producto;
import com.api.bazar.model.Venta;
import com.api.bazar.service.IClienteService;
import com.api.bazar.service.IProductoService;
import com.api.bazar.service.IVentaService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IProductoService productoService;

    @RequestMapping("/venta")
    public String verPaginaVenta(Model model) {
        List<Venta> listaVentas = this.ventaService.findAll();
        model.addAttribute("listaVentas", listaVentas);

        return "venta";
    }

    @GetMapping("/venta/nueva")
    public String mostrarFormularioCreacionVenta(Model model) {
        Venta nuevaVenta = new Venta();
        model.addAttribute("venta", nuevaVenta);

        // Obtener la lista de clientes para seleccionar en el formulario
        List<Cliente> listaClientes = clienteService.findAll();
        model.addAttribute("listaClientes", listaClientes);

        List<Producto> listaProductos = productoService.findAll();
        model.addAttribute("listaProductos", listaProductos);

        return "venta_form";
    }

    @PostMapping("/venta/guardar")
    public String guardarVenta(@ModelAttribute("venta") Venta venta) {
        // Establecer la fecha actual para la venta
        venta.setFecha_venta(LocalDate.now());

        // Buscar el cliente por ID y asignarlo a la venta
        Cliente cliente = clienteService.findById(venta.getUnCliente().getId_cliente());
        venta.setUnCliente(cliente);

        // Buscar y asignar los productos seleccionados
        List<Producto> productosSeleccionados = new ArrayList<>();
        for (Producto producto : venta.getListaProductos()) {
            Producto prod = productoService.findById(producto.getCodigo_producto());
            productosSeleccionados.add(prod);
        }
        venta.setListaProductos(productosSeleccionados);

        // Guardar la venta
        ventaService.saveOne(venta);

        return "redirect:/venta"; // Redirige a la lista de ventas después de guardar
    }

    @RequestMapping("/venta/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        ventaService.deleteOne(id);
        return "redirect:/venta";
    }

    @RequestMapping("/venta/editar/{id}")
    public ModelAndView formEditarVenta(@PathVariable Long id) {
        ModelAndView modelo = new ModelAndView("venta_form_editar");

        Venta venta = ventaService.findById(id);
        modelo.addObject("venta", venta);

        List<Cliente> listaClientes = clienteService.findAll();
        modelo.addObject("listaClientes", listaClientes);

        List<Producto> listaProductos = productoService.findAll();
        modelo.addObject("listaProductos", listaProductos);

        return modelo;
    }

    @PostMapping("/venta/editar")
    public String editarVenta(@ModelAttribute("venta") Venta venta) {
        Venta ventaExistente = ventaService.findById(venta.getCodigo_venta());

        // Actualizar los campos de la venta existente
        ventaExistente.setTotal(venta.getTotal());
        ventaExistente.setUnCliente(clienteService.findById(venta.getUnCliente().getId_cliente()));

        // Actualizar la lista de productos
        List<Producto> productosSeleccionados = new ArrayList<>();
        for (Producto producto : venta.getListaProductos()) {
            Producto prod = productoService.findById(producto.getCodigo_producto());
            productosSeleccionados.add(prod);
        }
        ventaExistente.setListaProductos(productosSeleccionados);

        // Guardar la venta editada
        ventaService.saveOne(ventaExistente);

        return "redirect:/venta"; // Redirige a la lista de ventas después de guardar
    }

}
