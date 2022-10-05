
package com.co.Service;

import com.co.DAO.UnidadDAO;
import com.co.POJO.Unidad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadServiceImplements implements IUnidadService {
    
     //Inyeccion de dependencias
    @Autowired
    private UnidadDAO unidadDao;
    
    
    @Override
    public List<Unidad> listarTodos() {
       
        return  (List<Unidad>) unidadDao.findAll(); 
    }

    @Override
    public void guardar(Unidad unidad) {
        unidadDao.save(unidad);
    }

    @Override
    public Unidad buscarPorId(Integer id) {
        return unidadDao.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        unidadDao.deleteById(id);
    }
    
}
