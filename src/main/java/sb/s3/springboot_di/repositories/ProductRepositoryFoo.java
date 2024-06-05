package sb.s3.springboot_di.repositories;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import sb.s3.springboot_di.models.Product;

import java.util.Collections;
import java.util.List;
//@Repository
@Primary // Con esta anotacion cuando hay 2 implementaciones de la misma interfaz, Spring selecciona la que tiene la anotacion @Primary
public class ProductRepositoryFoo implements ProductRepository {

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Product Foo", 100L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(1L, "Product Foo", 100L);
    }
}
