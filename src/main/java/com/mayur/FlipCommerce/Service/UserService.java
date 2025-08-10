package com.mayur.FlipCommerce.Service;

import com.mayur.FlipCommerce.DTOs.UserDto;
import com.mayur.FlipCommerce.Model.Cart;
import com.mayur.FlipCommerce.Model.UserEntity;
import com.mayur.FlipCommerce.Repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityRepository userRepository;
    private final ModelMapper modelMapper;

    public String createUser(UserDto userDto){
        UserEntity user = modelMapper.map(userDto, UserEntity.class);
        user.setCart(createCartForUser());
        UserEntity savedUser = userRepository.save(user);
        return "User registered Successfully";
    }

    public Cart createCartForUser(){
        Cart cart = new Cart();
        cart.setTotalQuantity(0);
        cart.setTotalPrice(0);
        cart.setCreatedAt(LocalDate.now());
        return cart;
    }
}
