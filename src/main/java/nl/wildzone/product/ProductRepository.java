package nl.wildzone.product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository 
        extends MongoRepository<Product, String>{
    
        //public Product findByProductId(Integer productId);

}
