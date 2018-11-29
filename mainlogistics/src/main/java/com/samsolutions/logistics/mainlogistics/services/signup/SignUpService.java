package com.samsolutions.logistics.mainlogistics.services.signup;

import org.springframework.stereotype.Component;

/**
 * Sign up service
 */
@Component
public interface SignUpService {
    /**
     * Save Passwords entity
     */
    void savePassword();

    /**
     * Save Contacts entities
     */
    void save();

    /**
     * Save Users entity
     */
    void saveUser();
}
