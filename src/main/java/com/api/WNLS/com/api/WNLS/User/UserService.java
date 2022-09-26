package com.api.WNLS.com.api.WNLS.User;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save (UserDTO userDTO) throws Exception {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
//        userModel.setUser_type(UserType.USER);
        return userRepository.save(userModel);
    }
}
