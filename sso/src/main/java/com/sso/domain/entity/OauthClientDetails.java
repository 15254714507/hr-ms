package com.sso.domain.entity;

import lombok.Data;

/**
 * @author 孔超
 * @date 2020/4/9 15:12
 */
@Data
public class OauthClientDetails {
    private Long id;
    /**
     * 跳转到目标的客户端id
     */
    private String clientId;
    /**
     *
     */
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;
}
