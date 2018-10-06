package ch.tbmelabs.serviceregistry.test.application;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import ch.tbmelabs.serviceregistry.Application;

public class ApplicationPackageNamingTest {

  private static final String APPLICATION_PACKAGE_NAME = "ch.tbmelabs.serviceregistry";

  @Test
  public void applicationPackageShouldMatchNamingConvention() {
    assertThat(Application.class.getPackage().getName()).isEqualTo(APPLICATION_PACKAGE_NAME);
  }
}
