package ra.demoredis.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ra.demoredis.entity.Product;
import ra.demoredis.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

//@Configuration
public class DataInit {
//    @Bean
    public CommandLineRunner runner(ProductRepository productRepository) {
        return args -> {
            List<Product> products = new ArrayList<>();
            // 10000 dữ liệu mẫu
            for (int i=1; i<=10000; i++){
                Product product = new Product(
                        null,
                        "product name "+i,
                        "description "+i,
                        1000.0+i
                );
                products.add(product);
            }
            productRepository.saveAll(products);
            System.out.println("insert successfully");
        };
    }
}
