package ch.tbmelabs.serviceregistry.test.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import ch.tbmelabs.serviceregistry.configuration.OAuth2SSOSecurityConfiguration;

public class OAuth2SSOSecurityConfigurationTest {

  @Test
  public void shouldBeAnnotated() {
    assertThat(OAuth2SSOSecurityConfiguration.class).hasAnnotation(Configuration.class)
        // .hasAnnotation(EnableOAuth2Sso.class)
        .hasAnnotation(EnableWebSecurity.class).hasAnnotation(EnableGlobalMethodSecurity.class);
  }

  @Test
  public void enablesGlobalPrePostMethodSecurity() {
    final EnableGlobalMethodSecurity enableGlobalMethodSecurityAnnotation =
        OAuth2SSOSecurityConfiguration.class
            .getDeclaredAnnotation(EnableGlobalMethodSecurity.class);

    assertThat(enableGlobalMethodSecurityAnnotation.prePostEnabled()).isTrue();
  }

  @Test
  public void shouldHavePublicConstructor() {
    assertThat(new OAuth2SSOSecurityConfiguration()).isNotNull();
  }
}
