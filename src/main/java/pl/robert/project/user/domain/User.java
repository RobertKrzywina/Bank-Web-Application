package pl.robert.project.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.project.bank.account.BankAccount;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@Builder
@ToString
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @Column(length = 12)
    String login;

    @NotNull
    @Column(length = 60)
    String password;

    @NotNull
    String email;

    @NotNull
    @Column(name = "phone_number", length = 15)
    String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    BankAccount bankAccount;

    @Column(name = "is_enabled")
    boolean isEnabled;
}
