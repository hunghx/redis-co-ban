package ra.demoredis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.demoredis.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
