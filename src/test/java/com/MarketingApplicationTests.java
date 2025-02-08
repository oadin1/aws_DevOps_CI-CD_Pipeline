package com.webapp;

import com.webapp.Model.Product;
import com.webapp.service.ProductService;
import com.webapp.controller.ProductController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class MarketingApplicationTests {

    @Autowired
    private ProductService productService;

    @Mock
    private ProductService mockProductService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        // Initialize the mock MVC environment
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    // Test if the Spring Boot application context loads correctly
    @Test
    void contextLoads() {
        // This test will automatically pass if the application context loads successfully.
    }

    // Test if the ProductService works correctly
    @Test
    void testGetAllProducts() {
        // Prepare mock data
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description for Product 1");
        product1.setPrice(100.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setDescription("Description for Product 2");
        product2.setPrice(200.0);

        List<Product> products = Arrays.asList(product1, product2);

        // Mock the behavior of the ProductService
        when(mockProductService.getAllProducts()).thenReturn(products);

        // Test that the service returns the correct list of products
        List<Product> returnedProducts = productService.getAllProducts();

        assert(returnedProducts.size() == 2);
        assert(returnedProducts.get(0).getName().equals("Product 1"));
        assert(returnedProducts.get(1).getPrice() == 200.0);
    }

    // Test if the ProductController works correctly
    @Test
    void testGetAllProductsFromController() throws Exception {
        // Prepare mock data for the controller
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description for Product 1");
        product1.setPrice(100.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setDescription("Description for Product 2");
        product2.setPrice(200.0);

        List<Product> products = Arrays.asList(product1, product2);

        // Mock the behavior of the ProductService in the controller
        when(mockProductService.getAllProducts()).thenReturn(products);

        // Test the controller's response for the GET request
        mockMvc.perform(get("/products/"))
                .andExpect(status().isOk()) // Ensure the response status is OK
                .andExpect(view().name("home")) // Ensure the correct view is returned
                .andExpect(model().attribute("products", products)); // Ensure products are passed to the model
    }
}
