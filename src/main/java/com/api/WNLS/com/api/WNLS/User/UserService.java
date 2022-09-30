package com.api.WNLS.com.api.WNLS.User;

import com.api.WNLS.com.api.WNLS.Utils.Exceptions.ValidationException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save (UserDTO userDTO) throws Exception {
        validateUniqueAttributes(userDTO);
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        userModel.setUser_type(UserType.USER);
        return userRepository.save(userModel);
    }
    private void validateUniqueAttributes(UserDTO userDTO) throws ValidationException {
        if (this.existsByEmail(userDTO.getEmail()))
            throw new ValidationException("Conflict: e-mail is alert in use", 409, "Conflict: email is alert in use");
    }

    public  boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Page<UserModel> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public UserModel findById(UUID id) throws ValidationException {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            throw new ValidationException("User not found", HttpStatus.NOT_FOUND.value(), "User not Found");
        return userOptional.get();
    }

    @Transactional
    public UserModel upddate(UUID id, UserDTO userDTO) throws ValidationException {
        UserModel user = this.findById(id);
        BeanUtils.copyProperties(userDTO, user);
        return userRepository.save(user);
    }

    @Transactional
    public void delete(UUID id) throws ValidationException {
        UserModel user = this.findById(id);
        userRepository.delete(user);
    }
}
