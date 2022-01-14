package nl.wildzone.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ProductController.class)
public class ProductTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
	public void shouldGetSeveralPRoducts() throws Exception {
        ProductSpec productSpec = new ProductSpec("L", 500, "blue", 20.00);
        List<ProductSpec> productSpecs = new ArrayList<ProductSpec>();
        productSpecs.add(productSpec);
        Product product = new Product(1, 1, "shirt", "nice shirt", productSpecs);
        Product product2 = new Product(2, 2, "pants", "cool pants", productSpecs);

        List<Product> productList = new ArrayList<Product>();
        productList.add(product);
        productList.add(product2);

        when(productService.getAllProducts()).thenReturn(productList);

		mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[:1].id").isNotEmpty())
                .andExpect(jsonPath("$[:1].productID").value(1))
                .andExpect(jsonPath("$[:1].supplierID").value(1))
                .andExpect(jsonPath("$[:1].name").value("shirt"))
                .andExpect(jsonPath("$[:1].description").value("nice shirt"))
                .andExpect(jsonPath("$[:1].createdAt").isNotEmpty())
                .andExpect(jsonPath("$[:1].updatedAt").isNotEmpty())
                .andExpect(jsonPath("$[:1].productSpecs").isNotEmpty())
                .andExpect(jsonPath("$[:2].id").isNotEmpty())
                .andExpect(jsonPath("$[:2].productID").value(2))
                .andExpect(jsonPath("$[:2].supplierID").value(2))
                .andExpect(jsonPath("$[:2].name").value("pants"))
                .andExpect(jsonPath("$[:2].description").value("cool pants"))
                .andExpect(jsonPath("$[:2].createdAt").isNotEmpty())
                .andExpect(jsonPath("$[:2].updatedAt").isNotEmpty())
                .andExpect(jsonPath("$[:2].productSpecs").isNotEmpty());
	}

    @Test
    public void getOneProduct() throws Exception {
        ProductSpec productSpec = new ProductSpec("L", 500, "blue", 20.00);
        List<ProductSpec> productSpecs = new ArrayList<ProductSpec>();
        productSpecs.add(productSpec);
        Product product = new Product(1, 1, "shirt", "nice shirt", productSpecs);

        when(productService.getProduct(1)).thenReturn(product);

        mockMvc.perform(get("/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.productID").value(1))
                .andExpect(jsonPath("$.supplierID").value(1))
                .andExpect(jsonPath("$.name").value("shirt"))
                .andExpect(jsonPath("$.description").value("nice shirt"))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.updatedAt").isNotEmpty())
                .andExpect(jsonPath("$.productSpecs").isNotEmpty());
    }

    @Test
    public void productDoesNotExist() throws Exception{

        ((ResultActions) ((MockHttpServletRequestBuilder) mockMvc.perform(get("/{id}", 23409)))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }
}