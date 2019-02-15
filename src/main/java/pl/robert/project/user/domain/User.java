package pl.robert.project.user.domain;

import lombok.*;
import pl.robert.project.bank_account.domain.BankAccount;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

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
    @Column(length = 12)
    private String login;

    @NotNull
    @Column(length = 60)
    private String password;

    @NotNull
    @Column(name = "decoded_b_crypt_password")
    private String decodedBCryptPassword;

    @NotNull
    private String email;

    @NotNull
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;
}
