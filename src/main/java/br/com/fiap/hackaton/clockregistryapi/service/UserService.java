package br.com.fiap.hackaton.clockregistryapi.service;

import br.com.fiap.hackaton.clockregistryapi.domain.User;
import br.com.fiap.hackaton.clockregistryapi.exception.EntityNotFoundException;
import br.com.fiap.hackaton.clockregistryapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Usuário %s não encontrado", userId)));
    }

}
