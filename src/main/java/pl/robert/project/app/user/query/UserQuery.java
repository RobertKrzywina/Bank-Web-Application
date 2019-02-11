package pl.robert.project.app.user.query;

import lombok.*;

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
}
