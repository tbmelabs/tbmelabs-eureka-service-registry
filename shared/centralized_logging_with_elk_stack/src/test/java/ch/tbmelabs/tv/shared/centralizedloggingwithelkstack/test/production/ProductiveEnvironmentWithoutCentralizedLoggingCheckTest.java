package ch.tbmelabs.tv.shared.centralizedloggingwithelkstack.test.production;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import ch.tbmelabs.tv.shared.centralizedloggingwithelkstack.production.ProductiveEnvironmentWithoutCentralizedLoggingCheck;
import ch.tbmelabs.tv.shared.constants.spring.SpringApplicationProfile;

public class ProductiveEnvironmentWithoutCentralizedLoggingCheckTest {
  @Test
  public void productiveEnvironmentWithoutCentralizedLoggingCheckShouldBeAnnotated() {
    assertThat(ProductiveEnvironmentWithoutCentralizedLoggingCheck.class).hasAnnotation(Component.class);
    assertThat(ProductiveEnvironmentWithoutCentralizedLoggingCheck.class.getDeclaredAnnotation(Profile.class).value())
        .containsExactly(SpringApplicationProfile.PROD);
  }
}