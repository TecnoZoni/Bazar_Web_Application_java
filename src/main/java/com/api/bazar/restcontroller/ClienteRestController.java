package com.api.bazar.restcontroller;

import com.api.bazar.model.Cliente;
import com.api.bazar.service.IClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/cliente/verClientes")
    public List<Cliente> verClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/cliente/verCliente/{id}")
    public Cliente verCliente(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @PostMapping("/cliente/crearCliente")
    public String crearCliente(@RequestBody Cliente cliente) {
        clienteService.saveOne(cliente);
        return "Cliente guardado correctamente";
    }

    @PutMapping("/cliente/editarCliente/{id}")
    public String editarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        clienteService.updateOne(cliente, id);
        return "Cliente actualizado correctamente";
    }

    @DeleteMapping("/cliente/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.deleteOne(id);
        return "Cliente eliminado con exito";
    }
}
