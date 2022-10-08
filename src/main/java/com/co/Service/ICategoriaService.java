
package com.co.Service;

import com.co.POJO.Categoria;
import java.util.List;


public interface ICategoriaService {
    
    
    //Metodos de mis categorias
    List<Categoria> listarTodos() ;
    public void guardar (Categoria categoria) ;
    public Categoria buscarPorId( Integer id) ;
    public void eliminar( Integer id) ;
    
    
}
