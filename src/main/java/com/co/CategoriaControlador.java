package com.co;

import com.co.POJO.Categoria;
import com.co.Service.ICategoriaService;
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
@RequestMapping("/vistas/categorias")
public class CategoriaControlador {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/categorias")
    public String categorias(Model model) {
        List<Categoria> listadoCategoria = categoriaService.listarTodos();
        model.addAttribute("titulo", "Lista de categorías");
        model.addAttribute("categorias", listadoCategoria);
        return "/vistas/categorias/categorias";
//        Esta es la ruta:    /vistas/categorias/categorias
    }

    //Crear
    @GetMapping("/crear")
    public String crear(Model model) {
        Categoria categoria = new Categoria();
        model.addAttribute("titulo", "Registrar categoría");
        model.addAttribute("categoria", categoria);

        return "/vistas/categorias/categoriaForm";
//        Esta es la ruta:    /vistas/categorias/crear
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Categoria categoria, BindingResult result, Model model) {

        //Validacion de errores
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registrar categoría");
            model.addAttribute("categoria", categoria);

            return "/vistas/categorias/categoriaForm";

        }

        categoriaService.guardar(categoria);
//        System.out.println("Se guardo correctamente");
        return "redirect:/vistas/categorias/categorias ";
    }

    //Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {

        Categoria categoria = null;
        if (id > 0) {
            categoria = categoriaService.buscarPorId(id);

            if (categoria == null) {

                return "redirect:/vistas/categorias/categorias";
            }
        } else {
            return "redirect:/vistas/categorias/categorias";
        }

        model.addAttribute("titulo", "Editar categoría");
        model.addAttribute("categoria", categoria);
        return "/vistas/categorias/categoriaForm";
//        Esta es la ruta:   /vistas/categorias/editar/{id}
    }

    //ELiminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {

        Categoria categoria = null;
        if (id > 0) {
            categoria = categoriaService.buscarPorId(id);

            if (categoria == null) {

                return "redirect:/vistas/categorias/categorias";
            }
        } else {
            return "redirect:/vistas/categorias/categorias";
        }

        categoriaService.eliminar(id);
        System.out.println("Registro eliminado");
        return "redirect:/vistas/categorias/categorias";
//        Esta es la ruta:   /vistas/categorias/eliminar/{id}
    }
}
