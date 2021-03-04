package com.henry.tryout.swagger;

import com.henry.tryout.entity.Contact;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController // to tell spring this class is for handling HTTP request
@RequestMapping("/api") // this is for the convenience for swagger-ui's endpoint
public class AddressBookResource {

    ConcurrentMap<String, Contact> contacts = new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds Contacts by id",
    notes = "Provide an id to look up specific contact from the address book",
    response = Contact.class) // about 'what this api does', 'what the response is'...
    public Contact getContact(@ApiParam(value = "ID value for the contact you need to retrieve", required = true)
                                  @PathVariable String id) {
        return contacts.get(id);
    }

    @GetMapping("/") // mapping request to / into this method, this is intentional
    public List<Contact> getAllContacts() {
        return new ArrayList<Contact>(contacts.values());
    }

    @PostMapping("/")
    public Contact addContact(@RequestBody Contact contact) {
        contacts.put(contact.getId(), contact);
        return contact;
    }
}
