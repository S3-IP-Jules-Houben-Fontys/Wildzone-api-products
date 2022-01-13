package nl.wildzone.product;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Product {
    @Id
    private String id;
    private Integer productID;
    private Integer supplierID;
    private String name;
    private String description;
    @JsonProperty("productSpecs")
    private List<ProductSpec> productSpecs;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    
    public Product(
                Integer productID,
                Integer supplierID,
                String name,
                String description,
                List<ProductSpec> productSpecs
            ) {
        this.productID = productID;
        this.supplierID = supplierID;
        this.name = name;
        this.description = description;
        this.productSpecs = productSpecs;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

}
