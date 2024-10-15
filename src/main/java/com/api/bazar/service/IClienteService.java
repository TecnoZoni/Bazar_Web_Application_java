package com.api.bazar.service;

import com.api.bazar.model.Cliente;
import java.util.List;

public interface IClienteService {

    List<Cliente> findAll();

    Cliente findById(Long id);

    void saveOne(Cliente cli);

    void updateOne(Cliente cli, Long id);

    void deleteOne(Long id);
}
