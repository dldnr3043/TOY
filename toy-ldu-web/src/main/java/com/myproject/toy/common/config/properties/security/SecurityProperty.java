package com.myproject.toy.common.config.properties.security;

import java.net.URL;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 보안(security) 속성
 * 
 * @author ldu
 *
 */
@Getter
@RequiredArgsConstructor
@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "security")
public class SecurityProperty {
	@NotNull
    private final URL authSuccessTargetUrl;         //인증 성공 시 대상 URL

    @NotNull
    private final URL logoutSuccessTargetUrl;       //로그아웃 성공 시 대상 URL
}
