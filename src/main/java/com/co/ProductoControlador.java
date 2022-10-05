
package com.co;

import com.co.POJO.Categoria;
import com.co.POJO.Producto;
import com.co.POJO.Unidad;
import com.co.Service.ICategoriaService;
import com.co.Service.IProductoService;
import com.co.Service.IUnidadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vistas/productos")
public class ProductoControlador {
    
     
    @Autowired
    private IProductoService productoService;
    @Autowired
    private ICategoriaService categoriaService;
    @Autowired
    private IUnidadService unidadService;

    @GetMapping("/productos")
    public String productos(Model model) {
        List<Producto> listadoProducto = productoService.listarTodos();
        model.addAttribute("titulo", "Lista de productos");
        model.addAttribute("productos", listadoProducto);
        return "/vistas/productos/productos";
//        Esta es la ruta:    /vistas/productos/productos
    }

    //Crear
    @GetMapping("/crear")
    public String crear(Model model) {
        Producto producto = new Producto();
        
        List<Categoria> listadoCategoria = categoriaService.listarTodos();
        List<Unidad> listadoUnidad = unidadService.listarTodos();
        
        model.addAttribute("titulo", "Registrar producto");
        model.addAttribute("producto", producto);
//        model.addAttribute("unidades", listadoUnidad);
//        model.addAttribute("categorias", listadoCategoria);

        return "/vistas/productos/productoForm";
//        Esta es la ruta:    /vistas/productos/crear
    }
    

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        System.out.println("Se guardo correctamente");
        return "redirect:/vistas/productos/productos ";
    }

 //Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id,  Model model) {
        Producto producto = productoService.buscarPorId(id);
        model.addAttribute("titulo", "Editar producto");
        model.addAttribute("producto", producto);
        return "/vistas/productos/productoForm";
//        Esta es la ruta:   /vistas/productos/editar/{id}
    }
    
    //ELiminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
       productoService.eliminar(id);
        System.out.println("Registro eliminado");
        return "redirect:/vistas/productos/productos";
//        Esta es la ruta:   /vistas/productos/eliminar/{id}
    }
    
}
