package pl.robert.project.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static pl.robert.project.validation.Constants.LENGTH_MAX_ROLE;
import static pl.robert.project.validation.Constants.LENGTH_MIN_ROLE;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @Length(min = LENGTH_MIN_ROLE, max = LENGTH_MAX_ROLE)
    String role;
}
