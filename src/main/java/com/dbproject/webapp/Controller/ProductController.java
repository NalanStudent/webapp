package com.dbproject.webapp.Controller;

import com.dbproject.webapp.Model.Cart;
import com.dbproject.webapp.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dbproject.webapp.Repository.CartRepository;
import com.dbproject.webapp.Repository.CategoryRepository;
import com.dbproject.webapp.Repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository ;

    @Autowired
    private CartRepository cartRepository ;

    @GetMapping("/")
    public String homeredirect(){
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryRepository.findAll()) ;
        return "product"; // Return the name of the Thymeleaf template (product.html)
    }

    @GetMapping("/order/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "order";
    }


    @PostMapping("/addtocart")
    public String addToCart(@RequestParam Long productId, @RequestParam Integer quantity, @RequestParam BigDecimal price ) {
        Cart cart = new Cart();


        BigDecimal total = new BigDecimal(quantity) ;
        
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setTotal(price.multiply(total));
        cartRepository.save(cart);

        return "redirect:/order/" + productId;
    }
}
