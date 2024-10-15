package com.api.bazar.service;

import com.api.bazar.model.Producto;
import java.util.List;

public interface IProductoService {

    List<Producto> findAll();

    Producto findById(Long id);

    void saveOne(Producto producto);

    void updateOne(Producto producto, Long id);

    void deleteOne(Long id);
}
