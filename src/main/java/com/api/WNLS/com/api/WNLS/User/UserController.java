package com.api.WNLS.com.api.WNLS.User;

import com.api.WNLS.com.api.WNLS.Utils.Exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDTO userDTO) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDTO));
        }
        catch (ValidationException e){
            return ResponseEntity.status(e.getHttpCode()).body(e.getValidationMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
