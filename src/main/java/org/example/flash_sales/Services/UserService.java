package org.example.flash_sales.Services;

import org.example.flash_sales.DTOs.UserDTO;
import org.example.flash_sales.Models.User;
import org.example.flash_sales.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO dto) {
        User existing = userRepository.findUserByEmail(dto.email());
        if (existing != null) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = dto.toEntity();
        User saved = userRepository.save(user);
        return UserDTO.fromEntity(saved);
    }

    public UserDTO getUser(String id) {
        User user = userRepository.findById(id).orElseThrow();
        return UserDTO.fromEntity(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::fromEntity)
                .toList();
    }

    public UserDTO updateUser(String id, UserDTO dto) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setType(dto.type());
        User saved = userRepository.save(user);
        return UserDTO.fromEntity(saved);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
