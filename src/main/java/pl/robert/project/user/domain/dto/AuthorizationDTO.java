package pl.robert.project.user.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.project.user.domain.Role;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class AuthorizationDTO {

    String login;
    String password;
    boolean isVerified;
    Set<Role> roles = new HashSet<>();
}
