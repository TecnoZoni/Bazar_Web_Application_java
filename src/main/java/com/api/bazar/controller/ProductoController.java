package com.api.bazar.controller;

import com.api.bazar.model.Producto;
import com.api.bazar.service.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductoController {

    @Autowired
    private IProductoService prodServi;

    @RequestMapping("/producto")
    public String verPaginaProducto(Model model) {
        List<Producto> listaProductos = prodServi.findAll();
        model.addAttribute("listaProductos", listaProductos);

        return "producto";
    }

    @GetMapping("/producto/nuevo")
    public String mostarFormularioCreacionProducto(Model model) {
        Producto producto = new Producto();
        model.addAttribute("producto", producto);

        return "producto_form";
    }

    @PostMapping("/producto/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        prodServi.saveOne(producto);
        return "redirect:/producto";
    }

    @RequestMapping("/producto/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        prodServi.deleteOne(id);
        return "redirect:/producto";
    }

    @RequestMapping("/producto/editar/{id}")
    public String actualizarProducto(@PathVariable("id") Long id, @ModelAttribute Producto productoEditado) {
        prodServi.updateOne(productoEditado, id);
        return "redirect:/producto";
    }

}
