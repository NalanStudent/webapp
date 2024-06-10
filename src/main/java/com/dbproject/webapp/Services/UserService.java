package com.dbproject.webapp.Services;

//import com.csc3402.security.form_login.dto.UserDto;
//import com.csc3402.security.form_login.model.User;

import com.dbproject.webapp.dto.UserDto;
import com.dbproject.webapp.Model.User;

public interface UserService {

    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
}
