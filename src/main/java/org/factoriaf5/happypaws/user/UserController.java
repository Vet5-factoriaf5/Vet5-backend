package org.factoriaf5.happypaws.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDTOResponse> createUser(@RequestBody UserDTORequest dto) {
        UserEntity entity = userMapper.toEntity(dto);
        UserEntity saved = userService.save(entity);
        return ResponseEntity.ok(userMapper.toDTO(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTOResponse> getUser(@PathVariable Long id) {
        UserEntity user = userService.getUserById(id);
        return ResponseEntity.ok(userMapper.toDTO(user));
    }
}