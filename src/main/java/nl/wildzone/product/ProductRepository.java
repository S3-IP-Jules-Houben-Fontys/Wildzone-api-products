package nl.wildzone.product;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository 
        extends MongoRepository<Product, String>{
    
        //Product findTopByOrderByProductIdAsc();


}
