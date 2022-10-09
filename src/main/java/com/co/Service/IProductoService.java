
package com.co.Service;

import com.co.POJO.Producto;
import java.util.List;

public interface IProductoService {
    
        //Metodos de mis productos
    public List<Producto> listarTodos() ;
    public void guardar (Producto producto) ;
    public Producto buscarPorId( Integer id) ;
    public void eliminar( Integer id) ;
    
}