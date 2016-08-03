package najtek.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
            .antMatchers("/secured/**").fullyAuthenticated()
            .antMatchers("/public/**", "/resources/**").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .loginProcessingUrl("/j_spring_security_check")
            .defaultSuccessUrl("/home")
            .permitAll()
            .and()
        .logout()
            .permitAll()
            .and()
            .csrf().disable();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("a").password("b").roles("USER");
    }
}
