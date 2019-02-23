package pl.robert.project.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.robert.project.user.domain.Role;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AuthorizationDTO {

    private String login;
    private String password;
    private boolean isVerified;
    private Set<Role> roles = new HashSet<>();
}
