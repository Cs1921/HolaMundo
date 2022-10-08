package com.co;

import com.co.POJO.Categoria;
import com.co.POJO.Producto;
import com.co.POJO.Unidad;
import com.co.Service.ICategoriaService;
import com.co.Service.IProductoService;
import com.co.Service.IUnidadService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        model.addAttribute("unidades", listadoUnidad);
        model.addAttribute("categorias", listadoCategoria);

        return "/vistas/productos/productoForm";
//        Esta es la ruta:    /vistas/productos/crear
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Producto producto, BindingResult result,
            Model model,@RequestParam("file") MultipartFile imagen, RedirectAttributes attribute) {

        //Validacion de errores
        if (result.hasErrors()) {

            List<Categoria> listadoCategoria = categoriaService.listarTodos();
            List<Unidad> listadoUnidad = unidadService.listarTodos();

            model.addAttribute("titulo", "Registrar producto");
            model.addAttribute("producto", producto);

            model.addAttribute("unidades", listadoUnidad);
            model.addAttribute("categorias", listadoCategoria);

            return "/vistas/productos/productoForm";
        }
        
        if(!imagen.isEmpty()){
            Path directorioImagenes = Paths.get("src//main//resources//static//images");
            String rutaAbosulta = directorioImagenes.toFile().getAbsolutePath();
            
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbosulta + "//"+imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                
                producto.setImagen(imagen.getOriginalFilename());
                
            } catch (IOException ex) {
                Logger.getLogger(ProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        

        productoService.guardar(producto);
//        System.out.println("Se guardo correctamente");
        attribute.addFlashAttribute("success", "Producto guardado con exito");
        return "redirect:/vistas/productos/productos ";
    }

    //Editar
    @GetMapping("/detalle/{id}")
    public String detalleProducto(@PathVariable("id") Integer id, Model model, RedirectAttributes attribute) {

        Producto producto = null;
        if (id > 0) {
            producto = productoService.buscarPorId(id);

            if (producto == null) {
                attribute.addFlashAttribute("error", "Atencion: El id de producto no existe");

                return "redirect:/vistas/productos/productos";
            }
        } else {
             attribute.addFlashAttribute("error", "Atencion: El id de producto no existe");
            return "redirect:/vistas/productos/productos";
        }

        List<Categoria> listadoCategoria = categoriaService.listarTodos();
        List<Unidad> listadoUnidad = unidadService.listarTodos();

        model.addAttribute("titulo", "Detalle producto: "+producto.getNombre());
        model.addAttribute("producto", producto);

        model.addAttribute("unidades", listadoUnidad);
        model.addAttribute("categorias", listadoCategoria);
        return "/vistas/productos/detalleProducto";
//        Esta es la ruta:   /vistas/productos/detalle/{id}
    }
    //Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model, RedirectAttributes attribute) {

        Producto producto = null;
        if (id > 0) {
            producto = productoService.buscarPorId(id);

            if (producto == null) {
                attribute.addFlashAttribute("error", "Atencion: El id de producto no existe");

                return "redirect:/vistas/productos/productos";
            }
        } else {
             attribute.addFlashAttribute("error", "Atencion: El id de producto no existe");
            return "redirect:/vistas/productos/productos";
        }

        List<Categoria> listadoCategoria = categoriaService.listarTodos();
        List<Unidad> listadoUnidad = unidadService.listarTodos();

        model.addAttribute("titulo", "Editar producto");
        model.addAttribute("producto", producto);

        model.addAttribute("unidades", listadoUnidad);
        model.addAttribute("categorias", listadoCategoria);
        return "/vistas/productos/productoForm";
//        Esta es la ruta:   /vistas/productos/editar/{id}
    }

    //ELiminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {

        Producto producto = null;
        if (id > 0) {
            producto = productoService.buscarPorId(id);

            if (producto == null) {

                return "redirect:/vistas/productos/productos";
            }
        } else {
            return "redirect:/vistas/productos/productos";
        }

        productoService.eliminar(id);
        System.out.println("Registro eliminado");
        return "redirect:/vistas/productos/productos";
//        Esta es la ruta:   /vistas/productos/eliminar/{id}
    }

}
