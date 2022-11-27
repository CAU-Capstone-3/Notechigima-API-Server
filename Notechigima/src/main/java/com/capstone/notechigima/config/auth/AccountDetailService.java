package com.capstone.notechigima.config.auth;

import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일과 일치하는 계정이 없습니다."));

        return new AccountDetails(user);
    }
}
