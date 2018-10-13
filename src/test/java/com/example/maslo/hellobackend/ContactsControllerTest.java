package com.example.maslo.hellobackend;

import com.example.maslo.hellobackend.model.Contact;
import com.example.maslo.hellobackend.repository.ContactRepository;
import com.example.maslo.hellobackend.service.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ContactsControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ContactsControllerTest.class);

    @Mock
    private ContactRepository contactRepository;
    @InjectMocks
    private ContactService contactService;

    private LinkedList<Contact> testContacts;

    @Before
    public void init() {

        testContacts = new LinkedList<>();
        testContacts.add(new Contact(1, "10-point"));
        testContacts.add(new Contact(2, "Abisag"));
        testContacts.add(new Contact(3, "abbasi"));
        testContacts.add(new Contact(4, "AAPSS"));
        testContacts.add(new Contact(5, "Abdon"));
    }

    @Test
    public void shouldReturnAllContactsNotContainWithA() throws Exception {
        LinkedList<Contact> correctContacts = new LinkedList<>();
        correctContacts.add(new Contact(1, "10-point"));
        correctContacts.add(new Contact(3, "abbasi"));

        when(contactRepository.findAll()).thenReturn(testContacts);

        List<Contact> returnContacts = contactService.findByNamePattern("^A.*$");

        assertEquals(correctContacts.toString(), returnContacts.toString());
    }

    @Test
    public void shouldReturnContactsDoNotContainAEI() throws Exception {

        LinkedList<Contact> correctContacts = new LinkedList<>();
        correctContacts.add(new Contact(4, "AAPSS"));
        correctContacts.add(new Contact(5, "Abdon"));

        when(contactRepository.findAll()).thenReturn(testContacts);

        List<Contact> returnContacts = contactService.findByNamePattern("^.*[aei].*$");

        assertEquals(correctContacts.toString(), returnContacts.toString());

    }

    @Test(expected = PatternSyntaxException.class)
    public void shoulReturnExceptionWhenBadPattern() throws Exception {

        when(contactRepository.findAll()).thenReturn(testContacts);

        List<Contact> returnContacts = contactService.findByNamePattern("***");

    }


}
