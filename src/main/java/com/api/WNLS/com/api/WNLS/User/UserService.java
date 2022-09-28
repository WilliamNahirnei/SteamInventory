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
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        userModel.setUser_type(UserType.USER);
        return userRepository.save(userModel);
    }
//    private void validateUniqueAttributes(ParkingSpotDTO parkingSpotDTO) throws ValidationException {
//        if (this.existsByLicensePlateCar(parkingSpotDTO.getLicensePlateCar()))
//            throw new ValidationException("Conflict: License plate car is alert in use", 409, "Conflict: License plate car is alert in use");
//        if (this.existsByParkingSpotNumber(parkingSpotDTO.getParkingSpotNumber()))
//            throw new ValidationException("Conflict: Parking spot is already in use", 409, "Conflict: Parking spot is already in use");
//        if (this.existsByApartmentAndBlock(parkingSpotDTO.getApartment(), parkingSpotDTO.getBlock()))
//            throw new ValidationException("Conflict: Parking spot already registered in this apartment and block", 409, "Conflict: Parking spot already registered in this apartment and block");
//    }

//    public  boolean existsByLicensePlateCar(String licensePlateCar) {
//        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
//    }
//
//    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
//        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
//    }
//
//    public boolean existsByApartmentAndBlock(String apartment, String block) {
//        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
//    }

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
