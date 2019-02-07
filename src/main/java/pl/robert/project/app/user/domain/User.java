package pl.robert.project.app.user.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login;

    private String password;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
}
