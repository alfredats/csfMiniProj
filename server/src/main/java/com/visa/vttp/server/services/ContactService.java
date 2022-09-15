package com.visa.vttp.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.visa.vttp.server.models.Contact;
import com.visa.vttp.server.repositories.ContactRepository;

@Service()
public class ContactService {

    private final ContactRepository cRepo;

    @Autowired
    ContactService(ContactRepository cRepo) {
        this.cRepo = cRepo;
    }

    public List<Contact> getContacts() {
        return cRepo.getContacts();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void addContact(Contact c) {
        cRepo.insertNameByMobile(c.getName(), c.getMobile());
        cRepo.insertNameByEmail(c.getName(), c.getEmail());
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void removeContact(String email, String mobile) {
        cRepo.removeNameByEmail(email);
        cRepo.removeNameByMobile(mobile);
    }
}
