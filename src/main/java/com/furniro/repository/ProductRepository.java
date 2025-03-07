package com.furniro.repository;

import com.furniro.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p.id, p.name, p.description, p.more_description, p.stock, p.price, p.original_price, p.image_url, " +
            "to_char(p.created_at, 'YYYY-MM-DD HH24:MI:SS') as created_at, " +
            "to_char(p.updated_at, 'YYYY-MM-DD HH24:MI:SS') as updated_at, " +
            "STRING_AGG(DISTINCT t.name, ',') as tagNames, " +
            "COALESCE(AVG(pr.rating), 0) as avgRating, " +
            "COUNT(DISTINCT pr.id) as reviewCount " +
            "FROM products p " +
            "LEFT JOIN product_tags pt ON p.id = pt.product_id " +
            "LEFT JOIN tags t ON pt.tag_id = t.id " +
            "LEFT JOIN product_reviews pr ON p.id = pr.product_id " +
            "GROUP BY p.id, p.name, p.description, p.more_description, p.stock, p.price, p.original_price, p.image_url, p.created_at, p.updated_at",
            nativeQuery = true)
    List<Object[]> findAllWithTagsAndReviews();

    @Query(value = "SELECT p.id, p.name, p.description, p.more_description, p.stock, p.price, p.original_price, p.image_url, " +
            "to_char(p.created_at, 'YYYY-MM-DD HH24:MI:SS') as created_at, " +
            "to_char(p.updated_at, 'YYYY-MM-DD HH24:MI:SS') as updated_at, " +
            "STRING_AGG(DISTINCT t.name, ',') as tagNames, " +
            "COALESCE(AVG(pr.rating), 0) as avgRating, " +
            "COUNT(DISTINCT pr.id) as reviewCount " +
            "FROM products p " +
            "LEFT JOIN product_tags pt ON p.id = pt.product_id " +
            "LEFT JOIN tags t ON pt.tag_id = t.id " +
            "LEFT JOIN product_reviews pr ON p.id = pr.product_id " +
            "WHERE p.id = :id " +
            "GROUP BY p.id, p.name, p.description, p.more_description, p.stock, p.price, p.original_price, p.image_url, p.created_at, p.updated_at",
            nativeQuery = true)
    Optional<Object[]> findByIdWithTagsAndReviews(@Param("id") Long id);
}