package com.sn.springboot.dao;

import com.sn.springboot.pojo.Product;
import com.sn.springboot.pojo.PurchaseRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDao {
    Product getProductById(Long id);

    int decreaseProductStock2(@Param(value = "id") Long id, @Param(value = "quantity") Integer quantity, @Param(value = "version") Integer version);

    int decreaseProductStock(@Param(value = "id") Long id, @Param(value = "quantity") Integer quantity);

    int addPurchaseRecord(PurchaseRecord purchaseRecord);
}
