package pl.robert.project.app.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);
}
