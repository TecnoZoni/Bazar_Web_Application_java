package com.api.bazar.service;

import com.api.bazar.model.Venta;
import com.api.bazar.repository.IVentaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

    private final IVentaRepository ventaRepo;

    public VentaService(IVentaRepository ventaRepo) {
        this.ventaRepo = ventaRepo;
    }

    @Override
    public List<Venta> findAll() {
        List<Venta> ventas = this.ventaRepo.findAll();
        return ventas;
    }

    @Override
    public Venta findById(Long id) {
        Venta venta = this.ventaRepo.findById(id).orElse(null);
        return venta;
    }

    @Override
    public void saveOne(Venta venta) {
        this.ventaRepo.save(venta);
    }

    @Override
    public void updateOne(Venta venta, Long id) {
        Venta vent = this.findById(id);

        vent.setFecha_venta(venta.getFecha_venta());
        vent.setListaProductos(venta.getListaProductos());
        vent.setUnCliente(venta.getUnCliente());
        vent.setTotal(venta.getTotal());

        this.ventaRepo.save(vent);
    }

    @Override
    public void deleteOne(Long id) {
        this.ventaRepo.deleteById(id);
    }

}
