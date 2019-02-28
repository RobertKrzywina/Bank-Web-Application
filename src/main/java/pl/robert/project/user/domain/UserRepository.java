package pl.robert.project.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
    User findByLoginAndPassword(String login, String password);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isEnabled = true WHERE u.email = :email")
    void findUserByEmailAndUpdateEnabled(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email = :newEmail WHERE u.id = :id")
    void findUserByIdAndUpdateEmail(@Param("id") long id, @Param("newEmail") String newEmail);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.phoneNumber = :newPhoneNumber WHERE u.id = :id")
    void findUserByIdAndUpdatePhoneNumber(@Param("id") long id, @Param("newPhoneNumber") String newPhoneNumber);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.id = :id")
    void findUserByIdAndUpdatePassword(@Param("id") long id, @Param("newPassword") String newPassword);
}
