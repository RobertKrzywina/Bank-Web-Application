package pl.robert.project.security;

import lombok.AllArgsConstructor;
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

@Component
@AllArgsConstructor
class CustomUserDetailsService implements UserDetailsService {

    private UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AuthorizationDTO user = userFacade.findByLogin(login);

        if (user == null) throw new UsernameNotFoundException("User not found");

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
