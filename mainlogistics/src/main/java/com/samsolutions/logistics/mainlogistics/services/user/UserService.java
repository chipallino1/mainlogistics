package com.samsolutions.logistics.mainlogistics.services.user;

import com.samsolutions.logistics.mainlogistics.services.security.Role;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    Role getRoleByEmail(String email);

}
