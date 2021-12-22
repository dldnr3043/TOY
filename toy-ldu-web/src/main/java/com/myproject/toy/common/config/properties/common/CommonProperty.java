package com.myproject.toy.common.config.properties.common;

import java.net.URI;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 공통(common) 속성
 * 
 * @author ldu
 *
 */
@Getter
@RequiredArgsConstructor
@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "common")
public class CommonProperty {
	@NotNull
    private final boolean            enabled;
	@NotNull
    private final URI                serviceUrl;  //팔레트 URL
    @NotNull
    private final URI                publicUrl;   //DMZ구간 외부에서 접속할 URL(망분리된 경우 사용/serviceUrl과 같을 수 있음)
    @NotNull
    private final URI                vueUrl;   	  //(SPA를 사용해 프론트 단 view의 vue Resource 출처)
}
