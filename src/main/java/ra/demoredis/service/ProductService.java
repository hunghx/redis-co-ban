package ra.demoredis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ra.demoredis.dto.ApiResponse;
import ra.demoredis.dto.ProductDto;
import ra.demoredis.entity.Product;
import ra.demoredis.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final RedisTemplate<String , Product> redisTemplate;
    private final ProductRepository productRepository;

    public ApiResponse getProductById(Long id) {
        long start = System.nanoTime();
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductDto dto =  ProductDto.mapToDto(p);
        long end = System.nanoTime();
        return new ApiResponse((end-start)/1_000_000,dto);

    }


    public ApiResponse getProductByIdWithRedis(Long id) {
        long start = System.nanoTime();
        String key = "product:"+id;
        // kiểm tra trong redis có lưu sản phẩm với id truyền vào hay chưa
        Object value = redisTemplate.opsForValue().get(key);
        if(value !=null){
            ProductDto dto =  ProductDto.mapToDto((Product)value);
            long end = System.nanoTime();
            return new ApiResponse((end-start)/1_000_000, dto);
        }
        // nếu chưa có : lấy từ db v lưu vào redis -> trả cho client
        // nếu  có rồi thì trả trực tiếp từ redis cho cleint
        Product entity = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        redisTemplate.opsForValue().set(key,entity);
        ProductDto dto =  ProductDto.mapToDto(entity);
        long end = System.nanoTime();
        return new ApiResponse((end-start)/1_000_000, dto);
    }
}
