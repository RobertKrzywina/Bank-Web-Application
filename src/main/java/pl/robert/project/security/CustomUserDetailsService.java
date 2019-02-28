package pl.robert.project.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.robert.project.user.domain.Role;
import pl.robert.project.user.domain.UserFacade;
import pl.robert.project.user.domain.dto.AuthorizationDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class CustomUserDetailsService implements UserDetailsService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AuthorizationDTO user = userFacade.findByLogin(login);

        if (user == null) throw new UsernameNotFoundException("User not found");

        if (!user.isVerified()) {
            user.setPassword(UUID.randomUUID().toString());
            logger.warn("User is not verified! Changing password to random UUID!");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                convertAuthorities(user.getRoles()));
    }

    private Set<GrantedAuthority> convertAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }
}
