package ca.tmas.product.logic;

import ca.tmas.product.dto.ProductDto;
import ca.tmas.product.utility.ProductStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    Optional<ProductDto> getProductByRefProduct(String ref);

    Set<String> getAllRefProduct();

    List<ProductDto> getAllProduct();

    List<ProductDto> getAllProductByNameProduct(String nameProduct);

    List<ProductDto> getProductByPriceProduct(double priceProduct);

    List<ProductDto> getAllProductBetweenPriceProduct(double price1, double price2);

    Optional<ProductDto> getProductByNameProductAndProductStatus(String nameProduct, ProductStatus productStatus);

    List<ProductDto> getAllProductByProductStatus(ProductStatus productStatus);

    Optional<ProductDto> getProductBySaleDate(LocalDateTime localDateTime);

    List<ProductDto> getAllProductBySaleDateAndProductStatus(LocalDateTime localDateTime, ProductStatus productStatus);

    Optional<ProductDto> updatePatiallyProductByRef(String ref, ProductDto productDto);

    Optional<ProductDto> updateTotallyProductByRef(String ref, ProductDto productDto);

    Optional<ProductDto> deleteProduct(String ref);

}
