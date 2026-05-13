package ra.demoredis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.demoredis.entity.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Double price;

    public static ProductDto mapToDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getPrice());
    }
}
