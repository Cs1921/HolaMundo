
package com.co.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorInicio {
            
    
//    El page es la pagina web
    @GetMapping("/")
    public String page(){
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
