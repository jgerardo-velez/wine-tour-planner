package edu.cetys.springlabs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
    public MyWebSecurityConfig(MyAuthenticationSuccessHandler myAuthenticationSuccessHandler) {
        this.authenticationSuccessHandler = myAuthenticationSuccessHandler;
    }
    
	
	@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
		
		
		/*
		System.out.println("PASSWORD ENCODED: " + passwordEncoder().encode("cetys123"));
		
		
		
        auth.inMemoryAuthentication()
          .withUser("admin@winetourplanner.com").password(passwordEncoder().encode("cetys123")).roles("ADMIN")
          .and()
          .withUser("tourist@winetourplanner.com").password(passwordEncoder().encode("cetys123")).roles("TOURIST")
          .and()
          .withUser("vintner@winetourplanner.com").password(passwordEncoder().encode("cetys123")).roles("VINTNER");
         */
    }
    
		
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	
    	// Default behaviour
    	/*
    	http
        .authorizeRequests()
        	.anyRequest().authenticated() 
            .and()
        .formLogin()                      
            .and()
        .httpBasic();  
        */
    	
    	
    	http
        .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/password-reset").permitAll()
            .antMatchers(HttpMethod.POST, "/sign-up").permitAll()
        	.antMatchers("/sign-up").permitAll()
        	.antMatchers("/forgot-password").permitAll()
        	.antMatchers("/css/*").permitAll()
        	.antMatchers("/images/*").permitAll()
        	.antMatchers("/admin-dashboard").hasRole("ADMIN")
        	.antMatchers("/tourist-dashboard").hasAnyRole("ADMIN", "VINTNER", "TOURIST")
        	.antMatchers("/winery-dashboard").hasAnyRole("ADMIN", "VINTNER")
        	.anyRequest().authenticated() 
            .and()
        .formLogin()           
        	.loginPage("/login").permitAll()           
            .successHandler(authenticationSuccessHandler)
            .and()
        .httpBasic()
    	    .and()
        .logout()
        	.clearAuthentication(true)
        	.invalidateHttpSession(true)    // set invalidation state when logout
        	.deleteCookies("JSESSIONID")     
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))            
            .logoutSuccessUrl("/login")
        .and()    
        .csrf().disable();    // temporary disable to allow POST messages)  
    }
	
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    /*
    @Bean
    public PasswordEncoder getPasswordEncoder() {
    	return NoOpPasswordEncoder.getInstance();
    }
    */
    
 
}
