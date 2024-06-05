package sb.s3.springboot_di.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sb.s3.springboot_di.models.Product;
import sb.s3.springboot_di.services.ProductService;
import java.util.List;

@RestController// RestController es la fucion de Controller y ResponseBody
// Recordar que Controller es una anotación que indica que la clase es un controlador de Spring MVC y que maneja las solicitudes HTTP.
// Mientras que ResponseBody es una anotación que indica que el valor de retorno de un método debe ser enlazado al cuerpo de la respuesta HTTP.
@RequestMapping("/api") // Mapeamos una URL base para todos los métodos de la clase.
public class ProductController {

    @Autowired // Realizamos la inyeccion de dependencias, pero esta vez es mediante la interfaz ProductService.
    private ProductService service;

    @GetMapping("/products") // Mapeamos la URL /api/products para el método list.
    public List<Product> list(){
        return service.findAll();
    }

    @GetMapping("/products/{id}") // Mapeamos la URL /api/products/{id} para el método detail.
    public Product showProduct(@PathVariable Long id){
        return service.findById(id);
    }


}
