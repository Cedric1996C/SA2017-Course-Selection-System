package hello.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by njucong on 2017/5/27.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/users/**").authenticated()
                .antMatchers(HttpMethod.POST, "/students/pageInfo").permitAll()
                .antMatchers("/batchimport").permitAll()
                .antMatchers(HttpMethod.POST, "/students").permitAll()
                .antMatchers(HttpMethod.POST,"/students/add").permitAll()
                .antMatchers(HttpMethod.POST,"/students/update").permitAll()
                .antMatchers(HttpMethod.DELETE, "/students/delete/*").permitAll()
                .anyRequest().authenticated();
    }

}
