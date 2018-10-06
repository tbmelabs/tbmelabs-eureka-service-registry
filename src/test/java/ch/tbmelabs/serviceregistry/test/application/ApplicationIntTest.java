package ch.tbmelabs.serviceregistry.test.application;

import ch.tbmelabs.serviceregistry.Application;
import ch.tbmelabs.serviceregistry.test.AbstractEurekaServerContextAwareTest;
import org.junit.Test;

public class ApplicationIntTest extends AbstractEurekaServerContextAwareTest {

  @Test
  public void applicationContextLoads() {
  }

  @Test
  public void publicStaticVoidMainShouldStartSpringApplication() {
    Application.main(new String[]{"--spring.profiles.active=test"});
  }
}
