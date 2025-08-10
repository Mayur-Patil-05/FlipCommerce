package com.mayur.FlipCommerce.Service;

import com.mayur.FlipCommerce.DTOs.UserDto;
import com.mayur.FlipCommerce.Exceptions.UserExistsException;
import com.mayur.FlipCommerce.Exceptions.UserNotFoundException;
import com.mayur.FlipCommerce.Model.Cart;
import com.mayur.FlipCommerce.Model.UserEntity;
import com.mayur.FlipCommerce.Repository.UserEntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public String createUser(UserDto userDto) {
        Optional<UserEntity> findUser = userRepository.findByEmail(userDto.getEmail());
        if (findUser.isPresent()) {
            throw new UserExistsException("User already registered with email: " + userDto.getEmail());
        }
        UserEntity user = modelMapper.map(userDto, UserEntity.class);
        UserEntity savedUser = userRepository.save(user);
        return "User registered Successfully";
    }

    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("Users not found");
        }
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    public UserDto getUserByEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new UserNotFoundException("Users not found with email: " + email);
        }
        return modelMapper.map(user.get(), UserDto.class);
    }

    @Transactional
    public String updateUser(Long userId, UserDto userDto) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userDto.getPhoneNumber());
        }
        if (userDto.getAddress() != null) {
            user.setAddress(userDto.getAddress());
        }
        return "User updated successfully";
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    public Cart createCartForUser() {
        Cart cart = new Cart();
        cart.setTotalQuantity(0);
        cart.setTotalPrice(0);
        cart.setCreatedAt(LocalDate.now());
        return cart;
    }
}
