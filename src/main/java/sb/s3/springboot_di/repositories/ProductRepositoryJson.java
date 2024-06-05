package sb.s3.springboot_di.repositories;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import sb.s3.springboot_di.models.Product;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@AllArgsConstructor
public class ProductRepositoryJson implements ProductRepository {

    private List<Product> list;

    public ProductRepositoryJson(Resource resource) throws IOException {
        //ClassPathResource resource = new ClassPathResource("json/product.json"); // Cargamos el archivo products.json
        ObjectMapper mapper = new ObjectMapper(); // Permite convertir un archivo JSON a una lista de objetos Product.
        list = Arrays.asList(mapper.readValue(resource.getInputStream(), Product[].class)); // Convertimos el archivo JSON a una lista de objetos Product.
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    //Explicacion detallada del metodo findById
    //El metodo findById recibe un id de tipo Long y retorna un objeto de tipo Product.
    //Se utiliza el metodo stream() para convertir la lista de productos en un flujo de datos.
    //Se utiliza el metodo filter(p -> { return p.getId().equals(id); }) para filtrar el flujo de datos y obtener el producto con el id que se busca.
    //Se utiliza el metodo findFirst() para obtener el primer producto que cumpla con la condicion del filtro.
    //Se utiliza el metodo orElseThrow() para lanzar una excepcion en caso de que no se encuentre el producto con el id buscado.
    //El metodo findById retorna el producto encontrado con el id buscado.
    @Override
    public Product findById(Long id) {
        return list
                .stream()
                .filter(p -> {
                    return p.getId().equals(id);
                }).findFirst().orElseThrow();
    }
}
