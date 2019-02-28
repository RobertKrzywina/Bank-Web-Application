package pl.robert.project.user.query;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.project.user.domain.Role;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@Builder
public class UserQuery {

    long id;
    String login;
    String email;
    String phoneNumber;
    String bankAccountNumber;
    double balance;
    Set<Role> roles;
    String rolesToDisplay;
}
