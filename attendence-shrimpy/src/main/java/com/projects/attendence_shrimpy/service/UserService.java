package com.projects.attendence_shrimpy.service;


import com.projects.attendence_shrimpy.entity.User;
import com.projects.attendence_shrimpy.model.DeleteUserRequest;
import com.projects.attendence_shrimpy.model.RegisterUserRequest;
import com.projects.attendence_shrimpy.model.UpdatePasswordRequest;
import com.projects.attendence_shrimpy.model.UserResponse;
import com.projects.attendence_shrimpy.repository.UserRepository;
import com.projects.attendence_shrimpy.resolver.UserArgumentResolver;
import com.projects.attendence_shrimpy.security.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserArgumentResolver.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void register(RegisterUserRequest request) {
        validationService.validate(request);

        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());
        user.setPhone(request.getPhone());

        userRepository.save(user);
    }

    public UserResponse get(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }
    @Transactional
    public void delete(User user, String username) {
        User userTarget = userRepository.findById(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not exists"));
        userRepository.delete(userTarget);
    }

    @Transactional
    public void updatePassword(User user, UpdatePasswordRequest request){
        validationService.validate(request);

        if (BCrypt.checkpw(request.getOldPassword(), user.getPassword())){
            user.setPassword(BCrypt.hashpw(request.getNewPassword(), BCrypt.gensalt()));
            userRepository.save(user);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Old password is wrong");
        }
    }

}
