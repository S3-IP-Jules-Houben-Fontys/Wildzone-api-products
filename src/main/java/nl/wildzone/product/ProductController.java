package nl.wildzone.product;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @GetMapping("")
    public List<Product> fetchAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/test")
    public String test(){
        return "Hello world!";
    }
}
