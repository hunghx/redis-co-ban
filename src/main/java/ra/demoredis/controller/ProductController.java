package ra.demoredis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.demoredis.dto.ApiResponse;
import ra.demoredis.entity.Product;
import ra.demoredis.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ApiResponse getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    @GetMapping("/redis/{id}")
    public ApiResponse getProductByRedis(@PathVariable Long id) {
        return productService.getProductByIdWithRedis(id);
    }


}
