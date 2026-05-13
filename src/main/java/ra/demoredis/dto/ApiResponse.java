package ra.demoredis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.demoredis.entity.Product;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private long duration;
    private ProductDto data;
}
