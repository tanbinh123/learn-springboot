package com.sn.springboot.dao;

import com.sn.springboot.pojo.Product;
import com.sn.springboot.pojo.PurchaseRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDao {
    Product getProductById(Long id);

    int decreaseProductStock(@Param(value = "id") Long id, @Param(value = "quantity") Long quantity);

    int addPurchaseRecord(PurchaseRecord purchaseRecord);
}
