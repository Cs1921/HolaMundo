
package com.co.Controller;

import com.co.POJO.Producto;
import com.co.Service.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorInicio {
    
    @Autowired
    private IProductoService productoService;
      
      
      //    El page es la pagina web
//    @GetMapping("/")
//    public ModelAndView page(){
//         List<Producto> listadoProducto = productoService.listarTodos();
//        
//       return new ModelAndView("index")  
//               .addObject("listadoProducto", listadoProducto);
//       
//
//    } 
            
    
//    El page es la pagina web
   @GetMapping("/")
    public String page(Model model){
         List<Producto> listadoProducto = productoService.listarTodos();
        model.addAttribute("titulo", "Lista de productos");
        model.addAttribute("productos", listadoProducto);
         
      return  ("page");
    }   
    
    //    Login
    @GetMapping("/login")
    public String login(){
        return("login");
    }
    
     //Portal admin
    @GetMapping("/index")
    public String index(){
        return("index");
    }
}
