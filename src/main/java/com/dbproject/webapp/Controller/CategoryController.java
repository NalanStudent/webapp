package com.dbproject.webapp.Controller;

import com.dbproject.webapp.Model.Category;
import com.dbproject.webapp.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dbproject.webapp.Repository.CategoryRepository;
import com.dbproject.webapp.Repository.ProductRepository;

import java.util.List;

@Controller
public class CategoryController {
    
    @Autowired
    private ProductRepository productRepository ;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/category/{id}")
    public String getAllProducts(@PathVariable("id") Long id ,Model model) {
        List<Product> cateproducts = productRepository.findByCategoryId(id) ;
        Category current_Category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("cateproducts", cateproducts);
        model.addAttribute("current_category", current_Category);
        //model.addAttribute("cate_id", id);
        return "categoryProducts"; 
    }

}

