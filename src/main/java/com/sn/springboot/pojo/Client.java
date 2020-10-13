package com.sn.springboot.pojo;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/10/13 17:28
 */
@Alias("client")
public class Client implements ClientDetails {
    private static final long serialVersionUID = -7593113692318584603L;

    private Long id;

    private String clientId;

    private String resourceIds;

    private String scopes;

    private Integer accessTokenValiditySeconds;

    private String authorizedGrantTypes;

    private String secret;

    private String authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        Set<String> sets = new HashSet<>(Arrays.asList(resourceIds.split(",")));
        return sets;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return secret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        Set<String> sets = new HashSet<>(Arrays.asList(scopes.split(",")));
        return sets;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> sets = new HashSet<>(Arrays.asList(authorizedGrantTypes.split(",")));
        return sets;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();

        for (String s : authorities.split(",")) {
            list.add(new SimpleGrantedAuthority(s));
        }
        return list;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return -1;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
