package br.com.fiap.hackaton.clockregistryapi.service;

import br.com.fiap.hackaton.clockregistryapi.domain.User;
import br.com.fiap.hackaton.clockregistryapi.dto.UserDTO;
import br.com.fiap.hackaton.clockregistryapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO save(UserDTO userDTO) {
        var user = new User(userDTO);
        var userSaved = userRepository.save(user);
        return new UserDTO(userSaved);
    }

}
