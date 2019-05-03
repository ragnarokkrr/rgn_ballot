package ragna.ballot.rest.mapper;

import org.springframework.stereotype.Component;
import ragna.ballot.repository.model.User;
import ragna.ballot.rest.dto.UserDto;

@Component
public class UserMapper {
    public User fromDto(UserDto newUser) {
        return User.builder().name(newUser.getName()).username(newUser.getUsername()).build();
    }
}
