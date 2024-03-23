package br.com.fiap.hackaton.clockregistryapi.security;

import br.com.fiap.hackaton.clockregistryapi.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final br.com.fiap.hackaton.clockregistryapi.service.UserService userService;

    public UserServiceImpl(br.com.fiap.hackaton.clockregistryapi.service.UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userService.findByUsername(username);
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), Collections.emptyList());
        };
    }

}
