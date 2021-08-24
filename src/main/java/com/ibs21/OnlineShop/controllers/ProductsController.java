package com.ibs21.OnlineShop.controllers;

import com.ibs21.OnlineShop.domain.Product;
import com.ibs21.OnlineShop.domain.User;
import com.ibs21.OnlineShop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{id}")
    public String product(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        //model.addAttribute("likely"); - похожие
        return "products/product";
    }

    @GetMapping("/add")
    public String addproduct() {
        return "products/add";
    }

    @PostMapping("/add")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam("productname") String productname,
                      @RequestParam("description") String description,
                      @RequestParam("count") int count,
                      @RequestParam("price") int price) {
        if (productname != null && description != null && count > 0 && price > 0) {
            Product product = new Product();
            product.setProducttitle(productname);
            product.setDescription(description);
            product.setCount(count);
            product.setPrice(price);
            product.setRaiting(0);
            product.setSeller(user);
            productRepository.save(product);
        }
        return "redirect:/home";
    }

}
