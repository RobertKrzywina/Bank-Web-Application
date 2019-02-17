package pl.robert.project.user.query;

import lombok.*;
import pl.robert.project.user.domain.Role;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class UserQuery {

    private long id;
    private String login;
    private String email;
    private String phoneNumber;
    private String password;
    private String bankAccountNumber;
    private double balance;
    private Set<Role> roles;
}
