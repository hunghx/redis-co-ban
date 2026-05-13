package ra.demoredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ra.demoredis.entity.Product;

@Configuration
public class RedisConfig {
    // KEY _VALUE
    /*
    name = nguyen Van A
    age = 18

    product : (group) Map(key, value)
        name = iphone
        price = 1000

     */
    @Bean
    public RedisTemplate<String, Product> productRedisTemplate(
            RedisConnectionFactory connectionFactory
    ) {
        RedisTemplate<String, Product> template = new RedisTemplate<>();

        template.setConnectionFactory(connectionFactory);

        JacksonJsonRedisSerializer<Product> serializer =
                new JacksonJsonRedisSerializer<>(Product.class);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();

        return template;
    }
}
