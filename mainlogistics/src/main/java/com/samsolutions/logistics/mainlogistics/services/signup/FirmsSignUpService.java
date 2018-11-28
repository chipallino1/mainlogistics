package com.samsolutions.logistics.mainlogistics.services.signup;

import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import org.springframework.stereotype.Component;

@Component
public interface FirmsSignUpService {

    void setFirmDTO(FirmDTO firmDTO);

    void savePassword();

    void save();

    void saveUser();
}
