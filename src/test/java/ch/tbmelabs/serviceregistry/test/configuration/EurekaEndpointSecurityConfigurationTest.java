package ch.tbmelabs.serviceregistry.test.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import ch.tbmelabs.actuatorendpointssecurityutils.annotation.EnableActuatorEndpointSecurity;
import ch.tbmelabs.serviceregistry.configuration.ApplicationProperties;
import ch.tbmelabs.serviceregistry.configuration.EurekaEndpointSecurityConfiguration;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;

public class EurekaEndpointSecurityConfigurationTest {

  public void shouldBeAnnotated() {
    assertThat(EurekaEndpointSecurityConfiguration.class).hasAnnotation(Configuration.class)
      .hasAnnotation(EnableActuatorEndpointSecurity.class);
  }

  @Test
  public void shouldHavePublicConstructor() {
    assertThat(new EurekaEndpointSecurityConfiguration(Mockito.mock(ObjectPostProcessor.class),
      Mockito.mock(ApplicationProperties.class))).isNotNull();
  }
}
