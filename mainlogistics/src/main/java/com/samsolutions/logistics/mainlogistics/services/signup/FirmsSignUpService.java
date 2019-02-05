package com.samsolutions.logistics.mainlogistics.services.signup;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import org.springframework.stereotype.Component;

/**
 * Firms sign up service
 */
@Component
public interface FirmsSignUpService extends SignUpService{

    /**
     * Store firm in db
     * @param firmDTO firm info
     */
    void store(FirmDTO firmDTO);

    /**
     * update existing firm
     * @param email firm email
     */
    void updateFirm(String email);
    void createNew();
    /**
     * Set Contacts and Passwords entities by mapping
     * @param firmDTO dto of contacts
     */
    void setFirmDTO(FirmDTO firmDTO);

    /**
     * Save firm user
     */
    void saveFirm();
}
