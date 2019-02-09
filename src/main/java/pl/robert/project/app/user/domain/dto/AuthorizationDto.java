package pl.robert.project.app.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.robert.project.app.user.domain.Role;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AuthorizationDto {

    private String login;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
