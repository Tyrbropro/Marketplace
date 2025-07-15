package com.tyrbropro.user_service.security;

import com.tyrbropro.user_service.repository.jdbc.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomUserServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username).map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
