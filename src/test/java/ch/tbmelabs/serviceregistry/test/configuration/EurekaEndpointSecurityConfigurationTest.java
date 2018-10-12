package ch.tbmelabs.serviceregistry.test.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import ch.tbmelabs.serviceregistry.configuration.ApplicationProperties;
import ch.tbmelabs.serviceregistry.configuration.ApplicationProperties.Eureka;
import ch.tbmelabs.serviceregistry.configuration.ApplicationProperties.Eureka.Administrator;
import ch.tbmelabs.serviceregistry.configuration.EurekaEndpointSecurityConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

public class EurekaEndpointSecurityConfigurationTest {

  ApplicationProperties applicationProperties;

  @Before
  public void beforeTestSetup() {
    applicationProperties = new ApplicationProperties();
    applicationProperties.setEureka(new Eureka());
    applicationProperties.getEureka().setAdministrator(new Administrator());
    applicationProperties.getEureka().getAdministrator().setName("name");
    applicationProperties.getEureka().getAdministrator().setPassword("password");
  }

  @Test
  public void shouldBeAnnotated() {
    assertThat(EurekaEndpointSecurityConfiguration.class).hasAnnotation(Order.class)
      .hasAnnotation(Configuration.class).hasAnnotation(EnableWebSecurity.class);
  }

  @Test
  public void isFirstOrder() {
    Order orderAnnotation =
      EurekaEndpointSecurityConfiguration.class.getDeclaredAnnotation(Order.class);

    assertThat(orderAnnotation.value()).isEqualTo(1);
  }

  @Test
  public void shouldHavePublicConstructor() {
    assertThat(new EurekaEndpointSecurityConfiguration(Mockito.mock(ObjectPostProcessor.class),
      applicationProperties)).isNotNull();
  }
}
