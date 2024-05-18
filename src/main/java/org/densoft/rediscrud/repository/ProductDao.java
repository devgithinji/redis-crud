package org.densoft.rediscrud.repository;

import lombok.RequiredArgsConstructor;
import org.densoft.rediscrud.entity.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDao {

    public static final String HASH_KEY = "Product";

    private final RedisTemplate<String, Object> redisTemplate;

    public Product save(Product product) {
        redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY).stream().map(o -> (Product) o).toList();
    }

    public Product findProductById(int id) {
        return (Product) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public Product update(Product product) {
        return save(product);
    }

    public String deleteProduct(int id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);
        return "product removed";
    }
}
