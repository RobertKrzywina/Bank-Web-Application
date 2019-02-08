package pl.robert.project.app.user.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Length(max = 12)
    private String login;

    @NotNull
    @Length(max = 60)
    private String password;

    @NotNull
    private String email;

    @Column(name = "phone_number")
    @NotNull
    @Length(max = 15)
    private String phoneNumber;
}
