package ru.vadim.naujavaprjct.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.exception.UserNotFoundException;
import ru.vadim.naujavaprjct.repository.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class MyUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        User appUser = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        return new
                org.springframework.security.core.userdetails.User(
                appUser.getUsername(), appUser.getPassword(),
                mapRoles(appUser));
    }

    private Collection<GrantedAuthority> mapRoles(User appUser) {
        return appUser.getAuthorities().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getAuthority()))
                .collect(Collectors.toList());
    }
}
