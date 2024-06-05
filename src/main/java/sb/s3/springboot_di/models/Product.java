package sb.s3.springboot_di.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Anotación de Loombok para generar un constructor vacío
@AllArgsConstructor // Anotación de Loombok para generar un constructor con todos los argumentos
@Data // Anotación de Loombok para generar los métodos getter, setter, equals, hashCode y toString
public class Product implements Cloneable{
    private Long id;
    private String name;
    private Long price;

    @Override
    public Object clone(){
        try{
            return (Product) super.clone();
        } catch (CloneNotSupportedException e){
            return new Product(this.getId(), this.getName(), this.getPrice());
        }
    }
}
