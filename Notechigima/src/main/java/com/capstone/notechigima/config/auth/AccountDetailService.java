package com.capstone.notechigima.config.auth;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.RestApiException;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws RestApiException {
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> {
            throw new RestApiException(ExceptionCode.ERROR_NOT_FOUND_USER);
        });

        return new AccountDetails(user);
    }
}
