package br.com.fiap.hackaton.clockregistryapi.service;

import br.com.fiap.hackaton.clockregistryapi.dto.JwtTokenDTO;
import br.com.fiap.hackaton.clockregistryapi.dto.SigninDTO;
import br.com.fiap.hackaton.clockregistryapi.dto.UserDTO;

public interface AuthenticationService {
    UserDTO signup(UserDTO userDTO);
    JwtTokenDTO signin(SigninDTO signinDTO);
}