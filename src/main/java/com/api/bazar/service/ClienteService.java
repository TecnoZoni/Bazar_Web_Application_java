package com.api.bazar.service;

import com.api.bazar.model.Cliente;
import com.api.bazar.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository cliRepo;

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = this.cliRepo.findAll();
        return clientes;
    }

    @Override
    public Cliente findById(Long id) {
        Cliente cliente = this.cliRepo.findById(id).orElse(null);
        return cliente;
    }

    @Override
    public void saveOne(Cliente cli) {
        this.cliRepo.save(cli);
    }

    @Override
    public void updateOne(Cliente cli, Long id) {
        Cliente cliente = this.findById(id);
        
        cliente.setNombre(cli.getNombre());
        cliente.setApellido(cli.getApellido());
        cliente.setDni(cli.getDni());

        this.cliRepo.save(cliente);
    }

    @Override
    public void deleteOne(Long id) {
        this.cliRepo.deleteById(id);
    }

}
