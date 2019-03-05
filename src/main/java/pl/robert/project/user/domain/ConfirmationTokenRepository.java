package pl.robert.project.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);
    ConfirmationToken findById(long id);
    ConfirmationToken findByUser(User user);
}
