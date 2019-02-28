package com.samsolutions.logistics.mainlogistics.services.user.impl;

import com.samsolutions.logistics.mainlogistics.services.security.Role;
import com.samsolutions.logistics.mainlogistics.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Role getRoleByEmail(String email) {
        return null;
    }

    //private UsersRepository usersRepository;

   /* @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }*/

    /*@Override
    public Role getRoleByEmail(String email) {
        return usersRepository.findByEmail(email).getRole();
    }*/
}
