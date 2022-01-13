package nl.wildzone.product;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private MongoTemplate mongoTemplate;

    public Product getProduct(Integer productID) {       

        Query query = new Query();
        query.addCriteria(Criteria.where("productID").is(productID).and("archivedAt").is(null).not());

        Product product = mongoTemplate.findOne(query, Product.class);

        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product newProduct) {
        Integer count = 0;
        for (ProductSpec product  : newProduct.getProductSpecs()) {
            product.setId(count);
            count++;
        }

        List<Product> latestProduct = productRepository.findAll(Sort.by(Sort.Direction.DESC, "productID")); 
        if(latestProduct.get(0).getProductID() != null) {
            newProduct.setProductID(latestProduct.get(0).getProductID()+1); 
        }else{
            newProduct.setProductID(0);
        }
        
        System.out.println(newProduct);

        return productRepository.save(newProduct);
    }

    public Product updateProduct(Integer productID, Product updateProduct) {

        if(productID == updateProduct.getProductID()) {
            return productRepository.save(updateProduct);
        }

        return null;

        
    }

    public Product archiveProduct(Integer productID) {

        Query query = new Query();
        query.addCriteria(Criteria.where("productID").is(productID).and("archivedAt").is(null));

        Product patch = mongoTemplate.findOne(query, Product.class);

        patch.setArchivedAt(LocalDateTime.now());

        return productRepository.save(patch);
    }
}
