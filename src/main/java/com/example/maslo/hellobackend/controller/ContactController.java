package com.example.maslo.hellobackend.controller;

import com.example.maslo.hellobackend.model.Contact;
import com.example.maslo.hellobackend.service.ContactService;
import com.example.maslo.hellobackend.validator.RegexPatternValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@RestController
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/hello/contacts", params = {"nameFilter"}, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Contact>> findContacts(@RequestParam(value = "nameFilter") String nameFilter) {
        logger.info("FindContacts start :"+nameFilter);
        if (nameFilter.trim().isEmpty()){
            logger.info("findContacts with  EMPTY:");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            Pattern.compile(nameFilter);
        } catch (Exception exception) {
            logger.error(" Bad reques with pafametr   "+exception );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        logger.info("findContacts size:"+contactService.findAll(nameFilter).size());
        return ResponseEntity.status(HttpStatus.OK).body(contactService.findAll(nameFilter));

    }

//    @RequestMapping(value = "/hello/contacts", params = {"nameFilter"}, method = RequestMethod.GET, produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<Model> getAllCards(@RequestParam(value = "nameFilter", required = true, defaultValue = "") String nameFilter, final Model model, HttpServletRequest req) {
//
//
//        List<Contact> contacts =  contactService.findAll(nameFilter);
//
//        if (contacts == null || contacts.size() == 0) {
//
//              model.addAttribute( );
//
//            return new ResponseEntity<Model>(model, HttpStatus.NOT_FOUND);
//        }
//
//        model.addAttribute("contacts", contacts);
//
//        return new ResponseEntity<Model>(model, HttpStatus.OK);
//    }

    @ExceptionHandler(ContactException.class)
    public void handleException(ContactException e,
                                HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
}
