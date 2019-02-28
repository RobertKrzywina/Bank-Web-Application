package pl.robert.project.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "confirmation_tokens")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;

    @Column(name = "confirmation_token", length = 36)
    String confirmationToken;

    @Column(name = "created_date_in_seconds", length = 11)
    String createdDateInSeconds;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    User user;

    ConfirmationToken(User user) {
        this.user = user;
        createdDateInSeconds = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        confirmationToken = UUID.randomUUID().toString();
    }
}
