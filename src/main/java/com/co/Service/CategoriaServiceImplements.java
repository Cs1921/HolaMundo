
package com.co.Service;

import com.co.DAO.CategoriaDAO;
import com.co.POJO.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImplements implements ICategoriaService {
    
    //Inyeccion de dependencias
    @Autowired
    private CategoriaDAO categoriaDao;

    @Override
    public List<Categoria> listarTodos() {
       
        return  (List<Categoria>) categoriaDao.findAll(); 
    }

    @Override
    public void guardar(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        return categoriaDao.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        categoriaDao.deleteById(id);
    }
    
}
