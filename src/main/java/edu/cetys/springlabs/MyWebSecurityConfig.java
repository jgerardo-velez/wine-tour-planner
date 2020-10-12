package edu.cetys.springlabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
    public MyWebSecurityConfig(MyAuthenticationSuccessHandler myAuthenticationSuccessHandler) {
        this.authenticationSuccessHandler = myAuthenticationSuccessHandler;
    }
    
	
	@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("admin@winetourplanner.com").password(passwordEncoder().encode("cetys123")).roles("ADMIN")
          .and()
          .withUser("tourist@winetourplanner.com").password(passwordEncoder().encode("cetys123")).roles("TOURIST")
          .and()
          .withUser("vintner@winetourplanner.com").password(passwordEncoder().encode("cetys123")).roles("VINTNER");
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
        	.antMatchers("/sign-up").permitAll()
        	.antMatchers("/forgot-password").permitAll()
        	.antMatchers("/css/*").permitAll()
        	.antMatchers("/images/*").permitAll()
        	.antMatchers("/admin-dashboard").hasRole("ADMIN")
        	.antMatchers("/tourist-dashboard").hasRole("TOURIST")
        	.antMatchers("/winery-dashboard").hasRole("VINTNER")
        	.anyRequest().authenticated() 
            .and()
        .formLogin()                      
            .successHandler(authenticationSuccessHandler)
            .permitAll()
            .and()
        .httpBasic()
    	    .and()
        .logout()
            .permitAll()
            .and()
        .csrf().disable();
        
    	
    	
    	/*
    	http
    		.csrf().disable()
    		.authorizeRequests()
    		.antMatchers("/sign-up").permitAll()
    		.antMatchers("/forgot-password").permitAll()
    		.antMatchers("/css/*").permitAll()
    		.antMatchers("/images/*").permitAll()
    		.anyRequest().authenticated()
    		.and()
    		.formLogin()
    		.loginPage("/")
    		///.defaultSuccessUrl("/admin-dashboard", true)
    		.permitAll();
    		*/
    	
    	/*
        http
          .csrf().disable()
          .authorizeRequests()
          .antMatchers("/admin/**").hasRole("ADMIN")
          .antMatchers("/anonymous*").anonymous()
          .antMatchers("/login*").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/login.html")
          .loginProcessingUrl("/perform_login")
          .defaultSuccessUrl("/homepage.html", true)
          //.failureUrl("/login.html?error=true")
          .failureHandler(authenticationFailureHandler())
          .and()
          .logout()
          .logoutUrl("/perform_logout")
          .deleteCookies("JSESSIONID")
          .logoutSuccessHandler(logoutSuccessHandler());
       */   
    }
	
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
