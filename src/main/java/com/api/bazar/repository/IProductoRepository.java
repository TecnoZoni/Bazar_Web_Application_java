package com.api.bazar.repository;

import com.api.bazar.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    @Modifying
    @Query("DELETE FROM Venta v WHERE :producto MEMBER OF v.listaProductos")
    void desvincularProductoDeVentas(@Param("producto") Producto producto);

}
