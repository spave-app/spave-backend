package com.spave.backend.spave.service;

import com.spave.backend.spave.model.UserInfo;
import com.spave.backend.spave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(PasswordEncoder encoder, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public List<UserInfo> getUsers() {
        return userRepository.findAll();
    }

    public UserInfo addUser(UserInfo newUserInfo) {
        newUserInfo.setPassword(encoder.encode(newUserInfo.getPassword()));
        return userRepository.save(newUserInfo);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userRepository.findByEmail(email);

        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        UserInfo user = userInfo.get();
        return new User(user.getEmail(), user.getPassword(),
                Arrays.stream(user.getRoles().split(","))
                        .map(role -> new SimpleGrantedAuthority(role.trim()))
                        .toList());
    }
}
