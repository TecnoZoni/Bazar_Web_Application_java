package com.api.bazar.controller.web;

import com.api.bazar.model.Cliente;
import com.api.bazar.service.IClienteService;
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
@RequestMapping("/cliente")
@PreAuthorize("hasRole('ADMIN')")
public class ClienteController {

    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String verPaginaCliente(Model model) {
        List<Cliente> listaClientes = this.clienteService.findAll();
        model.addAttribute("listaClientes", listaClientes);

        return "cliente";
    }

    @GetMapping("/nuevo")
    public String mostarFormularioCreacionCliente(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        return "cliente_form";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.saveOne(cliente);
        return "redirect:/cliente";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        clienteService.deleteOne(id);
        return "redirect:/cliente";
    }

    @RequestMapping("/editar/{id}")
    public String actualizarCliente(@PathVariable("id") Long id, @ModelAttribute Cliente clienteEditado) {
        clienteService.updateOne(clienteEditado, id);
        return "redirect:/cliente";
    }
}
