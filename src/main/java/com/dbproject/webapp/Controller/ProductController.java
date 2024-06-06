package com.dbproject.webapp.Controller;

import com.dbproject.webapp.Model.Product;
import com.dbproject.webapp.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.dbproject.webapp.Repository.CategoryRepository;
import com.dbproject.webapp.Model.Category;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository ;

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryRepository.findAll()) ;
        return "product"; // Return the name of the Thymeleaf template (product.html)
    }

    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product-detail"; // Return the name of the Thymeleaf template for product detail
    }
}
