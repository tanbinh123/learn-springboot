package com.sn.springboot.dao;

import com.sn.springboot.pojo.Client;
import org.springframework.stereotype.Repository;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/10/10 10:59
 */
@Repository
public interface ClientDao {
    Client getClientByClientId(String clientId);
}
