package pl.robert.project.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.project.bank.account.BankAccount;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static pl.robert.project.validation.Constants.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@Builder
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @Column(length = LENGTH_LOGIN)
    String login;

    @NotNull
    @Column(length = LENGTH_PASSWORD)
    String password;

    @NotNull
    String email;

    @NotNull
    @Column(name = "phone_number", length = LENGTH_PHONE_NUMBER)
    String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    BankAccount bankAccount;

    @Column(name = "is_enabled")
    boolean isEnabled;
}
