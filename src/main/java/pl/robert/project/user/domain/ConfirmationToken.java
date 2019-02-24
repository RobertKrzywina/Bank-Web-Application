package pl.robert.project.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "confirmation_tokens")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "confirmation_token", length = 36)
    private String confirmationToken;

    @Column(name = "created_date_in_seconds", length = 11)
    private String createdDateInSeconds;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    ConfirmationToken(User user) {
        this.user = user;
        createdDateInSeconds = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        confirmationToken = UUID.randomUUID().toString();
    }
}
