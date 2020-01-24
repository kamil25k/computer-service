package pl.kamil25k.pcservice.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.kamil25k.pcservice.User.UserService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    final private SecurityHandler securityHandler;
    final private UserService userService;

    @Autowired
    public SecurityConfiguration(SecurityHandler securityHandler, UserService userService) {
        this.securityHandler = securityHandler;
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin", "/user/**", "/device/**", "/order/**").hasRole("ADMIN")
                .antMatchers("/login").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").successHandler(securityHandler)
                .loginProcessingUrl("/authenticate")
                .and().logout()
                .and().exceptionHandling().accessDeniedPage("/denied");

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
