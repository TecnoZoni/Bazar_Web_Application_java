package com.api.bazar.controller.web;

import com.api.bazar.model.Producto;
import com.api.bazar.service.IProductoService;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producto")
@PreAuthorize("hasRole('ADMIN')")
public class ProductoController {

    private final IProductoService prodServi;

    public ProductoController(IProductoService prodServi) {
        this.prodServi = prodServi;
    }

    @GetMapping
    public String verPaginaProducto(Model model) {
        List<Producto> listaProductos = prodServi.findAll();
        model.addAttribute("listaProductos", listaProductos);

        return "producto";
    }

    @GetMapping("/nuevo")
    public String mostarFormularioCreacionProducto(Model model) {
        Producto producto = new Producto();
        model.addAttribute("producto", producto);

        return "producto_form";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        prodServi.saveOne(producto);
        return "redirect:/producto";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        prodServi.deleteOne(id);
        return "redirect:/producto";
    }

    @RequestMapping("/editar/{id}")
    public String actualizarProducto(@PathVariable("id") Long id, @ModelAttribute Producto productoEditado) {
        prodServi.updateOne(productoEditado, id);
        return "redirect:/producto";
    }

}
