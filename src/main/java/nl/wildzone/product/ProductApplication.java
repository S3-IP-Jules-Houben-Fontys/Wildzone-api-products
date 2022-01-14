package nl.wildzone.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
/*
	@Bean
	CommandLineRunner runner(ProductRepository repository) {
		return args -> {
			ProductSpec productSpec = new ProductSpec("L", 500, "blue", 20.00);
			List<ProductSpec> productSpecs = new ArrayList<ProductSpec>();
			productSpecs.add(productSpec);
			Product product = new Product(1, 1, "shirt", "nice shirt", productSpecs);

			repository.insert(product);
		};
	};
*/
}
