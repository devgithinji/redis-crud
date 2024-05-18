package org.densoft.rediscrud;

import lombok.RequiredArgsConstructor;
import org.densoft.rediscrud.entity.Product;
import org.densoft.rediscrud.repository.ProductDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class RedisCrudApplication {

    private final ProductDao productDao;

    public static void main(String[] args) {
        SpringApplication.run(RedisCrudApplication.class, args);
    }


    @PostMapping
    public Product save(@RequestBody Product product) {
        return productDao.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @GetMapping("/{id}")
    public Product findProduct(@PathVariable("id") int id) {
        return productDao.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        return productDao.deleteProduct(id);
    }

}
