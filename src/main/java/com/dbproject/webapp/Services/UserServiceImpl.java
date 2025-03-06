package com.dbproject.webapp.Services;

//import com.csc3402.security.form_login.dto.UserDto;
//import com.csc3402.security.form_login.model.Role;
//import com.csc3402.security.form_login.model.User;
//import com.csc3402.security.form_login.repository.RoleRepository;
//import com.csc3402.security.form_login.repository.UserRepository;
//import com.csc3402.security.form_login.util.TbConstants;

import com.dbproject.webapp.dto.UserDto;
import com.dbproject.webapp.Model.Role;
import com.dbproject.webapp.Model.User;
import com.dbproject.webapp.Repository.RoleRepository ;
import com.dbproject.webapp.Repository.UserRepository;
import com.dbproject.webapp.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private RoleRepository roleRepository ;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto){

        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if(role==null){
            role = roleRepository.save(new Role(TbConstants.Roles.USER));
        }

        User user = new User (userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), Arrays.asList(role) ) ;

        userRepository.save(user);

    }


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
