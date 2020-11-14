package pl.mgrzech.alcohols_collection.emailTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.mgrzech.alcohols_collection.controllers.EmailController;
import pl.mgrzech.alcohols_collection.email.EmailService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmailControllerTests {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private EmailController emailController;

    private MockMvc mockMvc;

    @BeforeAll
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(emailController).build();
    }

    @Test
    public void shouldReturnToAddNewsletterViewAfterIncorrectValidation() throws Exception {
        mockMvc.perform(get("/user/sendEMailAllNewsletter"))
                .andExpect(view().name("newsletter/send_message_newsletter"));
    }

    @Test
    public void shouldReturnToAddNewsletterViewAfterIncorrectValidationdwq() throws Exception {
        mockMvc.perform(get("/admin/email/test"))
                .andExpect(redirectedUrl("/admin"));
    }

    @Test
    public void shouldReturnToAddNewsletterViewAfterIncorrectValidationdsawq() throws Exception {
        mockMvc.perform(post("/user/sendEMailAllNewsletter")
                .param("subject", "subject")
                .param("message", "message") )
                .andExpect(redirectedUrl("/user"));
    }
}
