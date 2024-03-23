package br.com.fiap.hackaton.clockregistryapi.service;

import br.com.fiap.hackaton.clockregistryapi.dto.JwtTokenDTO;
import br.com.fiap.hackaton.clockregistryapi.dto.SigninDTO;
import br.com.fiap.hackaton.clockregistryapi.dto.UserDTO;
import br.com.fiap.hackaton.clockregistryapi.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserService userService, PasswordEncoder passwordEncoder,
                                     JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDTO signup(UserDTO userDTO) {
        userService.existsByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        var userSaved = userService.save(userDTO);
        var jwt = jwtService.generateToken(userSaved);
        return new UserDTO(userSaved).withToken(jwt);
    }

    @Override
    public JwtTokenDTO signin(SigninDTO signinDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinDTO.getUsername(), signinDTO.getPassword()));
        var user = userService.findByUsername(signinDTO.getUsername());
        var jwt = jwtService.generateToken(user);
        return new JwtTokenDTO(jwt);
    }
}