package org.factoriaf5.happypaws.security;

import java.util.Optional;

import org.factoriaf5.happypaws.user.UserEntity;

//import java.util.Optional;

//import org.factoriaf5.happypaws.user.UserEntity;
import org.factoriaf5.happypaws.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.findByUsername(username);

        System.out.println("<----- " + user.get().getUsername());
        System.out.println("<----- " + user.get().getPassword());
        System.out.println("<----- " + user.get().getRoles().toString());

        return userRepository.findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

}