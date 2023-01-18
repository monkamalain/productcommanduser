package ca.tmas.product.logic.impl;

import ca.tmas.product.dao.ProductRepository;
import ca.tmas.product.dto.ProductDto;
import ca.tmas.product.entities.Product;
import ca.tmas.product.logic.ProductService;
import ca.tmas.product.utility.ProductStatus;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final long CSTPDT;

    private static final String REFPDT;

    private final ModelMapper mapper;

    private final ProductRepository productRepository;

    static {
        CSTPDT = 22222L;
        REFPDT = "pdt-";
    }

    @Transactional
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product pd;
        String ref;
        if(productRepository.findAllRefProduct().isEmpty()) {
            ref = REFPDT + Long.toHexString(Long.MAX_VALUE % CSTPDT);
        } else {
            String maxRef = productRepository.findAllRefProduct().stream().map(x -> x.substring(4)).collect(Collectors.toSet()).stream().max(String::compareTo).get();
            long nextValueRef = Long.valueOf(maxRef, 16) + 1;
            ref = REFPDT + Long.toHexString(nextValueRef);
        }
        try {
            pd = mapper.map(productDto, Product.class);
            pd.setRefProduct(ref.toUpperCase());
            pd.setProductStatus(ProductStatus.AVALAIBLE);
            pd.setSaleDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            return mapper.map(productRepository.save(pd), ProductDto.class);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Bad argument");
        }
    }

    @Override
    public Optional<ProductDto> getProductByRefProduct(String ref) {
        return Optional.ofNullable(mapper.map(productRepository.findProductByRefProduct(ref).get(), ProductDto.class));
    }

    @Override
    public Set<String> getAllRefProduct() {
        return productRepository.findAllRefProduct().isEmpty()? Collections.emptySet(): productRepository.findAllRefProduct();
    }

    @Override
    public List<ProductDto> getAllProduct() {
        if(productRepository.findAll().isEmpty()) {
            return Collections.emptyList();
        } else {
            List<ProductDto> productDtoList = new ArrayList<>();
            productRepository.findAll().forEach(pd -> productDtoList.add(mapper.map(pd, ProductDto.class)));
            return productDtoList;
        }
    }

    @Override
    public List<ProductDto> getAllProductByNameProduct(String nameProduct) {
        if(productRepository.findAllProductByNameProduct(nameProduct).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<ProductDto> productDtoList = new ArrayList<>();
            productRepository.findAllProductByNameProduct(nameProduct).forEach(pd -> productDtoList.add(mapper.map(pd, ProductDto.class)));
            return productDtoList;
        }
    }

    @Override
    public List<ProductDto> getProductByPriceProduct(double priceProduct) {
        if(productRepository.findProductByPriceProduct(priceProduct).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<ProductDto> productDtoList = new ArrayList<>();
            productRepository.findProductByPriceProduct(priceProduct).forEach(pd -> productDtoList.add(mapper.map(pd, ProductDto.class)));
            return productDtoList;
        }
    }

    @Override
    public List<ProductDto> getAllProductBetweenPriceProduct(double price1, double price2) {
        if(productRepository.findAllProductBetweenPriceProduct(price1, price2).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<ProductDto> productDtoList = new ArrayList<>();
            productRepository.findAllProductBetweenPriceProduct(price1, price2).forEach(pd -> productDtoList.add(mapper.map(pd, ProductDto.class)));
            return productDtoList;
        }
    }

    @Override
    public Optional<ProductDto> getProductByNameProductAndProductStatus(String nameProduct, ProductStatus productStatus) {
        if(productRepository.findProductByNameProductAndProductStatus(nameProduct, productStatus).isPresent()) {
            return Optional.of(mapper.map(productRepository.findProductByNameProductAndProductStatus(nameProduct, productStatus).get(), ProductDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<ProductDto> getAllProductByProductStatus(ProductStatus productStatus) {
        if(productRepository.findAllProductByProductStatus(productStatus).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<ProductDto> productDtoList = new ArrayList<>();
            productRepository.findAllProductByProductStatus(productStatus).forEach(pd -> productDtoList.add(mapper.map(pd, ProductDto.class)));
            return productDtoList;
        }
    }

    @Override
    public Optional<ProductDto> getProductBySaleDate(LocalDateTime localDateTime) {
        Optional<Product> pd = productRepository.findProductBySaleDate(localDateTime);
        return Optional.ofNullable(mapper.map(pd.get(), ProductDto.class));
    }

    @Override
    public List<ProductDto> getAllProductBySaleDateAndProductStatus(LocalDateTime localDateTime, ProductStatus productStatus) {
        if(productRepository.findAllProductBySaleDateAndProductStatus(localDateTime, productStatus).isEmpty()) {
            return Collections.emptyList();
        } else {
            List<ProductDto> productDtoList = new ArrayList<>();
            productRepository.findAllProductBySaleDateAndProductStatus(localDateTime, productStatus).forEach(pd -> productDtoList.add(mapper.map(pd, ProductDto.class)));
            return productDtoList;
        }
    }

    @Transactional
    @Override
    public Optional<ProductDto> updatePatiallyProductByRef(String ref, ProductDto productDto) {
        if((productDto != null) && (productRepository.findProductByRefProduct(ref).isPresent()) && (productRepository.findProductByRefProduct(ref).get().getProductStatus().equals(ProductStatus.AVALAIBLE))) {
            Product currentProduct = mapper.map(productDto, Product.class);
            Product dbProduct = productRepository.findProductByRefProduct(ref).get();
            dbProduct.setRefProduct(ref);
            mapper.getConfiguration().setSkipNullEnabled(true);
            mapper.map(currentProduct, dbProduct);
            Product updateProduct = productRepository.save(dbProduct);
            return Optional.ofNullable(mapper.map(productRepository.save(updateProduct), ProductDto.class));
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public Optional<ProductDto> updateTotallyProductByRef(String ref, ProductDto productDto) {
        if((productDto != null) && (productRepository.findProductByRefProduct(ref).isPresent())) {
            Product currentProduct = mapper.map(productDto, Product.class);
            currentProduct.setId(productRepository.findProductByRefProduct(ref).get().getId());
            currentProduct.setRefProduct(productRepository.findProductByRefProduct(ref).get().getRefProduct());
            Product updateProduct = productRepository.save(currentProduct);
            return Optional.ofNullable(mapper.map(updateProduct, ProductDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<ProductDto> deleteProduct(String ref) {
        if((productRepository.findProductByRefProduct(ref).isPresent()) && (productRepository.findProductByRefProduct(ref).get().getProductStatus().equals(ProductStatus.AVALAIBLE))) {
            Product pd = productRepository.findProductByRefProduct(ref).get();
            pd.setProductStatus(ProductStatus.UNAVAILABLE);
            return Optional.of(mapper.map(pd, ProductDto.class));
        } else {
            return Optional.empty();
        }
    }
}
