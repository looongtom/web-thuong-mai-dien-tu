package com.example.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.BinaryJdbcType;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "product",uniqueConstraints = @UniqueConstraint(columnNames = {"name","image"}))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private String description;
    private double costPrice;
    private double salePrice;
    private int currentQuantity;
    @Lob
    @Column(columnDefinition = "BYTEA")
    @JdbcType(BinaryJdbcType.class)
    private String image;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="category_id",referencedColumnName = "category_id")
    private Category category;
    private boolean is_deleted;
    private boolean is_activated;

}
