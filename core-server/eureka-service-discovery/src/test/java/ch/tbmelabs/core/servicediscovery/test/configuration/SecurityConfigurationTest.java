package ch.tbmelabs.core.servicediscovery.test.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import ch.tbmelabs.core.servicediscovery.test.AbstractEurekaApplicationContextAware;
import ch.tbmelabs.tv.core.servicediscovery.configuration.SecurityConfiguration;

public class SecurityConfigurationTest extends AbstractEurekaApplicationContextAware {
  @Test
  public void securityConfigurationShouldBeAnnotated() {
    assertThat(SecurityConfiguration.class).hasAnnotation(Configuration.class);
    assertThat(SecurityConfiguration.class).hasAnnotation(EnableWebSecurity.class);
    assertThat(SecurityConfiguration.class).hasAnnotation(EnableGlobalMethodSecurity.class);
  }
}