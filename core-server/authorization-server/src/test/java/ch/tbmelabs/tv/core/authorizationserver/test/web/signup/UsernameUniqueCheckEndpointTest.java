package ch.tbmelabs.tv.core.authorizationserver.test.web.signup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import ch.tbmelabs.tv.core.authorizationserver.domain.repository.AuthenticationLogCRUDRepository;
import ch.tbmelabs.tv.core.authorizationserver.service.bruteforce.BruteforceFilterService;
import ch.tbmelabs.tv.core.authorizationserver.test.AbstractOAuth2AuthorizationApplicationContextAware;
import ch.tbmelabs.tv.core.authorizationserver.test.utils.testdata.TestUserManager;

@Transactional
public class UsernameUniqueCheckEndpointTest extends AbstractOAuth2AuthorizationApplicationContextAware {
  private static final String USERNAME_UNIQUE_CHECK_ENDPOINT = "/signup/is-username-unique";
  private static final String USERNAME_PARAMETER_NAME = "username";

  private static final String USERNAME_NOT_UNIQUE_ERROR_MESSAGE = "Username already exists!";

  private static final String VALID_USERNAME = "ValidUsername";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AuthenticationLogCRUDRepository authenticationLogRepository;

  @Autowired
  private TestUserManager testUserManager;

  @Before
  public void beforeTestSetUp() {
    authenticationLogRepository.deleteAll();

    BruteforceFilterService.resetFilter();
  }

  @Test(expected = NestedServletException.class)
  public void registrationWithExistingUsernameShouldFailValidation() throws Exception {
    try {
      mockMvc
          .perform(post(USERNAME_UNIQUE_CHECK_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content(
              new JSONObject().put(USERNAME_PARAMETER_NAME, testUserManager.getUserUser().getUsername()).toString()))
          .andDo(print()).andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    } catch (NestedServletException e) {
      assertThat(e.getCause()).isNotNull().isOfAnyClassIn(IllegalArgumentException.class)
          .hasMessage(USERNAME_NOT_UNIQUE_ERROR_MESSAGE);

      throw e;
    }
  }

  @Test
  public void registrationWithNewUsernameShouldPassValidation() throws Exception {
    mockMvc
        .perform(post(USERNAME_UNIQUE_CHECK_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
            .content(new JSONObject().put(USERNAME_PARAMETER_NAME, VALID_USERNAME).toString()))
        .andDo(print()).andExpect(status().is(HttpStatus.OK.value()));
  }
}