package ca.tmas.product.controller;

import ca.tmas.product.dto.ProductDto;
import ca.tmas.product.logic.ProductService;
import ca.tmas.product.utility.ProductStatus;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@RefreshScope
@EnableFeignClients(basePackages = { "ca.tmas.product" })
public class ProductController {

    private final ProductService productService;

    @PostMapping("/createProduct")
    synchronized public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        try {
            return new ResponseEntity<ProductDto>(productService.createProduct(productDto), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            return new ResponseEntity<ProductDto>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getProductByRefProduct/{ref}")
    synchronized public ResponseEntity<Optional<ProductDto>> getProductByRefProduct(@PathVariable(value = "ref") String ref) {
        if(productService.getProductByRefProduct(ref).isPresent()) {
            return new ResponseEntity<Optional<ProductDto>>(productService.getProductByRefProduct(ref), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<ProductDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllRefProduct")
    synchronized public ResponseEntity<Set<String>> getAllRefProduct() {
        if(productService.getAllRefProduct().isEmpty()) {
            return new ResponseEntity<Set<String>>(Collections.emptySet(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Set<String>>(productService.getAllRefProduct(), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllProduct")
    synchronized public ResponseEntity<List<ProductDto>> getAllProduct() {
        if(productService.getAllRefProduct().isEmpty()) {
            return new ResponseEntity<List<ProductDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<ProductDto>>(productService.getAllProduct(), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllProductByNameProduct/{nameProduct}")
    synchronized public ResponseEntity<List<ProductDto>> getAllProductByNameProduct(@PathVariable(value = "nameProduct") String nameProduct) {
        if(productService.getAllProductByNameProduct(nameProduct).isEmpty()) {
            return new ResponseEntity<List<ProductDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<ProductDto>>(productService.getAllProductByNameProduct(nameProduct), HttpStatus.OK);
        }
    }

    @GetMapping("/getProductByPriceProduct/{priceProduct}")
    synchronized public ResponseEntity<List<ProductDto>> getProductByPriceProduct(@PathVariable(value = "priceProduct") double priceProduct) {
        if(productService.getProductByPriceProduct(priceProduct).isEmpty()) {
            return new ResponseEntity<List<ProductDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<ProductDto>>(productService.getProductByPriceProduct(priceProduct), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllProductBetweenPriceProduct/{price1}/{price2}")
    synchronized public ResponseEntity<List<ProductDto>> getAllProductBetweenPriceProduct(@PathVariable(value = "price1") double price1, @PathVariable(value = "price2") double price2) {
        if(productService.getAllProductBetweenPriceProduct(price1, price2).isEmpty()) {
            return new ResponseEntity<List<ProductDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<ProductDto>>(productService.getAllProductBetweenPriceProduct(price1, price2), HttpStatus.OK);
        }
    }

    @GetMapping("/getProductByNameProductAndProductStatus/{nameProduct}/{productStatus}")
    synchronized public ResponseEntity<Optional<ProductDto>> getProductByNameProductAndProductStatus(@PathVariable(value = "nameProduct") String nameProduct, @PathVariable(value = "productStatus") ProductStatus productStatus) {
        if(productService.getProductByNameProductAndProductStatus(nameProduct, productStatus).isPresent()) {
            return new ResponseEntity<Optional<ProductDto>>(productService.getProductByNameProductAndProductStatus(nameProduct, productStatus), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<ProductDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllProductByProductStatus/{productStatus}")
    synchronized public ResponseEntity<List<ProductDto>> getAllProductByProductStatus(@PathVariable(value = "productStatus") ProductStatus productStatus) {
        if(productService.getAllProductByProductStatus(productStatus).isEmpty()) {
            return new ResponseEntity<List<ProductDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<ProductDto>>(productService.getAllProductByProductStatus(productStatus), HttpStatus.OK);
        }
    }

    @GetMapping("/getProductBySaleDate/{localDateTime}")
    synchronized public ResponseEntity<Optional<ProductDto>> getProductBySaleDate(@PathVariable(value = "localDateTime") LocalDateTime localDateTime) {
        if(productService.getProductBySaleDate(localDateTime).isPresent()) {
            return new ResponseEntity<Optional<ProductDto>>(productService.getProductBySaleDate(localDateTime), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<ProductDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllProductBySaleDateAndProductStatus/{localDateTime}/{productStatus}")
    synchronized public ResponseEntity<List<ProductDto>> getAllProductBySaleDateAndProductStatus(@PathVariable(value = "localDateTime") LocalDateTime localDateTime, @PathVariable(value = "productStatus") ProductStatus productStatus) {
        if(productService.getAllProductBySaleDateAndProductStatus(localDateTime, productStatus).isEmpty()) {
            return new ResponseEntity<List<ProductDto>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<ProductDto>>(productService.getAllProductBySaleDateAndProductStatus(localDateTime, productStatus), HttpStatus.OK);
        }
    }

    @PatchMapping("/updatePatiallyProductByRef/{ref}")
    synchronized public ResponseEntity<Optional<ProductDto>> updatePatiallyProductByRef(@PathVariable(value = "ref") String ref, @RequestBody ProductDto productDto) {
        if(productService.getProductByRefProduct(ref).isPresent()) {
            return new ResponseEntity<Optional<ProductDto>>(productService.updatePatiallyProductByRef(ref, productDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<ProductDto>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateTotallyProductByRef/{ref}")
    synchronized public ResponseEntity<Optional<ProductDto>> updateTotallyProductByRef(@PathVariable(value = "ref") String ref, @RequestBody ProductDto productDto) {
        if(productService.updateTotallyProductByRef(ref, productDto).isPresent()) {
            return new ResponseEntity<Optional<ProductDto>>(productService.updateTotallyProductByRef(ref, productDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<ProductDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteProduct/{ref}")
    synchronized public ResponseEntity<Optional<ProductDto>> deleteProduct(@PathVariable(value = "ref") String ref) {
        if(productService.deleteProduct(ref).isPresent()) {
            return new ResponseEntity<Optional<ProductDto>>(productService.deleteProduct(ref), HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<ProductDto>>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

}
