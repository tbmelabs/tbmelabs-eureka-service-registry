package ch.tbmelabs.serviceregistry.test.application;

import static org.assertj.core.api.Assertions.assertThat;

import ch.tbmelabs.serviceregistry.Application;
import org.junit.Test;

public class ApplicationPackageNamingTest {

  private static final String APPLICATION_PACKAGE_NAME = "ch.tbmelabs.serviceregistry";

  @Test
  public void packageShouldMatchNamingConvention() {
    assertThat(Application.class.getPackage().getName()).isEqualTo(APPLICATION_PACKAGE_NAME);
  }
}
