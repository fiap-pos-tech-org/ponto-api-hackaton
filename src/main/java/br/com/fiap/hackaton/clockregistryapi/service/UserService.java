package br.com.fiap.hackaton.clockregistryapi.service;

import br.com.fiap.hackaton.clockregistryapi.domain.User;
import br.com.fiap.hackaton.clockregistryapi.dto.UserDTO;
import br.com.fiap.hackaton.clockregistryapi.exception.EntityAlreadyExistException;
import br.com.fiap.hackaton.clockregistryapi.exception.EntityNotFoundException;
import br.com.fiap.hackaton.clockregistryapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(UserDTO userDTO) {
        return userRepository.save(new User(userDTO));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Usuário %s não encontrado", username)));
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Usuário %s não encontrado", userId)));
    }

    public void existsByUsernameOrEmail(String username, String email) {
        if (userRepository.existsByUsernameOrEmail(username, email)) {
            throw new EntityAlreadyExistException(String.format("Usuário com username %s ou email %s já está cadastrado",
                    username, email));
        }
    }

}
