package com.myproject.toy.common.security.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
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
import com.myproject.toy.login.util.JwtAuthenticationFilter;
import com.myproject.toy.login.util.JwtTokenProvider;

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
	
	private final CommonProperty   commonProperty;
	private final JwtTokenProvider jwtTokenProvider;
//	private final SecurityProperty securityProperty;
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
            .cors()	// 필수
            .and()
            .csrf().disable()
            .authorizeRequests()
            // vue.js 클라이언트 + spring boot rest api(db포함) 서버 개발 시 API와 Web Application이 서로 다른 출처
            // api : ex) localhost:8080		Web Application : ex) localhost:3000
            // SPA를 통해 vue단에서 routing 처리, 기존 서버에서 view를 렌더링 시키는 방식과는 다름
            // 그래서 cors를 처리 : 무분별한 http 통신을 제한하고 같은 출처 혹은 특정 url에 한해서만 통신 허용
            // preflight는 현재 Web Application과 호출한 api의 Resource 출처가 동일한지 먼저 http Options 메소드로 통신을 시도해보는 것
            // 따라서 preflight는 인증하지 않아도 됨
            // 출처 : https://sas-study.tistory.com/298
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
        
        
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
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
//           .antMatchers("/infra/hkcdv/api/chatbotContactHistory")
//           .antMatchers("/message"
//                      , "/reference"
//                      , "/expired_session"
//                      , "/message_bot")
//           .antMatchers("/message/**/")
           .antMatchers("/**/css/**")
           .antMatchers("/**/img/**")
           .antMatchers("/**/js/**")
           .antMatchers("/**/font/**")
           .antMatchers("/webjars/**")
           .antMatchers("/error*/**")
           .antMatchers("/favicon*/**")
//           .antMatchers("/RepositoryPublic/**")
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
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
      System.out.println("----------------cors config-----------------------");

      CorsConfiguration configuration = new CorsConfiguration();

      // vue 단의 url 허용
      configuration.addAllowedOrigin(commonProperty.getVueUrl().toString());
      configuration.addAllowedMethod("*");
      configuration.addAllowedHeader("*");
      configuration.setAllowCredentials(true);
      configuration.setMaxAge(3600L);
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);

      System.out.println("----------------cors config end-----------------------");
      return source;
    }
    
    /**
     * SuccessHandler bean register
     * 
     * Form Login(AuthenticationFilter)에서 인증이 성공했을 때 수행될 핸들러이다.
     * SimpleUrlAuthenticationSuccessHandler를 상속한 SavedRequestAwareAuthenticationSuccessHandler를
     * 다시 상속한 TeletalkAuthenticationSuccessHandler를 등록해주었다.
     * @return successHandler
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() throws Exception
    {
    	log.info("test :::::::");
        ToyAuthenticationSuccessHandler successHandler = new ToyAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("http://localhost:8080/web/main");
        return successHandler;
    }
}
