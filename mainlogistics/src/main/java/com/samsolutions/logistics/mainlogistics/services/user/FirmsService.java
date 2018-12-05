package com.samsolutions.logistics.mainlogistics.services.user;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.dto.PageDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.services.utils.Converter;
import com.samsolutions.logistics.mainlogistics.services.utils.Pagination;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Firms service
 */
@Component
public interface FirmsService  extends Converter,Pagination<ContactDTO,Contacts> {

    /**
     * Get all firms
     * @return list dto of firms
     */
    List<FirmDTO> getAll();

    /**
     * Get all firms
     * @param firmName firm name
     * @return list dto of firms
     */
    List<FirmDTO> getAllByName(String firmName);

    /**
     * Get firm
     * @param email firm email
     * @return dto of firms
     */
    FirmDTO getByEmail(String email);

    /**
     * Update firm
     * @param email firm email
     * @param firmDTO dto of firms
     */
    void update(String email, FirmDTO firmDTO);

    /**
     * Add new contact user to firm user
     * @param contactDTO dto of contacts
     */
    void addContact(ContactDTO contactDTO);

    /**
     * Delete contact user from firm users
     * @param contactDTO dto of contacts
     */
    void deleteContact(ContactDTO contactDTO);

    /**
     *
     * @param firmName firm name
     * @param state status of contact user (ADDED,WAIT)
     * @return
     */
    PageDTO<ContactDTO> getContactsByPage(String firmName, String state, Pageable pageable);
}
