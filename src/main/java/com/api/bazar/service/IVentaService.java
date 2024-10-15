package com.api.bazar.service;

import com.api.bazar.model.Venta;
import java.util.List;

public interface IVentaService {

    List<Venta> findAll();

    Venta findById(Long id);

    void saveOne(Venta venta);

    void updateOne(Venta venta, Long id);

    void deleteOne(Long id);
}
