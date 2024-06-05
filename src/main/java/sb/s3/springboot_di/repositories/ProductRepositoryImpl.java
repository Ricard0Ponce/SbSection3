package sb.s3.springboot_di.repositories;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import sb.s3.springboot_di.models.Product;
import java.util.Arrays;
import java.util.List;
// @Primary
@Repository("productList") // Usamos esta anotación para indicar que la clase es un componente de Spring y que se encarga de acceder a los datos.
// A Repository se le puede asignar un nombre, el cual luego es usado en @Qualifier para indicar cuál implementación se debe inyectar.
@RequestScope // Con esta anotación indicamos que el bean se debe crear una vez por cada solicitud HTTP.
@SessionScope // Mantiene el bean en la sesión HTTP hasta que esta termine. (Que se cierre laventana)
// No se usa mucho en API REST, pero es útil en aplicaciones web.
public class ProductRepositoryImpl implements ProductRepository{

    private List<Product> data;

    // Ejemplo Pre Inyeccion de dependencias.
    public ProductRepositoryImpl() {
        this.data = Arrays.asList(
          new Product(1L,"Memoria Corsair 32", 300L), // Se agregan las L para indicar que son Long
                new Product(2L,"CPU Intel Core I9", 850L),
                new Product(3L,"Teclado Razer Mini 60%", 180L),
                new Product(4L,"Motherboard Gigabyte", 490L)
        );
    }
    @Override // Indica que se esta sobreescribiendo un metodo de la interfaz
    // Override permite que el compilador verifique que el método realmente está sobreescribiendo un método de una superclase.
    public List<Product> findAll() {
        return data;
    }

    @Override
    public Product findById(Long id) {
        // EXplicacion de data.stream:
        // Iniciamos un flujo de datos con el método stream() de la lista data.
        // Luego, con el método filter(p -> p.getId().equals(id)) filtramos los elementos de la lista data que cumplan con la condición p.getId().equals(id).
        // Finalmente, con el método findFirst() obtenemos el primer elemento que cumpla con la condición, o null si no hay ninguno.
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
