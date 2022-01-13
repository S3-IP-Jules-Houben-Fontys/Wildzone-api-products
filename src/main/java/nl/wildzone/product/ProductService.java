package nl.wildzone.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<Product> getProduct(Integer productID) {
        //return productRepository.findByProductId(productID);
        return null;
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
        //System.out.println(latestProduct);
        //System.out.println(latestProduct.get(0));
        //System.out.println("ProductID: "+ latestProduct.get(0).getProductID());
        //latestProduct.get(0).setProductID(1);
        //System.out.println("ProductID: "+ latestProduct.get(0).getProductID());
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

}
