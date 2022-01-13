package nl.wildzone.product;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @GetMapping("/{id}")
    public Optional<Product> fetchProduct(@PathVariable("id") Integer id) {
        return productService.getProduct(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer productID, @RequestBody Product updateProduct){
        if(updateProduct != null) {
            Product product = productService.updateProduct(productID, updateProduct);
            if(product == null){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                return new ResponseEntity<>(product, HttpStatus.CREATED);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public List<Product> fetchAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        if(newProduct != null) {
            Product product = productService.createProduct(newProduct);
            if(product == null){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                return new ResponseEntity<>(product, HttpStatus.CREATED);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/test")
    public String test(){
        return "Hello world!";
    }
}
