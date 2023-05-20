package com.example.ecommerce.Controller;

import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Service.MerchantStockService;
import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/buy")
@RequiredArgsConstructor
public class BuyControl {
    private final ProductService productService;
    private final MerchantStockService merchantStockService;
    private final UserService userService;

    // http://localhost/api/v1/buy/user/55/product/15/merchant/5
    @PostMapping("/user/{userId}/product/{productId}/merchant/{merchantId}")
    public ResponseEntity buy(
            @PathVariable("userId") int userId,
            @PathVariable("productId") int productId,
            @PathVariable("merchantId") int merchantId){

        if(!merchantStockService.haveProduct(merchantId,productId)){
            return ResponseEntity.status(400).body("The merchant don't have Product");
        }

        Product product = productService.getProductById(productId);
        if(!userService.haveBalance(userId,product.getPrice())){
            return ResponseEntity.status(400).body("The user don't have balance");
        }

        merchantStockService.reduceProduct(merchantId,productId);
        userService.reduceBalance(userId,product.getPrice());

        return ResponseEntity.status(200).build();
    }
}
