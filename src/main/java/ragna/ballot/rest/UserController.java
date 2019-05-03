package ragna.ballot.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ragna.ballot.common.exception.EntityNotFoundException;
import ragna.ballot.repository.UserRepository;
import ragna.ballot.repository.model.User;
import ragna.ballot.rest.dto.UserDto;
import ragna.ballot.rest.mapper.UserMapper;

import java.util.List;

@RestController("/v1")
@Api("/v1/user")
public class UserController {

    public static final String ENTITY = "User";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "List users.")
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @ApiOperation(value = "Create user.")
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody UserDto newUser) {
        User user = userMapper.fromDto(newUser);
        return userRepository.save(user);
    }

    @ApiOperation(value = "Find user by id.")
    @GetMapping("/users/{id}")
    User findUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY, id));
    }

    @ApiOperation(value = "Find user by username.")
    @GetMapping("/users/username/{username}")
    User findUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY, "username", username));
    }

}
