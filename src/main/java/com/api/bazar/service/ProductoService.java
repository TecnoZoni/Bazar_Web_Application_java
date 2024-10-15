package com.api.bazar.service;

import com.api.bazar.model.Producto;
import com.api.bazar.repository.IProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository prodRepo;

    @Override
    public List<Producto> findAll() {
        List<Producto> productos = this.prodRepo.findAll();
        return productos;
    }

    @Override
    public Producto findById(Long id) {
        Producto prod = this.prodRepo.findById(id).orElse(null);
        return prod;
    }

    @Override
    public void saveOne(Producto producto) {
        this.prodRepo.save(producto);
    }

    @Override
    public void updateOne(Producto producto, Long id) {
        Producto prod = this.findById(id);

        prod.setNombre(producto.getNombre());
        prod.setMarca(producto.getMarca());
        prod.setCosto(producto.getCosto());
        prod.setCantidad_disponible(producto.getCantidad_disponible());

        this.prodRepo.save(prod);
    }

    @Override
    public void deleteOne(Long id) {
        this.prodRepo.deleteById(id);
    }

}
