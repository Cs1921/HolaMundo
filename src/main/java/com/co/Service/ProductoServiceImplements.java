
package com.co.Service;

import com.co.DAO.ProductoDAO;
import com.co.POJO.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImplements implements IProductoService {
    
     
     //Inyeccion de dependencias
    @Autowired
    private ProductoDAO productoDao;
    
    
    @Override
    public List<Producto> listarTodos() {
       
        return  (List<Producto>) productoDao.findAll(); 
    }

    @Override
    public void guardar(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    public Producto buscarPorId(Integer id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        productoDao.deleteById(id);
    }
    
}
