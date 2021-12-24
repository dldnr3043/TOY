package com.myproject.toy.common.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.myproject.toy.common.config.properties.common.CommonProperty;
import com.myproject.toy.login.utils.JwtAuthenticationFilter;
import com.myproject.toy.login.utils.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 스프링 시큐리티 설정 
 * 
 * @EnableWebSecurity 어노테이션을 통해 스프링 시큐리티를 사용하겠다라고 선언
 * WebSecurityConfigurerAdapter를 상속
 * 
 * 출처: https://coding-start.tistory.com/153 [코딩스타트]
 * 
 * @author 이동욱
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(securedEnabled = true)
@Slf4j
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
    private final JwtTokenProvider jwtTokenProvider;

    // 암호화에 필요한 PasswordEncoder 를 Bean 등록합니다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // authenticationManager를 Bean 등록합니다.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	    
    /**
     * HttpSecurity객체는 현재 로그인한 사용자가 적절한 역할과 연결돼 있는지 확인하는 서블릿 필터를 생성한다.
     * ant 패턴식 설명
     * 1) ? -> 단일 문자와 일치한다.
     * 2) * ->/를 제외하는 0자 이상의 문자와 일치한다. (ex "/events*" == "/events","/events123")
     * 3) ** ->경로의 0개 이상의 디렉터리와 일치한다. (ex "/events/**" == "/events","/events/","/events/1","/events/1/form?test=1")
     * 
     * @see kr.co.hkcloud.palette.config.webmvc.SpringWebMvcConfig
     * @see kr.co.hkcloud.palette.config.security.TeletalkAuthenticationInterceptor
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .cors()	// VUE와 API 단의 Resource 출처가 달라 cors 적용 필요하므로 설정
            .and()
            .csrf().disable()	// csrf 공격 방어를 위해 설정 (csrf : 사용자의 의도가 없이 공격자가 의도한 행위의 request를 하도록 하는 공격) TODO 사용하는 것으로 설정 필요
            
            // 토큰 기반 인증이므로 세션 사용 해제 설정
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            
            .and()
            .authorizeRequests()
            // vue.js 클라이언트 + spring boot rest api(db포함) 서버 개발 시 API와 Web Application이 서로 다른 출처
            // api : ex) localhost:8080		Web Application : ex) localhost:3000
            // SPA를 통해 vue단에서 routing 처리, 기존 서버에서 view를 렌더링 시키는 방식과는 다름
            // 그래서 cors를 처리 : 무분별한 http 통신을 제한하고 같은 출처 혹은 특정 url에 한해서만 통신 허용
            // preflight는 현재 Web Application과 호출한 api의 Resource 출처가 동일한지 먼저 http Options 메소드로 통신을 시도해보는 것
            // 따라서 preflight는 인증하지 않아도 됨
            // 출처 : https://sas-study.tistory.com/298
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            
            //로그인 / 로그아웃 / 회원가입은 별도 인증 필요없이 허용
            .antMatchers("/login").permitAll()
            .antMatchers("/logout").permitAll()
            .antMatchers("/signup").permitAll()
            
            // 그 외 나머지 url은 인증 필요
            .anyRequest().authenticated()
            
            // 인증, 인가 예외발생시 처리 TODO
            .and()
            .exceptionHandling()
//            .authenticationEntryPoint(authenticationEntryPoint)	// 인증 예외시	ex) redirect login page
//            .accessDeniedHandler(accessDeniedHandler)				// 인가 예외시	ex) redirect denied page
            
            // 실제 아이디 비밀번호 인증 하는 UsernamePasswordAuthenticationFilter 전에 Jwt 토큰 값 인증필터 수행
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                    UsernamePasswordAuthenticationFilter.class);
            ;
    }
    
    /**
     * Security Filter ignoring
     */
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.httpFirewall(strictHttpFirewall())
           .ignoring()
        //- antMatchers는 순서가 굉장히 중요하다.
           .antMatchers("/")
           .antMatchers("/**/css/**")
           .antMatchers("/**/img/**")
           .antMatchers("/**/js/**")
           .antMatchers("/**/font/**")
           .antMatchers("/webjars/**")
           .antMatchers("/error*/**")
           .antMatchers("/favicon*/**")
         ;
    }
    
    /**
     * 
     * @return
     */
    @Bean
    public HttpFirewall strictHttpFirewall()
    {
        StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
        // HTTP Method White List
        strictHttpFirewall.setAllowedHttpMethods(Arrays.asList(HttpMethod.GET.name()
                                                             , HttpMethod.POST.name()
                                                             , HttpMethod.PUT.name()
                                                             , HttpMethod.HEAD.name()));
        return strictHttpFirewall;
    }
    
    /**
     * cors config 설정 함수
     * Bean으로 등록하여 사용
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
      System.out.println("----------------cors config-----------------------");

      CorsConfiguration configuration = new CorsConfiguration();

      // vue 단의 url 허용	TODO 프로퍼티로 수정 필요
      configuration.addAllowedOrigin("http://localhost:3000");
      configuration.addAllowedMethod("*");
      configuration.addAllowedHeader("*");
      configuration.setAllowCredentials(true);
      configuration.setMaxAge(3600L);
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);

      System.out.println("----------------cors config end-----------------------");
      return source;
    }
}
