package sb.s3.springboot_di.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import sb.s3.springboot_di.models.Product;
import sb.s3.springboot_di.repositories.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;

// Esta clase tiene la tarea de manejar la lógica de negocio de los productos.
// El service accede a los datos mediante el repositorio y los procesa para devolverlos al controlador.
@Service // Esta anotación indica que la clase es un componente de Spring y que se encarga de la lógica de negocio.
public class ProductServiceImpl implements ProductService{


    @Autowired // Indica que se inyectará una instancia de la clase ProductRepositoryImpl.
    // Utiliza el principio de Hollywood: "No nos llames, nosotros te llamamos".
    @Qualifier("productJson") // Estamos inyectando el Json
    //private ProductRepository repository; // Inyeccion por nombre
    private  ProductRepository repository; // Inyeccion normal

    @Value("${config.impuesto}")
    private Double impuesto;
    @Primary
    @Override
    public List<Product> findAll() {
        // A continuacion vamos a agregar el IVA a los productos
        // Accedemos con el método findAll() al listado de productos.
        // Luego, con el método stream() iniciamos un flujo de datos.
        // Con el método map(p -> { ... }) recorremos cada producto y le agregamos el IVA.
        // Finalmente, con el método collect(Collectors.toList()) convertimos el flujo de datos en una lista.
        return repository.findAll().stream().map(p -> {
            Double priceImp = p.getPrice() * impuesto;
            //Product newProd = new Product(p.getId(),p.getName(),priceImp.longValue());
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceImp.longValue());
            return newProd; // De esta manera no se modifica el precio cada que se ejecuta.
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }

}
