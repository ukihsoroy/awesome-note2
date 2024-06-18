package org.ko.oauth.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * 配置鉴权服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 数据源
     */
	@Autowired
	private DataSource dataSource;

    /**
     * 权限管理
     */
	@Autowired
    private AuthenticationManager authenticationManager;

    /**
     * spring security user details service.
     */
	@Autowired
	private UserDetailsService userDetailsService;

	/**
     * We expose the JdbcClientDetailsService because it has extra methods that the Interface does not have. E.g.
     * {@link org.springframework.security.oauth2.provider.client.JdbcClientDetailsService#listClientDetails()} which we need for the
     * admin page.
     */
    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 写入到数据库
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 写入数据库
     * @return
     */
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }
    
    @Bean
    public OAuth2AccessDeniedHandler oauth2AccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }
	
	@Bean
    public OAuth2AuthenticationEntryPoint oauth2AuthenticationEntryPoint() {
        return new OAuth2AuthenticationEntryPoint();
    }
	
	@Bean
    public OAuth2RequestFactory oAuth2RequestFactory() {
        return new DefaultOAuth2RequestFactory(jdbcClientDetailsService());
    }
	
	@Bean
	public UserApprovalHandler userApprovalHandler() {
		return new ApprovalHandler(jdbcClientDetailsService(),approvalStore(),oAuth2RequestFactory());
//		return new DefaultUserApprovalHandler();
	}
	
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    	security
    		.accessDeniedHandler(oauth2AccessDeniedHandler())
        	.authenticationEntryPoint(oauth2AuthenticationEntryPoint())
        	.allowFormAuthenticationForClients();
    }

    /**
     * 通过数据库的方式配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
        		.userDetailsService(userDetailsService)
        		.userApprovalHandler(userApprovalHandler())
                .approvalStore(approvalStore())
                .authorizationCodeServices(authorizationCodeServices())
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore());
    }

}