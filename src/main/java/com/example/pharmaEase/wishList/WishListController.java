package com.example.pharmaEase.wishList;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.pharmaEase.DTO.product.ProductDto;
import com.example.pharmaEase.common.ApiResponse;
import com.example.pharmaEase.product.Product;
import com.example.pharmaEase.product.ProductService;
import com.example.pharmaEase.service.AuthenticationService;
import com.example.pharmaEase.user.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class WishListController {
        private final WishListService wishListService;
        private final AuthenticationService authenticationService;

        @GetMapping("/wishlist/{token}")
        public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {
                long user_id = authenticationService.getUser(token).getId();
                List<WishList> body = wishListService.readWishList(user_id);
                List<ProductDto> products = new ArrayList<ProductDto>();
                for (WishList wishList : body) {
                        products.add(ProductService.getDtoFromProduct(wishList.getProduct()));
                }

                return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
        }

        @PostMapping("/wishlist/add")
        public ResponseEntity<ApiResponse> addWishList(@RequestBody Product product, @RequestParam("token") String token) {
                authenticationService.authenticate(token);
                User user = authenticationService.getUser(token);
                WishList wishList = new WishList(user, product);
                wishListService.createWishlist(wishList);
                return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);

        }


}
