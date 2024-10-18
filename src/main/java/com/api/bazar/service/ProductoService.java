package com.api.bazar.service;

import com.api.bazar.model.Producto;
import com.api.bazar.model.Venta;
import com.api.bazar.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public void deleteOne(Long id) {
        Producto producto = prodRepo.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Desvincular el producto de todas las ventas
        for (Venta venta : new ArrayList<>(producto.getVentas())) {
            producto.removeVenta(venta);
        }

        // Ahora puedes eliminar el producto
        prodRepo.deleteById(id);
    }

}
