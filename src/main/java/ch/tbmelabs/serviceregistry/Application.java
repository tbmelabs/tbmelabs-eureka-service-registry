package ch.tbmelabs.serviceregistry;

import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;
import ch.tbmelabs.serverconstants.spring.SpringApplicationProfileEnum;

@EnableEurekaServer
@SpringCloudApplication
public class Application {

  private Environment environment;

  public Application(Environment environment) {
    this.environment = environment;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  public void initBean() {
    if (environment.acceptsProfiles(SpringApplicationProfileEnum.PROD.getName())
        && environment.acceptsProfiles(SpringApplicationProfileEnum.DEV.getName())) {
      throw new IllegalArgumentException(
          "Do not attempt to run an application in productive and development environment at the same time!");
    }
  }
}
