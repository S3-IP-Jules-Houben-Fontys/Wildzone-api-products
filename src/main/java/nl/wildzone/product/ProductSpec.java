package nl.wildzone.product;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductSpec implements Serializable {
    private Integer id;
    private String size;
    private Integer weight;
    private String color;
    private Double MSRP;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductSpec(
        String size,
        Integer weight,
        String color,
        Double MSRP
    ) {
        this.size = size;
        this.weight = weight;
        this.color = color;
        this.MSRP = MSRP;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}
