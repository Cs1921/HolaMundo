
package com.co.Service;

import com.co.POJO.Unidad;
import java.util.List;


public interface IUnidadService {
    
     //Metodos de mis unidades de medida
    public List<Unidad> listarTodos() ;
    public void guardar (Unidad unidad) ;
    public Unidad buscarPorId( Integer id) ;
    public void eliminar( Integer id) ;
    
}
