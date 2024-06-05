package sb.s3.springboot_di.repositories;
import java.util.List;
import sb.s3.springboot_di.models.Product;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(Long id);
}
