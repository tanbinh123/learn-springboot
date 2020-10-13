package com.sn.springboot.oauth2;

import com.sn.springboot.dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/10/13 17:53
 */
@Service
public class ClientService implements ClientDetailsService {
    @Autowired
    private ClientDao clientDao;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDao.getClientByClientId(clientId);
    }
}
