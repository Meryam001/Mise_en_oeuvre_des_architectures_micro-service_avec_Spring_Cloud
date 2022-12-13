package com.example.billingservice.repository;

import com.example.billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;
import java.util.Optional;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
    //public Optional<ProductItem> findById(Long id);
    @RestResource(path = "/byBillId")
    public Collection<ProductItem> findAllByBill_Id(Long id);

}
