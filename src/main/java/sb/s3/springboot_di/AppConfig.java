package sb.s3.springboot_di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import sb.s3.springboot_di.repositories.ProductRepository;
import sb.s3.springboot_di.repositories.ProductRepositoryJson;

import java.io.IOException;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("classpath:json/product.json")
    private Resource productJson;
    @Bean("productJson") // Indica que el método es un bean de Spring.
    //Explicación del metodo:
    //El metodo productRepositoryJson() retorna un objeto de tipo ProductRepositoryJson.
    //El metodo productRepositoryJson() lanza una excepcion de tipo IOException.
    //El metodo productRepositoryJson() carga el archivo product.json.
    //El metodo productRepositoryJson() convierte el archivo JSON a una lista de objetos Product.
    //El metodo productRepositoryJson() retorna un objeto de tipo ProductRepositoryJson.
    // @Primary
    public ProductRepository productRepositoryJson() throws IOException {
        return new ProductRepositoryJson(productJson);
    }
}
