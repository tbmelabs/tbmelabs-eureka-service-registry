package ch.tbmelabs.serviceregistry.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ch.tbmelabs.serverconstants.security.ClientUserRoleEnum;
import ch.tbmelabs.serviceregistry.configuration.ApplicationProperties;

@Order(1)
@Configuration
@EnableWebSecurity
public class EurekaEndpointSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  private ObjectPostProcessor<Object> objectPostProcessor;

  private String eurekaUserName;
  private String eurekaUserPassword;

  public EurekaEndpointSecurityConfiguration(ObjectPostProcessor<Object> objectPostProcessor,
      ApplicationProperties applicationProperties) {
    this.objectPostProcessor = objectPostProcessor;

    this.eurekaUserName = applicationProperties.getEureka().getAdministrator().getName();
    this.eurekaUserPassword = applicationProperties.getEureka().getAdministrator().getPassword();
  }

  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);
    builder.inMemoryAuthentication().passwordEncoder(PASSWORD_ENCODER).withUser(eurekaUserName)
        .password(PASSWORD_ENCODER.encode(eurekaUserPassword))
        .roles(ClientUserRoleEnum.EUREKA_ENDPOINT.getAuthority());
    return builder.build();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http

        .csrf().ignoringAntMatchers("/eureka/**")

        .and().antMatcher("/eureka/**").authorizeRequests()
          .antMatchers("/eureka/**").hasRole(ClientUserRoleEnum.EUREKA_ENDPOINT.getAuthority())

        .and().httpBasic();
    // @formatter:on
  }
}
