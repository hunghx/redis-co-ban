package ra.demoredis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class DemoRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoRedisApplication.class, args);
    }
//    @Bean
    public CommandLineRunner runner(RedisTemplate<String, Object> redisTemplate){
        return args -> {
//          Lấy dữ liệu từ redis theo key
            Object value = redisTemplate.opsForValue().get("name");
            if (value !=null){
                System.out.println(value.toString());
            }
            // đẩy dữ liệu vào redis theo key
//            redisTemplate.opsForValue().set("name","nguyen Van A");
            // lưu 1 cặp key- value vào redis

            // kiểm tra tồn tại theo key
            redisTemplate.hasKey("name"); // trả vể boolean
            redisTemplate.delete("name"); // xóa
            redisTemplate.opsForValue().increment("count", 1);
            redisTemplate.opsForValue().decrement("count", 1);

            // cho phép set thời gian hết hạn của key

            redisTemplate.opsForValue().set("OTP", "123456", Duration.of(15, ChronoUnit.MINUTES)); // key này sẽ sống trong 15p


            // hashkey
            redisTemplate.opsForHash().put("product1","id","P001");
            redisTemplate.opsForHash().put("product1","name","Iphoen 17 pro");
            redisTemplate.opsForHash().put("product1","price","1000");


            redisTemplate.opsForHash().get("product1", "id"); // product1/id = P001

            Set<Object> keys =redisTemplate.opsForHash().keys("product1");
            List<Object> entry = redisTemplate.opsForHash().values("products");

            /*
                    product1 : map
                        id = P001
                        name = Iphone 17 pro
                        price = 1000
             */
        };

    }
}
