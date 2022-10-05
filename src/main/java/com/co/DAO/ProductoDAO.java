package com.co.DAO;

import com.co.POJO.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDAO extends CrudRepository<Producto, Integer> {
    
}
