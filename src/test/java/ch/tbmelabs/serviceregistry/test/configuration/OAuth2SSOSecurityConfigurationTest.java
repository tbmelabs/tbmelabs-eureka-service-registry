package ch.tbmelabs.serviceregistry.test.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import ch.tbmelabs.serviceregistry.configuration.OAuth2SSOSecurityConfiguration;

public class OAuth2SSOSecurityConfigurationTest {

  @Test
  public void eurekaConfigurationShouldBeAnnotated() {
    assertThat(OAuth2SSOSecurityConfiguration.class).hasAnnotation(Configuration.class)
        .hasAnnotation(EnableOAuth2Sso.class);
  }

  @Test
  public void oAuth2SSOEurekaConfigurationShouldHavePublicConstructor() {
    assertThat(new OAuth2SSOSecurityConfiguration()).isNotNull();
  }
}
