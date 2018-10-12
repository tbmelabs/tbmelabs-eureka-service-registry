package ch.tbmelabs.serviceregistry.test.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import ch.tbmelabs.actuatorendpointssecurityutils.annotation.EnableActuatorEndpointSecurity;
import ch.tbmelabs.serviceregistry.configuration.ActuatorEndpointSecurityConfiguration;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;

public class ActuatorEndpointSecurityConfigurationTest {

  public void shouldBeAnnotated() {
    assertThat(ActuatorEndpointSecurityConfiguration.class).hasAnnotation(Configuration.class)
      .hasAnnotation(EnableActuatorEndpointSecurity.class);
  }

  @Test
  public void shouldHavePublicConstructor() {
    assertThat(new ActuatorEndpointSecurityConfiguration()).isNotNull();
  }
}
