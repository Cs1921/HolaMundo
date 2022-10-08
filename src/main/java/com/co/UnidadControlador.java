
package com.co;

import com.co.POJO.Unidad;
import com.co.Service.IUnidadService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/vistas/unidades")
public class UnidadControlador {
    
    
    @Autowired
    private IUnidadService unidadService;

    @GetMapping("/unidades")
    public String unidades(Model model) {
        List<Unidad> listadoUnidad = unidadService.listarTodos();
        model.addAttribute("titulo", "Lista de unidades");
        model.addAttribute("unidades", listadoUnidad);
        return "/vistas/unidades/unidades";
//        Esta es la ruta:    /vistas/unidades/unidades
    }

    //Crear
    @GetMapping("/crear")
    public String crear(Model model) {
        Unidad unidad = new Unidad();
        model.addAttribute("titulo", "Registrar unidad");
        model.addAttribute("unidad", unidad);
        
        return "/vistas/unidades/unidadForm";
//        Esta es la ruta:    /vistas/unidades/crear
    }
    

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Unidad unidad, BindingResult result, Model model) {
        
         if (result.hasErrors()){
          model.addAttribute("titulo", "Registrar unidad");
        model.addAttribute("unidad", unidad);
        
        return "/vistas/unidades/unidadForm";
         }
        unidadService.guardar(unidad);
//        System.out.println("Se guardo correctamente");
        return "redirect:/vistas/unidades/unidades ";
    }

 //Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id,  Model model) {
        Unidad unidad = unidadService.buscarPorId(id);
        model.addAttribute("titulo", "Editar unidad");
        model.addAttribute("unidad", unidad);
        return "/vistas/unidades/unidadForm";
//        Esta es la ruta:   /vistas/unidades/editar/{id}
    }
    
    //ELiminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
       unidadService.eliminar(id);
        System.out.println("Registro eliminado");
        return "redirect:/vistas/unidades/unidades";
//        Esta es la ruta:   /vistas/unidades/eliminar/{id}
    }
    
}
