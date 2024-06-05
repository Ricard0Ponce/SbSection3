package sb.s3.springboot_di.services;

import sb.s3.springboot_di.models.Product;

import java.util.List;
// Note que creamos una interfaz para el servicio, esto es para que se pueda implementar en cualquier clase que necesite
// De esta manera mantenemos el principio de alta cohesión y bajo acoplamiento.
public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
}
