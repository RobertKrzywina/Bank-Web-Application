package pl.robert.project.app.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
    User findByLoginAndPassword(String login, String password);
}
