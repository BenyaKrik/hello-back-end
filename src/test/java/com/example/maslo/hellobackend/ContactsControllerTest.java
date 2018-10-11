package com.example.maslo.hellobackend;

import com.example.maslo.hellobackend.controller.ContactController;
import com.example.maslo.hellobackend.model.Contact;
import com.example.maslo.hellobackend.service.ContactService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ContactsControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ContactsControllerTest.class);

    private MockMvc mockMvc;

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(contactController)
                .build();
    }

    @Test
    public void TestFindAllWithFilterSuccess() throws Exception {
        List<Contact> contacts = new LinkedList<Contact>();
        contacts.add(new Contact(1, "Admin"));
        contacts.add(new Contact(2, "user"));
        contacts.add(new Contact(3, "somebody else"));
        logger.info(" size in test" + contacts.size());

        Mockito.when(contactService.findAll("^A.*$")).thenReturn(contacts);
        mockMvc.perform(get("/hello/contacts?nameFilter="+ URLDecoder.decode("^A.*$", "UTF-8")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].id").value(contacts.get(2).getId()))
                .andExpect(jsonPath("$[0].name").value(contacts.get(2).getName()))
                .andExpect(jsonPath("$[1].id").value(contacts.get(3).getId()))
                .andExpect(jsonPath("$[1].name").value(contacts.get(3).getName()));
        Mockito.verify(contactService, Mockito.times(1)).findAll("^A.*$");
        Mockito.verifyNoMoreInteractions(contactService);
    }

    @Test
    public void TestFindAllsndSuccess() throws Exception {
        List<Contact> contacts = new LinkedList<Contact>();
        contacts.add(new Contact(1, "admin"));
        contacts.add(new Contact(2, "user"));
        contacts.add(new Contact(3, "somebody else"));
        logger.info(" size in test" + contacts.size());

        Mockito.when(contactService.findAll("")).thenReturn(contacts);
        mockMvc.perform(get("/hello/contacts?nameFilter=").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
//                .andExpect(jsonPath("$[0].id").value(contacts.get(1).getId()))
//                .andExpect(jsonPath("$[0].name").value(contacts.get(1).getName()))
//                .andExpect(jsonPath("$[2].id").value(contacts.get(2).getId()))
//                .andExpect(jsonPath("$[2].name").value(contacts.get(2).getName()));
       Mockito.verifyNoMoreInteractions(contactService);
    }

    @Test
    public void findByIdFailNotFound() throws Exception {
         // when(contactService.findAll("^A.*[$")).thenReturn(null);
        mockMvc.perform(get("/hello/contacts?nameFilter=%5EA.*%5B%24"))
                .andDo(print())
                .andExpect(status().isNotFound());
        Mockito.verifyNoMoreInteractions(contactService);
    }


}
