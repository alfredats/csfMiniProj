package com.visa.vttp.server.controllers;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visa.vttp.server.models.Contact;
import com.visa.vttp.server.services.ContactService;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@RestController()
@RequestMapping(path = { "/contacts" })
public class ContactController {

    private final ContactService cSvc;

    @Autowired
    ContactController(ContactService cSvc) {
        this.cSvc = cSvc;
    }

    @GetMapping(path = { "/", "/get" })
    public ResponseEntity<String> getContacts() {
        List<Contact> cList = this.cSvc.getContacts();
        JsonObjectBuilder objBldr = Json.createObjectBuilder();
        JsonArrayBuilder arrBldr = Json.createArrayBuilder();

        cList.stream().forEach((x) -> {
            arrBldr.add(x.toJson());
        });

        objBldr.add("code", HttpStatus.OK.value())
                .add("message", HttpStatus.OK.getReasonPhrase())
                .add("data", arrBldr.build());

        return ResponseEntity.ok(objBldr.build().toString());
    }

    @PostMapping(path = { "/add" }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addContact(@RequestBody String jsonStr) {
        Contact c;
        JsonObjectBuilder objBldr = Json.createObjectBuilder();
        try {
            JsonObject reqBody = Json.createReader(new StringReader(jsonStr)).readObject();
            c = Contact.create(reqBody);
            cSvc.addContact(c);
        } catch (Exception e) {
            objBldr.add("code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .add("message", e.getMessage());
            return ResponseEntity.badRequest().body(objBldr.build().toString());
        }

        objBldr.add("code", HttpStatus.CREATED.value())
                .add("message", HttpStatus.CREATED.getReasonPhrase())
                .add("data", c.getName());
        return ResponseEntity.ok().body(objBldr.build().toString());
    }

    @PostMapping(path = { "/remove" }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeContact(@RequestBody String jsonStr) {
        Contact c;
        JsonObjectBuilder objBldr = Json.createObjectBuilder();
        try {
            JsonObject reqBody = Json.createReader(new StringReader(jsonStr)).readObject();
            c = Contact.create(reqBody);
            cSvc.removeContact(c.getEmail(), c.getMobile());
        } catch (Exception e) {
            objBldr.add("code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .add("message", e.getMessage());
            return ResponseEntity.badRequest().body(objBldr.build().toString());
        }

        objBldr.add("code", HttpStatus.OK.value())
                .add("message", "Removed")
                .add("data", c.getName());
        return ResponseEntity.ok().body(objBldr.build().toString());
    }

}
