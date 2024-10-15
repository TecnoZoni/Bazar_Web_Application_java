package com.api.bazar.controller;

import com.api.bazar.model.Cliente;
import com.api.bazar.service.IClienteService;
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
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @RequestMapping("/cliente")
    public String verPaginaCliente(Model model) {
        List<Cliente> listaClientes = this.clienteService.findAll();
        model.addAttribute("listaClientes", listaClientes);

        return "cliente";
    }

    @GetMapping("/cliente/nuevo")
    public String mostarFormularioCreacionCliente(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        return "cliente_form";
    }

    @PostMapping("/cliente/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.saveOne(cliente);
        return "redirect:/cliente";
    }

    @RequestMapping("/cliente/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        clienteService.deleteOne(id);
        return "redirect:/cliente";
    }

    @RequestMapping("/cliente/editar/{id}")
    public String actualizarCliente(@PathVariable("id") Long id, @ModelAttribute Cliente clienteEditado) {
        clienteService.updateOne(clienteEditado, id);
        return "redirect:/cliente";
    }
}
