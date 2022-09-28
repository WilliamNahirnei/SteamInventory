package com.api.WNLS.com.api.WNLS.User;

import com.api.WNLS.com.api.WNLS.Utils.Exceptions.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getParkingSpotById(@PathVariable(value = "id") UUID id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
        }
        catch (ValidationException e){
            return ResponseEntity.status(e.getHttpCode()).body(e.getValidationMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid UserDTO userDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.upddate(id, userDTO));
        }
        catch (ValidationException e){
            return ResponseEntity.status(e.getHttpCode()).body(e.getValidationMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
