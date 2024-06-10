package com.dbproject.webapp.Controller;

import com.dbproject.webapp.Model.Cart;
import com.dbproject.webapp.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    long count ;
    //Long count = 10L;

    @GetMapping("/")
    public String homeredirect(){
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryRepository.findAll()) ;
        count =  cartRepository.count() ;
        model.addAttribute("counter", count);

        return "product"; // Return the name of the Thymeleaf template (product.html)
    }

    @GetMapping("/order/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        count =  cartRepository.count() ;
        model.addAttribute("counter", count);
        return "order";
    }


    @PostMapping("/addtocart")
    public String addToCart(@RequestParam Long productId, @RequestParam Integer quantity, @RequestParam BigDecimal price ) {
        Cart cart = new Cart();

        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + productId));


        BigDecimal qty = new BigDecimal(quantity) ;
        
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cart.setTotal(qty.multiply(product.getPrice()));
        cartRepository.save(cart);

        return "redirect:/order/" + productId;
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        List<Cart> cartProducts = cartRepository.findAll();
        model.addAttribute("cartproducts", cartProducts);
        count =  cartRepository.count() ;
        model.addAttribute("counter", count);
        return "cart"; // Return the name of the Thymeleaf template (product.html)
    }



    @PostMapping("/update-quantity")
    public String updateQuantity(@RequestParam Long itemId, @RequestParam Integer quantity) {

        BigDecimal qty = new BigDecimal(quantity) ;

        Cart itemToUpdate = cartRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + itemId));;
        //Product product = productRepository.findById(itemToUpdate.getProduct()).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + itemId));

        itemToUpdate.setQuantity(quantity);
        itemToUpdate.setTotal(qty.multiply(itemToUpdate.getProduct().getPrice()));
        cartRepository.save(itemToUpdate);

        return "redirect:/cart"; // Redirect back to the cart page
    }


    @PostMapping("/removeItem")
    public String removeItem(@RequestParam Long itemId) {
        Cart itemToUpdate = cartRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + itemId));;
        cartRepository.delete(itemToUpdate);
        return "redirect:/cart"; // Redirect back to the cart page
    }
}
