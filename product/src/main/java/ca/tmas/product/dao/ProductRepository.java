package ca.tmas.product.dao;

import ca.tmas.product.entities.Product;
import ca.tmas.product.utility.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("Select pd From Product pd Where pd.refProduct like :ref")
    Optional<Product> findProductByRefProduct(@Param("ref") String ref);

    @Query("Select pd.refProduct From Product pd where pd.refProduct is not null")
    Set<String> findAllRefProduct();

    @Query("Select pd From Product pd Where pd.nameProduct like :nameProduct")
    List<Product> findAllProductByNameProduct(@Param("nameProduct") String nameProduct);

    @Query("Select pd From Product pd Where pd.priceProduct = :priceProduct")
    List<Product> findProductByPriceProduct(@Param("priceProduct") double priceProduct);

    @Query("Select pd From Product pd Where (pd.priceProduct >= :priceProduct1) And (pd.priceProduct <= :priceProduct2)")
    List<Product> findAllProductBetweenPriceProduct(@Param("priceProduct1") double priceProduct1, @Param("priceProduct2") double priceProduct2);

    @Query("Select pd From Product pd Where (pd.nameProduct like :nameProduct) And (pd.productStatus = :productStatus)")
    Optional<Product> findProductByNameProductAndProductStatus(@Param("nameProduct") String nameProduct, @Param("productStatus") ProductStatus productStatus);

    @Query("Select pd From Product pd Where pd.productStatus = :productStatus")
    List<Product> findAllProductByProductStatus(@Param("productStatus") ProductStatus productStatus);

    @Query("Select pd From Product pd Where pd.saleDate = :saleDate")
    Optional<Product> findProductBySaleDate(@Param("saleDate") LocalDateTime saleDate);

    @Query("Select pd From Product pd Where (pd.saleDate = :saleDate) And (pd.productStatus = :productStatus)")
    List<Product> findAllProductBySaleDateAndProductStatus(@Param("saleDate") LocalDateTime saleDate, @Param("productStatus") ProductStatus productStatus);

}
