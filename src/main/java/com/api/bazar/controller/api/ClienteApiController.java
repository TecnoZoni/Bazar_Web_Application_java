package com.api.bazar.controller.api;

import com.api.bazar.model.Cliente;
import com.api.bazar.service.IClienteService;
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
@RequestMapping("/api/cliente")
public class ClienteApiController {

    private final IClienteService clienteService;

    public ClienteApiController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> verClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Cliente verCliente(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @PostMapping
    public String crearCliente(@RequestBody Cliente cliente) {
        clienteService.saveOne(cliente);
        return "Cliente guardado correctamente";
    }

    @PutMapping("/{id}")
    public String editarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        clienteService.updateOne(cliente, id);
        return "Cliente actualizado correctamente";
    }

    @DeleteMapping("/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.deleteOne(id);
        return "Cliente eliminado con exito";
    }
}
