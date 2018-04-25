package ch.tbmelabs.tv.core.authorizationserver.domain.dto;

import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends AbstractBasicEntityDTO {

  private String username;
  private String email;
  private Boolean isEnabled = false;
  private Boolean isBlocked = false;
  private String emailConfirmationToken;
  private Collection<RoleDTO> roles;
}